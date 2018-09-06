package com.idream.commons.mvc.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 配置上传文件大小
 *
 * @author hejiang
 */
@Configuration
public class FileUploadConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement(
            @Value("${multipart.maxFileSize}") String maxFileSize,
            @Value("${multipart.maxRequestSize}") String maxRequestSize,
            @Value("${multipart.location}")String location) {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大
        factory.setMaxFileSize(maxFileSize);
        // 设置总上传数据总大小
        factory.setMaxRequestSize(maxRequestSize);
        String osName = System.getProperties().getProperty("os.name");
        if(osName.toLowerCase().startsWith("windows")){
            Path uploadTmp = Paths.get(System.getProperty("user.dir")).getParent().resolve("upload_tmp");
            uploadTmp.toFile().mkdirs();
            location =uploadTmp.toString();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
