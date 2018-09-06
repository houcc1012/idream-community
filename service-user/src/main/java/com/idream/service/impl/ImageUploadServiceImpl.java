package com.idream.service.impl;

import com.idream.commons.db.token.JWTTokenService;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.token.TokenVerifyDto;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.util.DateUtils;
import com.idream.controller.ImageUploadController;
import com.idream.service.ImageUploadService;
import com.idream.utils.AliyunOssUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 图片处理
 *
 * @author hejiang
 */
@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    private final static Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    //支持图片格式集合
    private final static List<String> extensions = new ArrayList<String>(){{
        add(".jpg");
        add(".png");
        add(".gif");
        add(".webp");
    }};

    //文件压缩大小2M
    private final static int COMPRESSION_SIZE = 2 * 1024 * 1024;

    //文件压缩大小300k 图片大小比这个小的不压缩
    private final static int MIN_IMAGE_SIZE = 300 * 1024;

    @Resource
    private AliyunOssUtils aliyunOssUtils;

    @Resource
    private JWTTokenService jwtTokenService;

    @Value("${upload.file.temporary.path}")
    private String filePath;

    @Override
    public String uploadImage(String token, MultipartFile file, Byte type) {
        //校验token合法性
        TokenVerifyDto tokenVerify = jwtTokenService.verify(token);
        if (!tokenVerify.isVerifyResult()) {
            logger.error("根据token获取用户信息失败");
            throw new BusinessException(RetCodeConstants.REQUEST_VALIDATE_ERROR);
        }
        //文件名
        String fileName = file.getOriginalFilename();
        //文件格式
        String extension = handlerImageExtension(fileName.substring(fileName.lastIndexOf(".")));
        //上传文件名
        String uploadFileName = tokenVerify.getAuthUserInfo().getUserId() + "-" + System.nanoTime() + extension;
        //本地文件存储
        File tempFile = saveLocalFile(file, uploadFileName);
        String requestUrl = null;
        try {
            //是否GIF图片
            boolean isGif = ".gif".equalsIgnoreCase(extension);
            //获取用户上传目录
            String uploadCatalog = getUploadCatalog(type);
            //图片上传阿里云OSS
            requestUrl = uploadAliOSS(file, uploadFileName, tempFile, uploadCatalog, isGif);
        } catch (Exception e) {
            logger.error("上传图片失败!", e);
            throw new BusinessException(RetCodeConstants.UPLOAD_IMAGE_ERROR, "上传图片失败");
        } finally {
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        return requestUrl;
    }


    @Override
    public String uploadImage(String token, MultipartFile file) {
        //校验token合法性
        TokenVerifyDto tokenVerify = jwtTokenService.verify(token);
        if (!tokenVerify.isVerifyResult()) {
            logger.error("根据token获取用户信息失败");
            throw new BusinessException(RetCodeConstants.REQUEST_VALIDATE_ERROR);
        }
        //文件名
        String fileName = file.getOriginalFilename();
        //文件格式
        String extension = handlerImageExtension(fileName.substring(fileName.lastIndexOf(".")));
        //上传文件名
        String uploadFileName = tokenVerify.getAuthUserInfo().getUserId() + "-" + System.nanoTime() + extension;
        //本地文件存储
        File tempFile = saveLocalFile(file, uploadFileName);
        String requestUrl = null;
        String uploadCatalog = "default/" + DateUtils.format(new Date(), DateUtils.YYYY_MM_DD) + "/";
        try {
            boolean isGif = ".gif".equalsIgnoreCase(extension);
            //图片上传阿里云OSS
            requestUrl = uploadAliOSS(file, uploadFileName, tempFile, uploadCatalog, isGif);
        } catch (Exception e) {
            logger.error("上传图片失败!", e);
            throw new BusinessException(RetCodeConstants.UPLOAD_IMAGE_ERROR, "上传图片失败");
        } finally {
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        return requestUrl;
    }

    /**
     * 根据业务类型获取阿里云上传目录
     *
     * @param type
     */
    private String getUploadCatalog(Byte type) {
        String uploadCatalog = "";
        if (SystemEnum.UploadImgType.USER_LOGO.getCode().equals(type)) {
            uploadCatalog = SystemEnum.UploadImgFileFolder.USER_LOGO.getCode();
        } else if (SystemEnum.UploadImgType.ACTIVITY.getCode().equals(type)) {
            uploadCatalog = SystemEnum.UploadImgFileFolder.ACTIVITY.getCode();
        } else if (SystemEnum.UploadImgType.COMMUNITY_LIFR.getCode().equals(type)) {
            uploadCatalog = SystemEnum.UploadImgFileFolder.COMMUNITY_LIFR.getCode();
        } else if (SystemEnum.UploadImgType.BANNER.getCode().equals(type)) {
            uploadCatalog = SystemEnum.UploadImgFileFolder.BANNER.getCode();
        } else if (SystemEnum.UploadImgType.SYSTEM.getCode().equals(type)) {
            uploadCatalog = SystemEnum.UploadImgFileFolder.SYSTEM.getCode();
        } else if (SystemEnum.UploadImgType.AWARD.getCode().equals(type)) {
            uploadCatalog = SystemEnum.UploadImgFileFolder.AWARD.getCode();
        } else if (SystemEnum.UploadImgType.COMPLAIN.getCode().equals(type)) {
            uploadCatalog = SystemEnum.UploadImgFileFolder.COMPLAIN.getCode();
        }
        return uploadCatalog + DateUtils.format(new Date(), DateUtils.YYYY_MM_DD) + "/";
    }

    /**
     * 图片压缩
     *
     * @param file
     * @param size
     * @param outFileName
     */
    private static boolean thumbnail(File file, double size, String outFileName) {
        try {
            Thumbnails.of(file).scale(size).toFile(outFileName);
        } catch (IOException e) {
            logger.error("读取图片发生异常, 尝试cmyk转化", e);
            try {
                BufferedImage image = ImageIO.read(file);
                ImageOutputStream output = ImageIO.createImageOutputStream(file);
                if (!ImageIO.write(image, "jpg", output)) {
                    logger.info("cmyk转化异常");
                }
                Thumbnails.of(image).scale(size).toFile(outFileName);
                logger.info("cmyk转化成功");
            } catch (IOException e1) {
                logger.info("cmyk转化异常", e1);
                return false;
            }
        } catch (Exception e) {
            logger.error("上传图片失败", e);
            return false;
        }
        return true;
    }

    /**
     * 图片上传
     *
     * @param file
     * @param uploadFileName
     * @param tempFile
     * @param uploadCatalog
     * @param isGif          是否GIF图片
     */
    private String uploadAliOSS(MultipartFile file, String uploadFileName, File tempFile, String uploadCatalog, boolean isGif) {
        String requestUrl;//上传后的路径
        if (MIN_IMAGE_SIZE > file.getSize()) {
            //低于最小上传文件大小,默认不压缩
            requestUrl = aliyunOssUtils.aliOssUpload(uploadFileName, uploadCatalog, tempFile);
        } else {
            boolean flag = false;
            if (!isGif) {
                //图片压缩
                if (COMPRESSION_SIZE > file.getSize()) {
                    flag = thumbnail(tempFile, 0.6f, tempFile.getPath());
                } else {
                    flag = thumbnail(tempFile, 0.5f, tempFile.getPath());
                }
            }
            //压缩成功传压缩成功后的图,压缩失败传原图
            if (flag) {
                requestUrl = aliyunOssUtils.aliOssUpload(uploadFileName, uploadCatalog, tempFile.getPath());
            } else {
                requestUrl = aliyunOssUtils.aliOssUpload(uploadFileName, uploadCatalog, tempFile);
            }
        }
        return requestUrl;
    }

    @Override
    public String uploadImageUnTreated(String token, MultipartFile file) {
        //校验token合法性
        TokenVerifyDto tokenVerify = jwtTokenService.verify(token);
        if (!tokenVerify.isVerifyResult()) {
            logger.error("根据token获取用户信息失败");
            throw new BusinessException(RetCodeConstants.REQUEST_VALIDATE_ERROR);
        }
        //文件名
        String fileName = file.getOriginalFilename();
        //文件格式
        String extension = handlerImageExtension(fileName.substring(fileName.lastIndexOf(".")));

        //上传文件名
        String uploadFileName = tokenVerify.getAuthUserInfo().getUserId() + "-" + System.nanoTime() + extension;
        //本地文件存储
        File tempFile = saveLocalFile(file, uploadFileName);
        String requestUrl = null;
        String uploadCatalog = "default/" + DateUtils.format(new Date(), DateUtils.YYYY_MM_DD) + "/";
        try {
            requestUrl = aliyunOssUtils.aliOssUpload(uploadFileName, uploadCatalog, tempFile);
        } catch (Exception e) {
            logger.error("上传图片失败!", e);
            throw new BusinessException(RetCodeConstants.UPLOAD_IMAGE_ERROR, "上传图片失败");
        } finally {
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        return requestUrl;
    }

    private String handlerImageExtension(String extension){
        if(StringUtils.isNotEmpty(extension)){
            if(extensions.contains(extension.toLowerCase())){
                return extension;
            }
        }
        return ".jpg";
    }


    /**
     * 保存本地文件
     *
     * @param file
     * @param uploadFileName
     */
    private File saveLocalFile(MultipartFile file, String uploadFileName) {
        //创建文件夹
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //先把文件保存到本地
        File tempFile = null;
        try {
            String path = filePath + File.separator + uploadFileName;
            tempFile = new File(path);
            FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
        } catch (IOException e) {
            logger.error("本地存储图片失败", e);
            throw new BusinessException(RetCodeConstants.UPLOAD_IMAGE_ERROR, "上传图片失败");
        }
        return tempFile;
    }
}
