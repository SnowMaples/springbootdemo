/**
 * FileName: GracefulShutdownService
 * Author:   snowm
 * Date:     2018/9/8 16:55
 * Description: 优雅的关闭springboot服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.snow.demo.service;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈优雅的关闭springboot服务〉
 *
 * @author snowm
 * @create 2018/9/8
 * @since 1.0.0
 */
public class GracefulShutdownService implements TomcatConnectorCustomizer,ApplicationListener<ContextClosedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(GracefulShutdownService.class);
    private volatile Connector connector;
    private final int waitTime = 10;
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        this.connector.pause();
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        logger.info("正在关闭tomcat进程.......");
        if(executor instanceof ThreadPoolExecutor){
            try{
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdownNow();
                logger.info("关闭tomcat进程 shutdownNow .......");
                if(!threadPoolExecutor.awaitTermination(waitTime,TimeUnit.SECONDS)){
                    logger.warn("Tomcat 进程在{}秒内无法结束，请强制结束",waitTime);
                }
            }catch (InterruptedException e){
                logger.error(e.getMessage());
            }
        }



    }

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }
}
