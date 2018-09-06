package com.idream.controller.app;

import com.idream.commons.lib.annotation.SensitiveWordVaild;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.AppCommunityLifeDetailDto;
import com.idream.commons.lib.dto.activity.AppCommunityLifeDto;
import com.idream.commons.lib.dto.activity.AppCommunityLifeParams;
import com.idream.commons.lib.dto.activity.AppLifeIdParam;
import com.idream.commons.lib.dto.activity.AppLifeTypeDto;
import com.idream.commons.lib.dto.activity.AppMyCommunityLifeByTimeDto;
import com.idream.commons.lib.dto.activity.AppThumbUpDetailDto;
import com.idream.service.AppMyNeighborService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description : 邻里动态相关接口
 * @Created by xiaogang on 2018/4/28.
 */
@RestController
@Api(tags = "邻里动态相关接口", description = "AppMyNeighborController")
@RequestMapping(value = "app/myNeighbor")
public class AppMyNeighborController {

    @Autowired
    private AppMyNeighborService appMyNeighborService;

    @RequestMapping(value = "/addMyDynamic", method = RequestMethod.POST)
    @ApiOperation(value = "新增我的动态", notes = "新增我的动态/0000为成功", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @SensitiveWordVaild
    public JSONPublicDto addMyDynamic(@RequestBody JSONPublicParam<AppCommunityLifeParams> appCommunityLifeParams) {
        appMyNeighborService.addMyDynamic(appCommunityLifeParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "发布成功", null);
    }

    @RequestMapping(value = "/deleteMyDynamic", method = RequestMethod.PUT)
    @ApiOperation(value = "删除我的动态", notes = "删除我的动态/(0000为成功,9014为原数据不存在或已删除)", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteMyDynamic(@RequestBody JSONPublicParam<AppLifeIdParam> param) {
        appMyNeighborService.deleteMyDynamic(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除成功", null);
    }

    @RequestMapping(value = "/getMyDynamicList", method = RequestMethod.GET)
    @ApiOperation(value = "查询我的动态列表", notes = "查询我的动态列表")
    public JSONPublicDto<PagesDto<AppMyCommunityLifeByTimeDto>> getMyDynamicList(@NotNull @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
                                                                                 @NotNull @ApiParam(value = "多少行", required = true) @RequestParam(value = "rows") int rows,
                                                                                 @ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", appMyNeighborService.getMyDynamicList(authUserId, page, rows));
    }

    @RequestMapping(value = "/getMyNeighborDynamicList", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户邻友圈动态列表", notes = "查询用户邻友圈动态列表")
    public JSONPublicDto<PagesDto<AppCommunityLifeDto>> getMyNeighborDynamicList(@NotNull @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
                                                                                 @NotNull @ApiParam(value = "多少行", required = true) @RequestParam(value = "rows") int rows,
                                                                                 @ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", appMyNeighborService.getMyNeighborDynamicList(page, rows, authUserId));
    }

    @RequestMapping(value = "/getDynamicDetail", method = RequestMethod.GET)
    @ApiOperation(value = "查询动态详情", notes = "查询动态详情")
    public JSONPublicDto<AppCommunityLifeDetailDto> getDynamicDetail(@NotNull(message = "动态ID") @RequestParam int id,
                                                                     @ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyNeighborService.getDynamicDetail(id, authUserId));
    }

//    @RequestMapping(value = "/getDynamicCommentList",method = RequestMethod.GET)
//    @ApiOperation(value = "查询评论列表", notes = "查询评论列表")
//    public JSONPublicDto<List<AppCommentDto>> getDynamicCommentList(@NotNull(message ="动态ID") @RequestParam int id){
//        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyNeighborService.getDynamicCommentList(id));
//    }
//
//    @RequestMapping(value = "/getDynamicCommentDetail",method = RequestMethod.GET)
//    @ApiOperation(value = "查询评论详情", notes = "查询评论详情")
//    public JSONPublicDto<AppCommentDto> getDynamicCommentDetail(@NotNull(message ="评论ID") @RequestParam int id){
//        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyNeighborService.getDynamicCommentDetail(id));
//    }

    @RequestMapping(value = "/getThumbUpNum", method = RequestMethod.GET)
    @ApiOperation(value = "查询点赞数", notes = "查询点赞数")
    public JSONPublicDto getThumbUpNum(@NotNull(message = "动态ID") @RequestParam int id) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyNeighborService.getThumbUpNum(id));
    }

//    @RequestMapping(value = "/getCommentNum",method = RequestMethod.GET)
//    @ApiOperation(value = "查询评论数", notes = "查询评论数")
//    public JSONPublicDto getCommentNum(@NotNull(message ="动态ID") @RequestParam int id){
//        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyNeighborService.getCommentNum(id));
//    }

    @RequestMapping(value = "/getMsgThumbUpNum", method = RequestMethod.GET)
    @ApiOperation(value = "查询消息内点赞数", notes = "查询消息内点赞数")
    public JSONPublicDto getMsgThumbUpNum(@ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyNeighborService.getMsgThumbUpNum(authUserId));
    }

    @RequestMapping(value = "/getMsgThumbUpList", method = RequestMethod.GET)
    @ApiOperation(value = "查询消息内获赞列表", notes = "查询消息内获赞列表")
    public JSONPublicDto<List<AppThumbUpDetailDto>> getMsgThumbUpList(@ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyNeighborService.getMsgThumbUpList(authUserId));
    }

    @RequestMapping(value = "/addMyLike", method = RequestMethod.POST)
    @ApiOperation(value = "新增点赞", notes = "新增点赞/(0000为成功,7007已点过赞)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addMyLike(@RequestBody JSONPublicParam<AppLifeIdParam> param) {
        appMyNeighborService.addMyLike(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "点赞成功", null);
    }

    @RequestMapping(value = "/deleteMyLike", method = RequestMethod.PUT)
    @ApiOperation(value = "取消点赞", notes = "取消点赞/(0000为成功,7008为未点赞或已取消赞)", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteMyLike(@RequestBody JSONPublicParam<AppLifeIdParam> param) {
        appMyNeighborService.deleteMyLike(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "取消成功", null);
    }

    @RequestMapping(value = "/getLifeType", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有动态类型", notes = "查询所有动态类型", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AppLifeTypeDto>> getLifeType() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyNeighborService.getLifeType());
    }

}
