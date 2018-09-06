package com.idream.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;


@Service
public class AliyunOssUtils {
    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Value("${aliyunOss.externalEndpoint}")
    private String externalEndpoint;

    @Value("${aliyunOss.insideEndpoint}")
    private String insideEndpoint;

    @Value("${aliyunOss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyunOss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyunOss.bucketName}")
    private String bucketName;

    @Value("${aliyunOss.isExternal}")
    private boolean isExternal;

    @Value("${aliyunOss.request.url}")
    private String requestUrl;

    /**
     * aliyun oss 根据图片路径上传
     *
     * @param uploadFileName 上传文件名
     * @param uploadFilePath 文件上传存放路径
     * @param filePath       上传文件路径
     */
    public String aliOssUpload(String uploadFileName, String uploadFilePath, String filePath) {
        uploadFileName = addImageHeightAndWidth(uploadFileName, Paths.get(filePath).toFile());
        OSSClient client = null;
        //是否使用外网路径
        String endpoint = isExternal ? externalEndpoint : insideEndpoint;

        if (StringUtils.isNotEmpty(uploadFilePath)) {
            if (uploadFilePath.endsWith("/") || uploadFilePath.endsWith("\\")) {
                uploadFileName = uploadFilePath + uploadFileName;
            } else {
                uploadFileName = uploadFilePath + File.separator + uploadFileName;
            }
        }
        try {
            client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            File file = new File(filePath);
            InputStream content = new FileInputStream(file);
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            // 必须设置ContentLength
            meta.setContentLength(file.length());
            meta.setCacheControl("no-cache");
            meta.setObjectAcl(CannedAccessControlList.PublicRead);
            // 上传Object.
            PutObjectResult result = client.putObject(bucketName, uploadFileName, content, meta);
            return requestUrl + "/" + uploadFileName;
        } catch (OSSException e) {
            logger.error("OSS Error Message: " + e.getErrorCode());
            logger.error("OSS Error Code:    " + e.getErrorCode());
            logger.error("OSS Request ID:    " + e.getRequestId());
            logger.error("OSS Host ID:       " + e.getHostId());
        } catch (FileNotFoundException e) {
            logger.error("OSS文件找不到", e);
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return null;
    }

    /**
     * aliyun oss 根据图片路径上传
     *
     * @param uploadFileName 上传文件名
     * @param uploadFilePath 文件上传存放路径
     * @param file           文件对象
     */
    public String aliOssUpload(String uploadFileName, String uploadFilePath, File file) {

        uploadFileName = addImageHeightAndWidth(uploadFileName, file);
        String endpoint = isExternal ? externalEndpoint : insideEndpoint;
        if (StringUtils.isNotEmpty(uploadFilePath)) {
            if (uploadFilePath.endsWith("/") || uploadFilePath.endsWith("\\")) {
                uploadFileName = uploadFilePath + uploadFileName;
            } else {
                uploadFileName = uploadFilePath + File.separator + uploadFileName;
            }
        }
        OSSClient client = null;
        try {
            client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            InputStream content = new FileInputStream(file);
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            // 必须设置ContentLength
            meta.setContentLength(file.length());
            meta.setCacheControl("no-cache");
            meta.setObjectAcl(CannedAccessControlList.PublicRead);
            // 上传Object
            PutObjectResult result = client.putObject(bucketName, uploadFileName, content, meta);
            return requestUrl + "/" + uploadFileName;
        } catch (OSSException e) {
            logger.error("OSS Error Message: " + e.getErrorCode());
            logger.error("OSS Error Code:    " + e.getErrorCode());
            logger.error("OSS Request ID:    " + e.getRequestId());
            logger.error("OSS Host ID:       " + e.getHostId());
        } catch (FileNotFoundException e) {
            logger.error("OSS文件找不到", e);
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return null;
    }

    private String addImageHeightAndWidth(String uploadFileName, File file) {
        try {
            int i = uploadFileName.indexOf('.');
            String type = uploadFileName.substring(i);
            int width = 0;
            int height = 0;
            // 处理webp 格式的 宽高
            if (".webp".equalsIgnoreCase(type)) {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[30];
                fileInputStream.read(bytes, 0, bytes.length);
                width = ((int) bytes[27] & 0xff) << 8 | ((int) bytes[26] & 0xff);
                height = ((int) bytes[29] & 0xff) << 8 | ((int) bytes[28] & 0xff);
                fileInputStream.close();
            } else {
                BufferedImage bi = ImageIO.read(file);
                if (bi != null) {
                    height = bi.getHeight();
                    width = bi.getWidth();
                }
            }
            uploadFileName = uploadFileName.substring(0, i) + String.format("@%d*%d", height, width) + type;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadFileName;
    }
}