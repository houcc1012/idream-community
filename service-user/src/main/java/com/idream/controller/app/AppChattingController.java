package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.app.AddFriendParams;
import com.idream.commons.lib.dto.app.DislikeActivityParams;
import com.idream.commons.lib.dto.app.MyChatDto;
import com.idream.commons.lib.dto.app.NeighborChatDto;
import com.idream.commons.lib.dto.app.NeighborChatParams;
import com.idream.commons.lib.dto.app.SearchAttentionListDto;
import com.idream.commons.lib.dto.app.SearchAttentionListParams;
import com.idream.commons.lib.dto.app.SearchChatListDto;
import com.idream.commons.lib.dto.app.SearchChatListParams;
import com.idream.commons.lib.dto.app.SuggestAttentionDto;
import com.idream.commons.lib.dto.app.SuggestAttentionFriendDto;
import com.idream.commons.lib.dto.app.SuggestAttentionParams;
import com.idream.service.AppChattingService;
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
 * @Author: jeffery
 * @Date: 2018/7/17 10:51
 */
@RestController
@RequestMapping("/app/chat")
@Api(tags = "app端 聊天业务逻辑")
public class AppChattingController {

    @Autowired
    private AppChattingService appChattingService;

    @RequestMapping(value = "/neighbor/activity/chat", method = RequestMethod.GET)
    @ApiOperation(value = "附近活动趣聊", notes = "附近活动趣聊", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<NeighborChatDto>> selectNeighborChat(@ApiIgnore @RequestParam("authUserId") Integer authUserId, NeighborChatParams param) {
        List<NeighborChatDto> data = appChattingService.getNeighborChat(authUserId, param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/phoneList/myChat", method = RequestMethod.GET)
    @ApiOperation(value = "通讯录 我的趣聊", notes = "通讯录 我的趣聊", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<MyChatDto>> selectMyChat(@NotNull(message = "page不能为空") @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
                                                           @NotNull(message = "rows不能为空") @ApiParam(value = "多少行", required = true) @RequestParam(value = "rows") int rows,
                                                           @ApiIgnore @RequestParam("authUserId") Integer authUserId) {
        PagesDto<MyChatDto> data = appChattingService.getMyChatByUserId(page, rows, authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/suggest/attention", method = RequestMethod.GET)
    @ApiOperation(value = "添加朋友 推荐关注", notes = "添加朋友 推荐关注", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<SuggestAttentionDto>> selectSuggestAttention(SuggestAttentionParams suggestAttentionParams) {
        PagesDto<SuggestAttentionDto> data = appChattingService.getSuggestAttentionByUserId(suggestAttentionParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/friendList", method = RequestMethod.GET)
    @ApiOperation(value = "添加朋友 输入昵称或手机号模糊查询", notes = "添加朋友 输入昵称或手机号模糊查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<SuggestAttentionFriendDto>> selectFriendByNickNameOrPhone(@ApiIgnore @RequestParam("authUserId") Integer authUserId, AddFriendParams addFriendParams) {
        PagesDto<SuggestAttentionFriendDto> data = appChattingService.getFriendByNickNameOrPhone(authUserId, addFriendParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/search/chatList", method = RequestMethod.GET)
    @ApiOperation(value = "聊天搜索 趣聊(已加入活动的趣聊)", notes = "聊天搜索 趣聊(已加入活动的趣聊)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<SearchChatListDto>> selectChatList(@ApiIgnore @RequestParam("authUserId") Integer authUserId, SearchChatListParams param) {
        PagesDto<SearchChatListDto> data = appChattingService.getChatList(authUserId, param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/search/attentionList", method = RequestMethod.GET)
    @ApiOperation(value = "聊天搜索 联系人(关注的朋友)", notes = "聊天搜索 联系人(关注的朋友)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<SearchAttentionListDto>> selectAttentionList(@ApiIgnore @RequestParam("authUserId") Integer authUserId, SearchAttentionListParams param) {
        PagesDto<SearchAttentionListDto> data = appChattingService.getAttentionList(authUserId, param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/neighbor/activity/disLike", method = RequestMethod.POST)
    @ApiOperation(value = "附近活动趣聊 不感兴趣", notes = "附近活动趣聊 不感兴趣", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addDisLikeActivity(@RequestBody JSONPublicParam<DislikeActivityParams> param) {
        int i = appChattingService.addDisLikeActivity(param);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SAVE_FAILED, "推荐群聊添加不感兴趣失败...", null);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "将减少推荐类似内容...", null);
    }


}
