package com.idream.controller.admin;


import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.app.NoticeListRequestDto;
import com.idream.commons.lib.dto.app.NoticeListResponseDto;
import com.idream.commons.lib.dto.app.SystemNoticeParams;
import com.idream.commons.lib.dto.app.SystemNoticeRequestDto;
import com.idream.commons.lib.model.SystemNotice;
import com.idream.feign.FeignThirdInterfaceService;
import com.idream.service.AdminNoticeListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/admin/systemnotice")
@Api(tags = "系统消息推送", description = "AdminSystemNoticeController")
public class AdminSystemNoticeController {

    @Autowired
    private AdminNoticeListService adminNoticeListService;
    @Autowired
    private FeignThirdInterfaceService feignThirdInterfaceService;

    @RequestMapping(value = "/notice/list", method = RequestMethod.GET)
    @ApiOperation(value = "通知列表", notes = "通知列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<NoticeListResponseDto>> selectNoticeList(NoticeListRequestDto noticeListRequestDto) {
        PagesDto<NoticeListResponseDto> data = adminNoticeListService.selectNoticeList(noticeListRequestDto);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/add/notice", method = RequestMethod.POST)
    @ApiOperation(value = "新增新通知", notes = "新增新通知", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addNewNotice(@ApiIgnore Integer authUserId, @RequestBody SystemNoticeRequestDto systemNoticeRequestDto) {
        int i = adminNoticeListService.addNewNotice(authUserId, systemNoticeRequestDto);
        if (i == 0) {
            return JSONPublicDto.returnErrorData(RetCodeConstants.SAVE_FAILED, "添加失败");
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "添加成功", null);
    }

    @RequestMapping(value = "/select/notice/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "编辑通知数据回显", notes = "编辑通知数据回显", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<SystemNotice> selectNotice(@ApiParam(value = "系统通知id", required = true) @PathVariable("id") Integer id) {
        SystemNotice systemNotice = adminNoticeListService.selectNoticeById(id);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", systemNotice);
    }

    @RequestMapping(value = "/update/notice", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑通知", notes = "编辑通知", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateNotice(@RequestBody SystemNoticeParams systemNoticeParams) {
        int i = adminNoticeListService.updateNotice(systemNoticeParams);
        if (i == 0) {
            return JSONPublicDto.returnErrorData(RetCodeConstants.SAVE_FAILED, "修改失败");
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "修改成功", null);
    }

    @RequestMapping(value = "/delete/notice", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除通知", notes = "删除通知", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteNotice(@ApiParam(value = "系统通知id", required = true) @RequestParam(value = "id", required = true) Integer id) {
        int i = adminNoticeListService.deleteNotice(id);
        if (i == 0) {
            return JSONPublicDto.returnErrorData(RetCodeConstants.DELETE_FAIL, "删除失败");
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除成功", null);
    }

    @RequestMapping(value = "/publish/notice", method = RequestMethod.PUT)
    @ApiOperation(value = "发布通知", notes = "发布通知", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto publishNotice(@ApiIgnore @RequestParam("authUserId") Integer authUserId, @ApiParam(value = "系统消息id", required = true) @RequestParam(value = "id", required = true) Integer id) {
        int i = adminNoticeListService.updatePublishNotice(authUserId, id);
        if (i == 0) {
            return JSONPublicDto.returnErrorData(RetCodeConstants.SAVE_FAILED, "发布失败");
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "发布成功", null);
    }

	/*@RequestMapping(value="/publish/notice",method=RequestMethod.POST)
    @ApiOperation(value="发布通知",notes="发布通知",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONPublicDto publishSystemNotice(){
		PushMessageRequestDto pushMessageRequestDto = new PushMessageRequestDto();
		pushMessageRequestDto.setText("123");
		pushMessageRequestDto.setTitle("123");
		pushMessageRequestDto.setUrl("www.baidu.com");
		GetuiResponseDto result = feignThirdInterfaceService.pushMessageToAllAndroid(pushMessageRequestDto);
		//GetuiResponseDto result = feignThirdInterfaceService.pushMessageToAllIOS(pushMessageRequestDto); 
		return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "发布成功",result);
	}*/

}
