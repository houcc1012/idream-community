package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.app.CommunityLifeLikeCountDto;
import com.idream.commons.lib.dto.app.LifeLikeResponseDto;
import com.idream.service.CommunityLifeLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by DELL2018-005 on 2018/5/18.
 */
@RestController
@RequestMapping(value = "/app/community")
@Api(tags = "app站内信通知接口", description = "AppSystemNoticeController")
public class AppSystemNoticeController {

    @Autowired
    private CommunityLifeLikeService communityLifeLikeService;

    @RequestMapping(value = "/communitylife/like/notice/check", method = RequestMethod.GET)
    @ApiOperation(value = "邻里生活点赞通知(已查看)", notes = "邻里生活点赞通知(已查看)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<LifeLikeResponseDto>> selectCommunityLifeLikeChecked(@ApiIgnore Integer authUserId) {
        List<LifeLikeResponseDto> data = communityLifeLikeService.selectCommunityLifeLikeChecked(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询已读成功", data);
    }

    @RequestMapping(value = "/communitylife/like/notice/uncheck", method = RequestMethod.GET)
    @ApiOperation(value = "邻里生活点赞通知(未查看)", notes = "邻里生活点赞通知(未查看)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<LifeLikeResponseDto>> selectCommunityLifeLikeUnchecked(@ApiIgnore Integer authUserId) {
        List<LifeLikeResponseDto> data = communityLifeLikeService.selectCommunityLifeLikeUnchecked(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询未读成功", data);
    }

    @RequestMapping(value = "/communitylife/likecount/notice/uncheck", method = RequestMethod.GET)
    @ApiOperation(value = "邻里生活点赞通知(未查看数量)", notes = "邻里生活点赞通知(未查看数量)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<CommunityLifeLikeCountDto> selectCountCommunityLifeLikeUnchecked(@ApiIgnore Integer authUserId) {
        CommunityLifeLikeCountDto communityLifeLikeCountDto = communityLifeLikeService.selectCountCommunityLifeLikeUnchecked(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询未读成功", communityLifeLikeCountDto);
    }

    @RequestMapping(value = "/emptylike", method = RequestMethod.PUT)
    @ApiOperation(value = "清空点赞", notes = "清空点赞", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto emptyLike(@ApiIgnore Integer authUserId) {
        int i = communityLifeLikeService.updateEmptyLike(authUserId);
        if (i != 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "清空点赞成功", null);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.ERROR_EMPTY_LIEK, "清空点赞失败", null);
    }


}
