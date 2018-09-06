package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.app.*;
import com.idream.commons.lib.dto.app.CommunityActivityRequestDto;
import com.idream.commons.lib.dto.region.UnityBulletinDto;
import com.idream.commons.lib.dto.region.UnityGroupDto;
import com.idream.commons.lib.dto.region.UnityRegionDto;
import com.idream.commons.lib.mapper.HotCityMapper;
import com.idream.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/app/community")
@Api(tags = "app社区相关接口", description = "AppCommunityController")
public class AppCommunityController {

    @Autowired
    private MyCommunityService myCommunityService;
    @Autowired
    private ActivityInfoService activityInfoService;
    @Autowired
    private CommunityInfoService communityInfoService;
    @Autowired
    private HotRegionService hotRegionService;
    @Autowired
    private MyNeighborCommunityService myNeighborCommunityService;
    @Autowired
    private NeighborhoodService neighborhoodService;
    @Autowired
    private AppCommunityService appCommunityService;
    @Autowired
    private HotCityMapper hotCityMapper;
    @Autowired
    private LotteryListService lotteryListService;
    @Autowired
    private CommunityInformationService communityInformationService;
    @Autowired
    private RegionGroupInfoService regionGroupInfoService;
    @Autowired
    private UnityService unityService;

    @RequestMapping(value = "/select/community/activity/list", method = RequestMethod.GET)
    @ApiOperation(value = "(按条件)社区活动列表查询", notes = "(按条件)社区活动列表查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<CommunityActivityListResponseDto> selectCommunityActivityListByCriteria(@ApiIgnore Integer authUserId,
                                                                                                 CommunityActivityRequestDto communityActivityRequestDto) {
        CommunityActivityListResponseDto data = regionGroupInfoService.selectRegionGroupInfo(communityActivityRequestDto, authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/select/region/activitylist", method = RequestMethod.GET)
    @ApiOperation(value = "通过大社区id查询当前大社区下所有的小区活动", notes = "通过大社区id查询当前大社区下所有的小区活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<RegionActivityDto>> selectRegionActivityList(RegionActivityParams regionActivityParams) {
        List<RegionActivityDto> data = activityInfoService.selectActivityInfoByRegionId(regionActivityParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/lottery/list", method = RequestMethod.GET)
    @ApiOperation(value = "社区活动有奖显示", notes = "社区活动有奖显示", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<LotteryListResponseDto>> selectLotteryList(@ApiParam(value = "大社区id", required = true) @RequestParam(value = "regionId") Integer regionId) {
        List<LotteryListResponseDto> data = lotteryListService.selectLotteryList(regionId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/select/mycommunityname", method = RequestMethod.GET)
    @ApiOperation(value = "我的社区(认证社区)的查询显示社区名称接口", notes = "我的社区(认证社区)的查询显示社区名称接口", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<CommunityNameListResponseDto>> selectMyCommunityNameList(@ApiIgnore Integer authUserId, MyCommunityParams myCommunityParams) {
        List<CommunityNameListResponseDto> data = myCommunityService.selectMyCommunityList(authUserId, myCommunityParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/select/mycommunity", method = RequestMethod.GET)
    @ApiOperation(value = "我的社区查询接口(选择我的社区)", notes = "我的社区查询接口(选择我的社区)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<CommunityActivityListResponseDto> selectMyCommunityActivity(@ApiIgnore Integer authUserId, @NotNull(message = "communityId不能为null") @ApiParam(value = "小区id", required = true) @RequestParam(value = "communityId") Integer communityId) {
        CommunityActivityListResponseDto data = activityInfoService.selectCommunityActivityListByCommunityId(authUserId, communityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/search/community", method = RequestMethod.GET)
    @ApiOperation(value = "搜索社区查询", notes = "搜索社区查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<CommunityInfoResponseDto>> selectCommunity(@ApiIgnore Integer authUserId, CommunityListSearchRequestDto communityListSearchRequestDto) {
        PagesDto<CommunityInfoResponseDto> data = communityInfoService.selectCommunityInfoListByCommunityName(authUserId, communityListSearchRequestDto);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/hotcommunity", method = RequestMethod.GET)
    @ApiOperation(value = "热门社区查询", notes = "热门社区查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<HotRegionInfoResponseDto>> selectHotCommunity(PagesParam param) {
        PagesDto<HotRegionInfoResponseDto> data = hotRegionService.selectHotCommunityList(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/myneighborcommunity", method = RequestMethod.GET)
    @ApiOperation(value = "我的社区与附近社区", notes = "我的社区与附近社区", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<MyNeighborCommunityResponseDto> selectMyNeighborCommunity(@ApiIgnore Integer authUserId,
                                                                                   NeighborCommunityRequestDto neighborCommunityRequestDto) {
        MyNeighborCommunityResponseDto data = myNeighborCommunityService.selectMyNeighborCommunity(authUserId, neighborCommunityRequestDto);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/neighbor/{communityId}", method = RequestMethod.GET)
    @ApiOperation(value = "我的邻居列表", notes = "我的邻居列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<NeighborCommunityResponseDto>> selectNeighbor(@ApiParam(value = "社区id", required = true) @PathVariable Integer communityId) {
        List<NeighborCommunityResponseDto> data = neighborhoodService.selectNeighborhood(communityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @ApiOperation(value = "绑定用户认证社区", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public JSONPublicDto<String> addAuth(@RequestBody JSONPublicParam<AppAuthCommunityParams> param) {
        appCommunityService.addUserCommunityRelation(param.getAuthUserInfo().getUserId(), param.getRequestParam());
        return JSONPublicDto.returnSuccessData("认证成功");
    }

    @ApiOperation(value = "查询用户认证社区", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public JSONPublicDto<PagesDto<AppAuthCommunityDto>> getAuth(@ApiIgnore @RequestParam("authUserId") Integer userId, PagesParam param) {
        PagesDto<AppAuthCommunityDto> a = appCommunityService.listAuthCommunity(userId, param);
        return JSONPublicDto.returnSuccessData(a);
    }

    @RequestMapping(value = "/show/city", method = RequestMethod.GET)
    @ApiOperation(value = "城市列表(社区位置)", notes = "城市列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<CityListResponseDto>> selectCityList() {
        List<CityListResponseDto> data = hotCityMapper.selectCityList();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/show/neighborcommunity", method = RequestMethod.GET)
    @ApiOperation(value = "附近的社区(社区位置)", notes = "附近的社区(社区位置)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<List<NeighborCommunityListResponseDto>>> selectNeighborCommunity(NeighborCommunityRequestDto neighborCommunityRequestDto) {
        PagesDto<List<NeighborCommunityListResponseDto>> data = myNeighborCommunityService.selectNeighborCommunity(neighborCommunityRequestDto);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @ApiOperation(value = "开通的城市", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public JSONPublicDto<List<AppCityDto>> openCity() {
        List<AppCityDto> list = appCommunityService.listOpenCity();
        return JSONPublicDto.returnSuccessData(list);
    }

    @ApiOperation(value = "重新认证", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "auth", method = RequestMethod.PUT)
    public JSONPublicDto updateAuth(@RequestBody JSONPublicParam<AppAuthCommunityParams> param) {
        appCommunityService.updateAuthCommunity(param.getAuthUserInfo().getUserId(), param.getRequestParam());
        return JSONPublicDto.returnSuccessData("修改成功");
    }

    @ApiOperation(value = "删除认证", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "auth", method = RequestMethod.DELETE)
    public JSONPublicDto delAuth(@ApiIgnore @RequestParam("authUserId") Integer userId, Integer communityId) {
        appCommunityService.deleteAuthCommunity(userId, communityId);
        return JSONPublicDto.returnSuccessData("删除成功");
    }

    @ApiOperation(value = "书屋编码", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/bookHouse", method = RequestMethod.GET)
    public JSONPublicDto<List<CommunityInfoResponseDto>> bookHouse(AppAuthBookHouseParams param) {
        List<CommunityInfoResponseDto> list = appCommunityService.listCommunitiesByBookCode(param);
        return JSONPublicDto.returnSuccessData(list);
    }

    @RequestMapping(value = "/select/communityindex", method = RequestMethod.GET)
    @ApiOperation(value = "社区指数", notes = "社区指数", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<CommunityIndexDto> selectCommunityIndex(@NotNull(message = "regionId不能为空") @ApiParam(value = "大社区id", required = true) @RequestParam("regionId") Integer regionId) {
        CommunityIndexDto data = communityInformationService.getCommunityIndexById(regionId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/select/neighborcommunity", method = RequestMethod.GET)
    @ApiOperation(value = "附近社区查询接口(选择附近社区)", notes = "附近社区查询接口(选择附近社区)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<CommunityActivityListResponseDto> selectNeighborCommunity(@ApiIgnore Integer authUserId, NeighborRegionParams neighborRegionParams) {
        CommunityActivityListResponseDto data = activityInfoService.selectCommunityActivityListByRegionId(authUserId, neighborRegionParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @ApiOperation(value = "社区公告")
    @RequestMapping(value = "/unity/bulletin", method = RequestMethod.GET)
    public JSONPublicDto<UnityBulletinDto> getBulletin(@ApiParam("社区id") @RequestParam("regionId") Integer regionId) {
        UnityBulletinDto dto = unityService.getRegionBulletin(regionId);
        return JSONPublicDto.returnSuccessData(dto);
    }

    @RequestMapping(value = "/unity/region", method = RequestMethod.GET)
    @ApiOperation(value = "unity社区信息", notes = "unity社区信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<UnityRegionDto>> region(@ApiParam("大社区id") @RequestParam("regionId") Integer regionId) {
        List<UnityRegionDto> data = unityService.getRegionByRegionId(regionId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/unity/building", method = RequestMethod.GET)
    @ApiOperation(value = "unity社区建筑", notes = "unity社区信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<UnityGroupDto>> group(@ApiParam("模型社区id") @RequestParam("unityId") String unityId) {
        List<UnityGroupDto> data = unityService.getRegionGroupByUnityId(unityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", data);
    }

    @RequestMapping(value = "/unity/activity", method = RequestMethod.GET)
    @ApiOperation(value = "根据社区id查询活动", notes = "根据社区id查询活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<ActivityByRegionAndCityCodeResonDto>> getActivityByRegionId(@ApiParam("大社区id") @RequestParam("regionId") Integer regionId) {
        List<ActivityByRegionAndCityCodeResonDto> activityByRegionId = appCommunityService.getActivityByRegionId(regionId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功！", activityByRegionId);
    }

    @RequestMapping(value = "/city", method = RequestMethod.PUT)
    public void updateOpenCity() {
        //fegin调用
        appCommunityService.updateOpenCity();
    }

    //@ApiOperation(value = "热门社区",notes = "热门社区",httpMethod = "PUT",produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/hot/region", method = RequestMethod.PUT)
    public void updateHotRegion() {
        //fegin调用
        appCommunityService.updateHotRegion();
    }
}
