/**
 * FileName: DemoApplication
 * Author:   snowm
 * Date:     2018/8/25 12:47
 * Description: springboot启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.snow.demo;

import com.snow.demo.init.InitApplicationListener;
import com.snow.demo.service.CreateFileService;
import com.snow.demo.service.GracefulShutdownService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.xml.ws.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈springboot启动类〉
 *
 * @author snowm
 * @create 2018/8/25
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication .class);
        springApplication.addListeners(new InitApplicationListener());
        springApplication.run(args);
    }
    @Bean
    public GracefulShutdownService gracefulShutdownService(){
        return new GracefulShutdownService();
    }

    @Bean
    public ServletWebServerFactory servletWebServerFactory(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers(gracefulShutdownService());
        return tomcatServletWebServerFactory;
    }

}
