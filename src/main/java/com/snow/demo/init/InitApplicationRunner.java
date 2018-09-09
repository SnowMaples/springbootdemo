/**
 * FileName: ApplicationInitListener
 * Author:   snowm
 * Date:     2018/8/25 14:59
 * Description: 程序初始化加载
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.snow.demo.init;

import com.snow.demo.service.CreateFileService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈程序初始化加载〉
 *
 * @author snowm
 * @create 2018/8/25
 * @since 1.0.0
 */
@Component
@Order(value = 1)
public class InitApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("启动.....");
    }
}
