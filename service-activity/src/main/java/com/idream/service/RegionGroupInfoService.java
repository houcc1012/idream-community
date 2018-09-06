package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.AdminUserInfoRequestDto;
import com.idream.commons.lib.dto.activity.AdminUserInfoResponseDto;
import com.idream.commons.lib.dto.admin.*;
import com.idream.commons.lib.dto.app.CommunityActivityListResponseDto;
import com.idream.commons.lib.dto.app.CommunityActivityRequestDto;
import com.idream.commons.lib.dto.marketing.AdminAwardPoolDto;
import com.idream.commons.lib.dto.marketing.IntegrationPoolDto;
import com.idream.commons.lib.model.ActivityType;

import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/6/13 19:14
 */
public interface RegionGroupInfoService {

    //后台管理 小区列表查询
    PagesDto<SmallCommunityListDto> getCommunityList(SmallCommunityListParams smallCommunityListParams);

    //后台管理 新增小区
    int addSmallCommunity(AddSmallCommunityParams addSmallCommunityParams);

    //后台管理 编辑小区
    int updateSmallCommunity(UpdateSmallCommunityParams updateSmallCommunityParams);

    //后台管理 编辑小区数据回显
    UpdateSmallCommunityDto selectShowSmallCommunity(Integer communityId);

    //后台管理 关联社区 社区数据回显
    List<ShowConnectRegionNameDto> getCommunityRegionListByExample(ShowConnectRegionNameParams showConnectRegionNameParams);

    //后台管理 小区关联社区
    int addConnectRegionToCommunity(ConnectRegionParams connectRegionParams);

    //后台管理 书屋列表
    PagesDto<BookHouseListDto> getBookHouseList(BookHouseListParams bookHouseListParams);

    //后台管理 新增书屋
    int addBookHouse(AddBookHouseParams addBookHouseParams);

    //后台管理 编辑书屋回显
    UpdateBookHouseDto selectShowBookHouse(Integer bookHouseId);

    //后台管理 编辑书屋
    int updateBookHouse(UpdateBookHouseParams updateBookHouseParams);

    //后台管理 高级社区列表
    PagesDto<RegionListDto> selectRegionList(RegionListParams regionListParams);

    //后台管理 新增高级社区
    int addRegion(AddRegionParams addRegionParams);

    //后台管理 高级社区编辑数据回显
    UpdateRegionDto selectRegionByRegionId(Integer regionId);

    //后台管理 高级社区编辑
    int updateRegion(UpdateRegionParams updateRegionParams);

    //后台管理 高级社区 小区明细
    List<CommunityDetailDto> selectCommunityListByRegionId(Integer regionId);

    //后台管理 高级社区 小区或书屋和社区取消关联
    int deleteUnfollowRegion(ConnectRegionParams connectRegionParams);

    //后台管理 高级社区 书屋明细
    List<BookHouseDetailDto> selectBookHouseListByRegionId(Integer regionId);

    //根据省市区模糊查询小区或者书屋
    List<String> selectGroupDetail(GroupDetailParams groupDetailParams);

    //根据省市区模糊查询大社区
    List<String> selectRegionDetail(RegionDetailParams regionDetailParams);

    //后台管理 书屋关联社区
    int addConnectRegionToBookHouse(BookConnectRegionParams bookConnectRegionParams);

    //查询所有当前位置的活动,并按照距离来排序
    CommunityActivityListResponseDto selectRegionGroupInfo(CommunityActivityRequestDto communityActivityRequestDto, Integer userId);

    //管理端 终端 我的社区
    List<MyRegionInfoListDto> selectRegionInfoList(Integer bookId);

    //管理端 终端 小区明细
    List<CommunityInfoDetailDto> selectCommunityInfoDetail(Integer regionId);

    //管理端 终端 用户明细
    PagesDto<AdminUserInfoResponseDto> selectUserInfo(AdminUserInfoRequestDto adminUserInfoRequestDto);

    //书屋推广
    PagesDto<BookHousePromotionDto> getBookPromotionList(BookHousePromotionParams bookHousePromotionParams);

    //书屋推广 详细数据
    PagesDto<BookHousePromotionDetailDto> getBookPromotionDetailList(BookHousePromotionDetailParams bookHousePromotionDetailParams);

    //其他推广
    PagesDto<OtherPromotionDto> getOtherPromotionList(OtherPromotionParams otherPromotionParams);

    //新增推广
    int addPromotionTeam(AddPromotionTeamParams addPromotionTeamParams);

    //编辑推广
    int updatePromotionTeam(AddPromotionTeamParams addPromotionTeamParams);

    //其他推广 详细数据
    PagesDto<BookHousePromotionDetailDto> getOtherPromotionDetailList(OtherPromotionDetailParams otherPromotionDetailParams);

    //其他推广编辑修改回显
    ShowOtherPromotionTeamDto getShowPromotionTeam(Integer id);

    //管理端 书屋列表 累计发布活动 活动类型查询查询
    List<ActivityType> getActivityType();

    //后台管理 书屋列表 活动详情
    PagesDto<BookHouseListActivityDetailDto> selectActivityDetailByBookId(BookHouseListActivityDetailParams bookHouseListActivityDetailParams);

    //书屋列表 兑换奖品设置
    PagesDto<AdminAwardPoolDto> getAwardDetailByBookId(AwardDetailParams awardDetailParams);

    PagesDto<IntegrationPoolDto> getIntegrationDetailByBookId(IntegrationDetailParams integrationDetailParams);
}
