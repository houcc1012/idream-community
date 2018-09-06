package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.admin.*;
import com.idream.commons.lib.dto.admin.BookHouseListDto;
import com.idream.commons.lib.dto.marketing.AdminAwardPoolDto;
import com.idream.commons.lib.dto.marketing.IntegrationPoolDto;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.model.ActivityType;
import com.idream.service.CommunityInfoService;
import com.idream.service.RegionGroupInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author charles.wei
 */
@RestController
@RequestMapping("/admin/community")
@Api(tags = "社区(管理端)")
public class AdminCommunityController {
    @Autowired
    private CommunityInfoService communityInfoService;
    @Autowired
    private RegionGroupInfoService regionGroupInfoService;

    @ApiOperation(value = "查询所有认证用户")
    @RequestMapping(value = "/authUser", method = RequestMethod.GET)
    public JSONPublicDto<PagesDto<AdminCommunityAuthDto>> authUser(AdminCommunityAuthParams params) {
        PagesDto<AdminCommunityAuthDto> page = communityInfoService.getAuthUser(params);
        return JSONPublicDto.returnSuccessData(page);
    }

    //    @ApiOperation(value = "通过审核")
    @RequestMapping(value = "/authUser/success", method = RequestMethod.PUT)
    public JSONPublicDto authSuccess(@RequestBody JSONPublicParam<AdminCommunityAuthOperParams> param) {
        AdminCommunityAuthOperParams requestParam = param.getRequestParam();
        AuthUserInfo info = param.getAuthUserInfo();
        communityInfoService.updateAuthUserCommunitySuccess(info.getUserId(), requestParam.getAuthId());
        return JSONPublicDto.returnSuccessData("操作成功");
    }

    //    @ApiOperation(value = "审核失败")
    @RequestMapping(value = "/authUser/fail", method = RequestMethod.PUT)
    public JSONPublicDto authFail(@RequestBody JSONPublicParam<AdminCommunityAuthOperParams> param) {
        AdminCommunityAuthOperParams requestParam = param.getRequestParam();
        AuthUserInfo info = param.getAuthUserInfo();
        communityInfoService.updateAuthUserCommunityFail(info.getUserId(), requestParam.getAuthId());
        return JSONPublicDto.returnSuccessData("操作成功");
    }

