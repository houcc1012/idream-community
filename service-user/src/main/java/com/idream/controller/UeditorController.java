package com.idream.controller;

import com.alibaba.fastjson.JSONObject;
import com.idream.commons.mvc.base.BaseController;
import com.idream.service.ImageUploadService;
import com.idream.ueditor.ActionEnter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author hejiang
 * @date 2018/8/23
 */

@Controller
public class UeditorController extends BaseController{

    @Autowired
    private ActionEnter actionEnter;

    @Resource
    private ImageUploadService imageUploadService;

    @ResponseBody
    @RequestMapping("/ueditor/exec")
    public String exe(HttpServletRequest request){
        String str = actionEnter.exec(request);
        System.out.println(str);
        return str;
    }

    @RequestMapping(value = "/ueditor/image", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImage(@RequestParam("upfile") MultipartFile file,
                              @ApiParam("type类型:1-用户头像;2-邻里生活;3-活动图片;4-banner;5-系统图片;6-奖品图片;7-投诉")
                              @RequestParam(value = "type", required = false) Byte type){
        //上传图片
        String imageUrl;
        if(type == null){
            imageUrl = imageUploadService.uploadImage(getToken(), file);
        }else{
            imageUrl = imageUploadService.uploadImage(getToken(), file, type);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", "SUCCESS");
        jsonObject.put("url", imageUrl);
        return jsonObject.toJSONString();
    }
}
