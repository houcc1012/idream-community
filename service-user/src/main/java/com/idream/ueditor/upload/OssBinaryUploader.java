package com.idream.ueditor.upload;

import com.idream.commons.lib.util.SpringUtil;
import com.idream.service.ImageUploadService;
import com.idream.service.impl.ImageUploadServiceImpl;
import com.idream.ueditor.define.AppInfo;
import com.idream.ueditor.define.BaseState;
import com.idream.ueditor.define.FileType;
import com.idream.ueditor.define.State;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author hejiang
 * @date 2018/8/24
 */

public class OssBinaryUploader {

    @Resource
    private ImageUploadService imageUploadService;

    public State save(HttpServletRequest request, Map<String, Object> conf) {
        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("upfile");

        try {
            if (file == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }
            String originFileName = file.getOriginalFilename();
            String suffix = FileType.getSuffixByFilename(file.getOriginalFilename());
            originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
            long maxSize = ((Long) conf.get("maxSize")).longValue();
            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            State storageState = new BaseState();

            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6bnVsbCwibmlja05hbWUiOiLnq4vljbPnmbvlvZUiLCJ1c2VyVHlwZSI6MSwidXNlck5hbWUiOm51bGwsInVzZXJSb2xlIjo5LCJ1c2VySWQiOjQsInRzIjoxNTM1MDA4OTM0MTY2fQ.4ZOIndgGZfMy7D4_fvDHKatYWdPo42C8Al8KTCXODxQ";
            if(imageUploadService == null){
                imageUploadService = SpringUtil.getBean("imageUploadServiceImpl", ImageUploadServiceImpl.class);
            }
            String url = imageUploadService.uploadImage(token, file);
            if (storageState.isSuccess()) {
                storageState.putInfo("url", url);
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
                storageState.putInfo("title", url.substring(url.lastIndexOf("/") + 1));
            }
            return storageState;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseState(false, AppInfo.IO_ERROR);
        }
    }

    public static void main(String[] args) {
        String url = "https://hzfh-test.oss-cn-hangzhou.aliyuncs.com/default/2018-08-24/4-759400971452045.jpg";
        System.out.println(url.substring(url.lastIndexOf("/")));
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }

}
