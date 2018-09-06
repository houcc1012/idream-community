package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.admin.*;
import com.idream.commons.lib.dto.app.BookHouseListResponseDto;
import com.idream.commons.lib.dto.app.CommunityInfoResponseDto;
import com.idream.commons.lib.dto.app.NeighborCommunityListResponseDto;
import com.idream.commons.lib.dto.marketing.CommunityDto;
import com.idream.commons.lib.dto.marketing.CommunityInfoParams;
import com.idream.commons.lib.model.CommunityInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface CommunityInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityInfo record);

    int insertSelective(CommunityInfo record);

    CommunityInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityInfo record);

    int updateByPrimaryKey(CommunityInfo record);

    //通过城市查询社区信息
    List<ActivityDataDto> getCommunityInfoByCity(String city);

    List<CommunityInfo> listCommunitiesByQuery(String query);

    List<CommunityInfo> selectCommunitiesByLocation(String city);

    List<CommunityInfo> selectCommunitiesByUserId(int userId);

    DistrictCommunityDto getDistrictAndCommunityByActivityId(Integer activityId);

    //根据条件(无社区关联)查询社区列表 
    List<CommunityInfo> selectCommunityListByExample(CommunityBookHouseRequestDto communityBookHouseRequestDto);

    //根据条件(有社区关联)查询社区列表
    List<CommunityBookHouseDto> selectCommunityBookHouseListByExample(CommunityBookHouseRequestDto communityBookHouseRequestDto);

    @Select("SELECT community_name FROM community_info WHERE id = #{communityId}")
    CommunityInfo getCommunityNameByPrimaryKey(Integer communityId);

    //查询社区信息
    List<CommunityDto> selectCommunityList(CommunityInfoParams param);

    CommunityDto selectByCommunityId(Integer id);

    //社区列表空白页查询
    List<CommunityBookHouseDto> selectCommunityBookHouseListAll();

    //编辑社区数据回显
    CommunityBookHouseDto selectCommunityAndBookHouse(@Param("communityId") Integer communityId);

    //编辑社区数据回显
    CommunityBookHouseDto selectCommunityById(Integer communityId);

    @Select("SELECT ad_code adCode FROM community_info WHERE id = #{communityId}")
    String getAdCodeByCommunityId(Integer communityId);

    @Select("SELECT community_name communityName FROM community_info WHERE id = #{communityId}")
    String getCommunityNameByCommunityId(Integer communityId);

    //发现页 活动列表 默认展示所有城市活动
    List<CommunityActivityResponseDto> selectActivityInfoAll();

    List<CommunityActivityResponseDto> getCommunityInfoAndActivityByCity(String city);

    List<CommunityActivityResponseDto> getCommunityInfoAndActivityByCommunityActivityRequestDto(CommunityActivityRequestDto communityActivityRequestDto);

    List<FindUserAsocciationCommunityResponseDto> getCommunityByUserId(Integer userId);

    /**
     * 通过管理员id,查找管理的社区
     *
     * @param managerUserId 管理员id
     *
     * @return List<CommunityInfo>
     */
    List<CommunityInfo> selectCommunitiesByManagerUserId(@Param("managerUserId") Integer managerUserId);

    List<FindUserAsocciationCommunityResponseDto> getPublishActivityCommunityByUserId(@Param("userId") Integer userId);

    //附近的社区(app端)
    List<NeighborCommunityListResponseDto> selectNeighborCommunity(@Param(value = "longitude") BigDecimal longitude, @Param(value = "latitude") BigDecimal latitude);

    //书屋验证 app端
    List<BookHouseListResponseDto> selectCommunityListByBookId(@Param(value = "bookId") Integer bookId);

    List<CommunityInfoResponseDto> selectCommunityInfoListByBookIdAndLocation(@Param(value = "bookId") Integer bookId, @Param(value = "longitude") BigDecimal longitude, @Param(value = "latitude") BigDecimal latitude);

    @Select("select * from community_info")
    List<CommunityInfo> selectAll();

    //社区列表条数
    Integer getTotal(CommunityBookHouseRequestDto communityBookHouseRequestDto);

    List<CommunityActivityResponseDto> getCommunityInfoAndActivityByCityLatitudeLongitude(@Param(value = "city") String city, @Param(value = "longitude") BigDecimal longitude, @Param(value = "latitude") BigDecimal latitude);

    //活动列表
    List<com.idream.commons.lib.dto.admin.ActivityInfoDto> getActivityListByCriteria(ActivityInfoParams activityInfoParams);

    //通过热门城市code查询城市活动列表
    List<com.idream.commons.lib.dto.admin.ActivityInfoDto> getActivityListByHotCity(HotCityRequestParams hotCityParams);

    //手机未给定位权限 全国 活动列表
    List<com.idream.commons.lib.dto.admin.ActivityInfoDto> getActivityListByAllCountry(@Param("userId") Integer userId);
}