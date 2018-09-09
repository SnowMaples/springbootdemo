/**
 * FileName: CreateFileService
 * Author:   snowm
 * Date:     2018/8/25 14:58
 * Description: 生成文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.snow.demo.service;

import com.twelvemonkeys.io.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈生成文件〉
 *
 * @author snowm
 * @create 2018/8/25
 * @since 1.0.0
 */
@Service
public class CreateFileService {
    private static final Logger logger = LoggerFactory.getLogger(CreateFileService.class);
    @Value("${folder.path}")
    private String folderPath;
    @Value("${create.file.interval}")
    private String createInterval;
    @Value("${poolSize}")
    private String poolSize;
    private final ExecutorService createFilePool = Executors.newFixedThreadPool(1);
    private final ExecutorService deleteFilePool = Executors.newFixedThreadPool(1);
    public void createFile(){
        for(int i = 0; i <Integer.parseInt(poolSize);i++){
            createFilePool.execute(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        long timestamp = System.currentTimeMillis();
                        logger.info("creating file:{}",timestamp);
                        try {
                            FileUtil.write(new File(folderPath+File.separator+timestamp),"springboot file create".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(Long.parseLong(createInterval));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void deleteFile(){
        deleteFilePool.execute(new Runnable() {
            @Override
            public void run() {
                while (true){

                    try {
                        File[] files = FileUtil.list(folderPath);
                        for(int i = 0 ; i < files.length;i++){
                            FileUtil.delete(files[i]);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(Long.parseLong(createInterval));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }



}