    @RequestMapping(value = "/small/community/list", method = RequestMethod.GET)
    @ApiOperation(value = "小区列表", notes = "小区列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<SmallCommunityListDto>> selectSmallCommunityList(SmallCommunityListParams smallCommunityListParams) {
        PagesDto<SmallCommunityListDto> data = regionGroupInfoService.getCommunityList(smallCommunityListParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/add/small/community", method = RequestMethod.POST)
    @ApiOperation(value = "新增小区", notes = "新增小区", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addSmallCommunity(@RequestBody AddSmallCommunityParams addSmallCommunityParams) {
        int i = regionGroupInfoService.addSmallCommunity(addSmallCommunityParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "新增小区失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "新增小区成功", null);
        }
    }

    @RequestMapping(value = "/update/small/community", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑小区", notes = "编辑小区", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateSmallCommunity(@RequestBody UpdateSmallCommunityParams updateSmallCommunityParams) {
        int i = regionGroupInfoService.updateSmallCommunity(updateSmallCommunityParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "编辑小区失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑小区成功", null);
        }
    }

    @RequestMapping(value = "/echo/update/community/{communityId}", method = RequestMethod.GET)
    @ApiOperation(value = "编辑小区数据回显", notes = "编辑小区数据回显", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<UpdateSmallCommunityDto> selectShowSmallCommunity(@ApiParam(value = "小区id") @PathVariable("communityId") Integer communityId) {
        UpdateSmallCommunityDto data = regionGroupInfoService.selectShowSmallCommunity(communityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/connect/community/region", method = RequestMethod.POST)
    @ApiOperation(value = "小区 关联社区", notes = "小区 关联社区", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addConnectRegionToCommunity(@RequestBody ConnectRegionParams connectRegionParams) {
        int i = regionGroupInfoService.addConnectRegionToCommunity(connectRegionParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "关联社区失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "关联社区成功", null);
        }
    }

    @RequestMapping(value = "/show/regionname", method = RequestMethod.POST)
    @ApiOperation(value = "关联社区 社区名称显示", notes = "关联社区 社区名称显示", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<ShowConnectRegionNameDto>> getRegionNameList(@RequestBody ShowConnectRegionNameParams showConnectRegionNameParams) {
        List<ShowConnectRegionNameDto> data = regionGroupInfoService.getCommunityRegionListByExample(showConnectRegionNameParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/show/bookhouse/list", method = RequestMethod.GET)
    @ApiOperation(value = "书屋列表", notes = "书屋列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<BookHouseListDto>> selectBookHouseList(BookHouseListParams bookHouseListParams) {
        PagesDto<BookHouseListDto> data = regionGroupInfoService.getBookHouseList(bookHouseListParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/add/bookhouse", method = RequestMethod.POST)
    @ApiOperation(value = "新增书屋", notes = "新增书屋", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addBookHouse(@RequestBody AddBookHouseParams addBookHouseParams) {
        int i = regionGroupInfoService.addBookHouse(addBookHouseParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "新增书屋失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "新增书屋成功", null);
        }
    }

    @RequestMapping(value = "/update/bookhouse", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑书屋", notes = "编辑书屋", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateBookHouse(@RequestBody UpdateBookHouseParams updateBookHouseParams) {
        int i = regionGroupInfoService.updateBookHouse(updateBookHouseParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "编辑书屋失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑书屋成功", null);
        }
    }

    @RequestMapping(value = "/echo/update/bookhouse/{bookHouseId}", method = RequestMethod.GET)
    @ApiOperation(value = "编辑书屋数据回显", notes = "编辑书屋数据回显", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<UpdateBookHouseDto> updateShowBookHouse(@ApiParam(value = "书屋id") @PathVariable("bookHouseId") Integer bookHouseId) {
        UpdateBookHouseDto data = regionGroupInfoService.selectShowBookHouse(bookHouseId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/show/region/list", method = RequestMethod.GET)
    @ApiOperation(value = "高级社区列表", notes = "高级社区列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<RegionListDto>> selectRegionList(RegionListParams regionListParams) {
        PagesDto<RegionListDto> data = regionGroupInfoService.selectRegionList(regionListParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/add/region", method = RequestMethod.POST)
    @ApiOperation(value = "新增社区", notes = "新增社区", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addRegion(@RequestBody AddRegionParams addRegionParams) {
        int i = regionGroupInfoService.addRegion(addRegionParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "新增社区失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "新增社区成功", null);
        }
    }

    @RequestMapping(value = "/update/region", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑高级社区", notes = "编辑高级社区", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateRegion(@RequestBody UpdateRegionParams updateRegionParams) {
        int i = regionGroupInfoService.updateRegion(updateRegionParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "编辑高级社区失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑高级社区成功", null);
        }
    }

    @RequestMapping(value = "/echo/update/region/{regionId}", method = RequestMethod.GET)
    @ApiOperation(value = "编辑社区数据回显", notes = "编辑社区数据回显", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<UpdateRegionDto> updateShowRegion(@ApiParam(value = "社区id") @PathVariable("regionId") Integer regionId) {
        UpdateRegionDto data = regionGroupInfoService.selectRegionByRegionId(regionId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/show/community/detail/{regionId}", method = RequestMethod.GET)
    @ApiOperation(value = "点击关联小区跳转小区明细", notes = "点击关联小区跳转小区明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<CommunityDetailDto>> selectCommunityDetail(@ApiParam(value = "社区id") @PathVariable("regionId") Integer regionId) {
        List<CommunityDetailDto> data = regionGroupInfoService.selectCommunityListByRegionId(regionId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/unfollow/region", method = RequestMethod.DELETE)
    @ApiOperation(value = "小区 书屋 取消关联社区", notes = "小区 书屋 取消关联社区", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteUnfollowRegion(ConnectRegionParams connectRegionParams) {
        int i = regionGroupInfoService.deleteUnfollowRegion(connectRegionParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "小区或书屋 取消关联社区失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "小区或书屋 取消关联社区成功", null);
        }
    }

    @RequestMapping(value = "/show/bookhouse/detail/{regionId}", method = RequestMethod.GET)
    @ApiOperation(value = "点击关联书屋跳转书屋明细", notes = "点击关联书屋跳转书屋明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<BookHouseDetailDto>> selectBookHouseDetail(@ApiParam(value = "社区id") @PathVariable("regionId") Integer regionId) {
        List<BookHouseDetailDto> data = regionGroupInfoService.selectBookHouseListByRegionId(regionId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/show/group", method = RequestMethod.GET)
    @ApiOperation(value = "根据省市区模糊查询小区或者书屋", notes = "根据省市区模糊查询小区或者书屋", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<String>> selectGroupDetailByAdCode(GroupDetailParams groupDetailParams) {
        List<String> data = regionGroupInfoService.selectGroupDetail(groupDetailParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/show/region", method = RequestMethod.GET)
    @ApiOperation(value = "根据省市区模糊查询大社区", notes = "根据省市区模糊查询大社区", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<String>> selectRegionDetailByAdCode(RegionDetailParams regionDetailParams) {
        List<String> data = regionGroupInfoService.selectRegionDetail(regionDetailParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/connect/bookhouse/region", method = RequestMethod.POST)
    @ApiOperation(value = "书屋 关联社区", notes = "书屋 关联社区", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addConnectRegionToBookHouse(@RequestBody BookConnectRegionParams bookConnectRegionParams) {
        int i = regionGroupInfoService.addConnectRegionToBookHouse(bookConnectRegionParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "关联社区失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "关联社区成功", null);
        }
    }

    @RequestMapping(value = "/show/myRegion/{bookId}", method = RequestMethod.GET)
    @ApiOperation(value = "我的社区", notes = "我的社区", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<MyRegionInfoListDto>> selectMyRegionDetail(@ApiParam(value = "书屋id") @PathVariable("bookId") Integer bookId) {
        List<MyRegionInfoListDto> data = regionGroupInfoService.selectRegionInfoList(bookId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/show/communityInfo/detail/{regionId}", method = RequestMethod.GET)
    @ApiOperation(value = "小区明细", notes = "小区明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<CommunityInfoDetailDto>> selectCommunity(@ApiParam(value = "社区id") @PathVariable("regionId") Integer regionId) {
        List<CommunityInfoDetailDto> data = regionGroupInfoService.selectCommunityInfoDetail(regionId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    /*@RequestMapping(value="/show/activity/detail/{regionId}",method=RequestMethod.GET)
    @ApiOperation(value="活动明细",notes="活动明细",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<>> selectActivityDetail(@ApiParam(value = "社区id") @PathVariable("regionId")Integer regionId){

        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }*/

    @RequestMapping(value = "/select/userinfo", method = RequestMethod.GET)
    @ApiOperation(value = "用户明细", notes = "用户明细(后台管理)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminUserInfoResponseDto>> selectUserInfo(AdminUserInfoRequestDto adminUserInfoRequestDto) {
        PagesDto<AdminUserInfoResponseDto> data = regionGroupInfoService.selectUserInfo(adminUserInfoRequestDto);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/select/bookHousePromotion", method = RequestMethod.GET)
    @ApiOperation(value = "书屋推广", notes = "书屋推广", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<BookHousePromotionDto>> selectBookHousePromotion(BookHousePromotionParams bookHousePromotionParams) {
        PagesDto<BookHousePromotionDto> data = regionGroupInfoService.getBookPromotionList(bookHousePromotionParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/select/bookHousePromotion/detail", method = RequestMethod.GET)
    @ApiOperation(value = "书屋推广 详细数据", notes = "书屋推广 详细数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<BookHousePromotionDetailDto>> selectBookHousePromotionDetail(BookHousePromotionDetailParams bookHousePromotionDetailParams) {
        PagesDto<BookHousePromotionDetailDto> data = regionGroupInfoService.getBookPromotionDetailList(bookHousePromotionDetailParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/select/otherPromotionList", method = RequestMethod.GET)
    @ApiOperation(value = "其他推广", notes = "其他推广", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<OtherPromotionDto>> selectOtherPromotionList(OtherPromotionParams otherPromotionParams) {
        PagesDto<OtherPromotionDto> data = regionGroupInfoService.getOtherPromotionList(otherPromotionParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/add/promotionTeam", method = RequestMethod.POST)
    @ApiOperation(value = "新增推广", notes = "新增推广", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addPromotionTeam(@RequestBody AddPromotionTeamParams addPromotionTeamParams) {
        int i = regionGroupInfoService.addPromotionTeam(addPromotionTeamParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "新增推广失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "新增推广成功", null);
        }
    }

    @RequestMapping(value = "/update/promotionTeam", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑推广", notes = "编辑推广", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updatePromotionTeam(@RequestBody AddPromotionTeamParams addPromotionTeamParams) {
        int i = regionGroupInfoService.updatePromotionTeam(addPromotionTeamParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "编辑推广失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑推广成功", null);
        }
    }

    @RequestMapping(value = "/select/otherPromotionList/detail", method = RequestMethod.GET)
    @ApiOperation(value = "其他推广 详细数据", notes = "其他推广 详细数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<BookHousePromotionDetailDto>> selectOtherPromotionListDetail(OtherPromotionDetailParams otherPromotionDetailParams) {
        PagesDto<BookHousePromotionDetailDto> data = regionGroupInfoService.getOtherPromotionDetailList(otherPromotionDetailParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/select/showPromotionTeam/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "编辑推广回显", notes = "编辑推广回显", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<ShowOtherPromotionTeamDto> selectShowPromotionTeam(@ApiParam(value = "其他推广id") @PathVariable("id") Integer id) {
        ShowOtherPromotionTeamDto data = regionGroupInfoService.getShowPromotionTeam(id);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑推广回显成功", data);
    }

    @RequestMapping(value = "/select/activity/type",method = RequestMethod.GET)
    @ApiOperation(value = "书屋列表 累计发布活动 活动类型",notes = "书屋列表 累计发布活动 活动类型",httpMethod = "GET",produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public JSONPublicDto<List<ActivityType>> selectActivityType(){
        List<ActivityType> data = regionGroupInfoService.getActivityType();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/select/activityDetailByBookId",method = RequestMethod.GET)
    @ApiOperation(value = "书屋列表 累计发布活动 活动详情",notes = "书屋列表 累计发布活动 活动详情",httpMethod = "GET",produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public JSONPublicDto<PagesDto<BookHouseListActivityDetailDto>> selectActivityDetailByBookId(BookHouseListActivityDetailParams bookHouseListActivityDetailParams){
        PagesDto<BookHouseListActivityDetailDto> data = regionGroupInfoService.selectActivityDetailByBookId(bookHouseListActivityDetailParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/select/awardDetailByBookId",method = RequestMethod.GET)
    @ApiOperation(value = "书屋列表 兑换奖品设置",notes = "书屋列表 兑换奖品设置",httpMethod = "GET",produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminAwardPoolDto>> selectAwardDetailByBookId(AwardDetailParams awardDetailParams){
        PagesDto<AdminAwardPoolDto> data = regionGroupInfoService.getAwardDetailByBookId(awardDetailParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/select/integrationDetailByBookId",method = RequestMethod.GET)
    @ApiOperation(value = "书屋列表 抽奖奖品设置",notes = "书屋列表 抽奖奖品设置",httpMethod = "GET",produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public JSONPublicDto<PagesDto<IntegrationPoolDto>> selectIntegrationDetailByBookId(IntegrationDetailParams integrationDetailParams){
        PagesDto<IntegrationPoolDto> data = regionGroupInfoService.getIntegrationDetailByBookId(integrationDetailParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }
}
