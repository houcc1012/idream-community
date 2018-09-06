package com.idream.controller;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.mvc.base.BaseController;
import com.idream.service.ExcelUploadService;
import com.idream.service.ImageUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author hejiang
 * @date 2018/3/26
 */
@RestController
@RequestMapping("/upload")
@Api(tags = "上传相关(小程序)", description = "上传图片相关接口")
public class ImageUploadController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    @Resource
    private ImageUploadService imageUploadService;
    @Autowired
    private ExcelUploadService excelUploadService;

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ApiOperation(value="上传图片", notes = "上传图片", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> uploadImage(@NotNull(message = "上传文件为空") @RequestParam(value = "file") MultipartFile file){
        //上传图片
        String imageUrl = imageUploadService.uploadImage(getToken(), file);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "上传成功", imageUrl);
    }

    @RequestMapping(value = "/image/classify", method = RequestMethod.POST)
    @ApiOperation(value="分类上传图片", notes = "分类上传图片", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> uploadImage(@ApiParam("type类型:1-用户头像;2-邻里生活;3-活动图片;4-banner;5-系统图片;6-奖品图片;7-投诉")@RequestParam Byte type,
                                             @NotNull(message = "上传文件为空") @RequestParam(value = "file") MultipartFile file){
        //上传图片
        String imageUrl = imageUploadService.uploadImage(getToken(), file, type);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "上传成功", imageUrl);
    }

    @RequestMapping(value = "/image/untreated", method = RequestMethod.POST)
    @ApiOperation(value="图片不处理直接上传", notes = "图片不处理直接上传", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> uploadImageUnTreated(@NotNull(message = "上传文件为空") @RequestParam(value = "file") MultipartFile file){

        //上传图片
        String imageUrl = imageUploadService.uploadImageUnTreated(getToken(), file);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "上传成功", imageUrl);
    }

    @RequestMapping(value = "/excel/life", method = RequestMethod.POST)
//    @ApiOperation("生活动态")
    public JSONPublicDto<String> uploadExcel(@RequestParam(value = "file") MultipartFile file, @RequestParam("fileLocation") String fileLocation, @RequestParam("startId") Integer startId,@RequestParam("endId") Integer endId ){
        excelUploadService.uploadLifeExcel(file,fileLocation,startId,endId);
        return JSONPublicDto.returnSuccessData("ok");
    }
    @RequestMapping(value = "/excel/activityLife", method = RequestMethod.POST)
//    @ApiOperation("活动动态")
    public JSONPublicDto<String> uploadExcelActivityLife(@RequestParam(value = "file") MultipartFile file, @RequestParam("fileLocation") String fileLocation, @RequestParam("startId") Integer startId,@RequestParam("endId") Integer endId ){
        excelUploadService.uploadActivityLifeExcel(file,fileLocation,startId,endId);
        return JSONPublicDto.returnSuccessData("ok");
    }
    @RequestMapping(value = "/excel/user",method = RequestMethod.POST)
//    @ApiOperation("创建用户信息")
    public JSONPublicDto<String> uploadExcelUser(@RequestParam(value = "file") MultipartFile file, @RequestParam("fileLocation") String fileLocation) {
        excelUploadService.uploadUser(file,fileLocation);
        return JSONPublicDto.returnSuccessData("ok");
    }

}
