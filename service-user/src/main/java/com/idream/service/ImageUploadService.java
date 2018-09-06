package com.idream.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author hejiang
 */
public interface ImageUploadService {

    /**
     * 上传图片
     *
     * @param file
     */
    String uploadImage(String token, MultipartFile file);

    /**
     * 上传图片
     *
     * @param token
     * @param file
     * @param type
     */
    String uploadImage(String token, MultipartFile file, Byte type);

    /**
     * 图片不处理上传
     *
     * @param token
     * @param file
     */
    String uploadImageUnTreated(String token, MultipartFile file);
}
