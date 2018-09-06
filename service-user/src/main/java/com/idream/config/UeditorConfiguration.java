package com.idream.config;

import com.idream.ueditor.ActionEnter;
import com.idream.ueditor.ConfigManager;
import com.idream.ueditor.UEditorConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hejiang
 * @date 2018/8/23
 */
@Configuration
public class UeditorConfiguration {

    @Value("${ueditor.config:config.json}")
    private String config;

    @Value("${ueditor.unified:true}")
    private boolean ueditorUnified;

//    @Value("${ueditor.upload-path}")
//    private String path;
//
//    @Value("${ueditor.url-prefix}")
//    private String prefix;

    @Bean
    public UEditorConfig uEditorConfig(){
        UEditorConfig uEditorConfig = new UEditorConfig();
        uEditorConfig.setConfig(config);
        uEditorConfig.setUnified(ueditorUnified);
//        uEditorConfig.setUploadPath(path);
//        uEditorConfig.setUrlPrefix(prefix);
        return uEditorConfig;
    }

    @Bean
    public ActionEnter actionEnter(){
        return new ActionEnter(ConfigManager.getInstance(uEditorConfig()));
    }

}
