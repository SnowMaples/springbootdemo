/**
 * FileName: InitApplicationListener
 * Author:   snowm
 * Date:     2018/8/25 15:43
 * Description: 启动监听器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.snow.demo.init;

import com.snow.demo.service.CreateFileService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 〈一句话功能简述〉<br> 
 * 〈启动监听器〉
 *
 * @author snowm
 * @create 2018/8/25
 * @since 1.0.0
 */
public class InitApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        CreateFileService createFileService = (CreateFileService) context.getBean(CreateFileService.class);
//        createFileService.createFile();
        createFileService.deleteFile();
    }
}
