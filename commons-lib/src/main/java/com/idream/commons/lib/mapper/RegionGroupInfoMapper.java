package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AdminActivityCommunityListRequestDto;
import com.idream.commons.lib.dto.activity.RegionGroupInfoExtention;
import com.idream.commons.lib.dto.admin.*;
import com.idream.commons.lib.dto.app.*;
import com.idream.commons.lib.dto.marketing.BookHouseDto;
import com.idream.commons.lib.dto.marketing.BookHouseParams;
import com.idream.commons.lib.dto.marketing.CommunityDto;
import com.idream.commons.lib.dto.marketing.CommunityInfoParams;
import com.idream.commons.lib.model.RegionGroupInfo;
import com.idream.commons.lib.model.RegionInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface RegionGroupInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RegionGroupInfo record);

    int insertSelective(RegionGroupInfo record);

    RegionGroupInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RegionGroupInfo record);

    int updateByPrimaryKey(RegionGroupInfo record);

    //后台管理 小区列表
    List<SmallCommunityListDto> getSmallCommunityList(SmallCommunityListParams smallCommunityListParams);

    //后台管理 编辑小区数据回显
    @Select("SELECT id communityId,province,province_code provinceCode,city,city_code cityCode,district,district_code districtCode,name communityName,address,longitude,latitude,description,icon FROM region_group_info WHERE id = #{communityId}")
    UpdateSmallCommunityDto selectShowSmallCommunity(@Param("communityId") Integer communityId);

    //后台管理 小区关联高级社区大社区显示
    List<ShowConnectRegionNameDto> getCommunityRegionListByExample(ShowConnectRegionNameParams showConnectRegionNameParams);

    //后台管理 书屋列表
    List<BookHouseListDto> getBookHouseList(BookHouseListParams bookHouseListParams);

    //后台管理 书屋列表 书屋总记录数
    Integer getBookHouseTotal(BookHouseListParams bookHouseListParams);

    //后台管理 新增书屋 校验是否书屋名已存在
    @Select("SELECT * FROM region_group_info WHERE `name` = #{bookHouseName} AND category = 2")
    RegionGroupInfo selectBookHouseByBookHouseName(@Param("bookHouseName") String bookHouseName);

    //后台管理 查询数据库中书屋的数量
    @Select("SELECT count(*) AS count FROM region_group_info WHERE province_code = #{provinceCode} and category = 2")
    Integer selectBookHouseCount(@Param("provinceCode") String provinceCode);

    //后台管理 编辑书屋数据回显
    @Select("SELECT id bookHouseId,province,province_code provinceCode,city,city_code cityCode,district,district_code districtCode,name bookHouseName,address,longitude,latitude,description FROM region_group_info WHERE id = #{bookHouseId}")
    UpdateBookHouseDto selectShowBookHouse(@Param("bookHouseId") Integer bookHouseId);

    //后台管理 高级社区列表
    List<RegionDto> getRegionList(RegionListParams regionListParams);

    //后台管理 高级社区编辑数据回显
    UpdateRegionDto getRegionByRegionId(@Param("regionId") Integer regionId);

    //后台管理 高级社区 小区明细
    List<CommunityDetailDto> getCommunityListByRegionId(@Param("regionId") Integer regionId);

    //后台管理 高级社区 书屋明细
    List<BookHouseDetailDto> getBookHouseListByRegionId(@Param("regionId") Integer regionId);

    //根据省市区模糊查询小区或者书屋
    List<String> getGroupDetail(GroupDetailParams groupDetailParams);

    //根据省市区模糊查询大社区
    List<String> getRegionDetail(RegionDetailParams regionDetailParams);

    List<RegionInfo> selectRegionInfoList(AdminActivityCommunityListRequestDto param);

    List<RegionGroupInfo> selectRegionGroupByRegionId(@Param("regionId") Integer regionId);

    //根据区编码查询书屋信息
    List<BookHouseDto> selectBookHouse(BookHouseParams param);

    //查询当前位置 社区小区信息
    List<CommunitySetParams> getRegionGroupInfoByCityCode(CommunityActivityRequestDto communityActivityRequestDto);

    //通过communityId或者bookHouseId 查询详情
    @Select("SELECT * FROM region_group_info WHERE id = #{id} AND category = #{category}")
    RegionGroupInfo getRegionGroupInfoById(@Param("id") Integer id, @Param("category") Integer category);

    //搜索社区(社区列表)
    List<CommunityInfoResponseDto> selectCommunityInfoListByCommunityName(@Param(value = "cityCode") String cityCode, @Param(value = "communityName") String communityName, @Param(value = "longitude") BigDecimal longitude, @Param(value = "latitude") BigDecimal latitude);

    //附近的社区(社区位置)
    List<NeighborCommunityListResponseDto> getNeighborCommunity(@Param(value = "cityCode") String cityCode, @Param(value = "longitude") BigDecimal longitude, @Param(value = "latitude") BigDecimal latitude);

    //通过regionId,longitude,latitude查询所有的小区信息
    List<CommunityInfoListDto> getCommunityInfoByRegionId(@Param("regionId") Integer regionId, @Param("longitude") BigDecimal longitude, @Param("latitude") BigDecimal latitude);

    List<BookHouseDto> selectBookHouseByCode(@Param("code") String code, @Param("name") String name);

    //管理端 终端 小区明细
    @Select("SELECT a.id communityId,a.name communityName,a.city,a.district,a.address FROM region_group_info a INNER JOIN region_group_relation b ON b.group_id = a.id INNER JOIN region_info c ON c.id = b.region_id WHERE a.category = 1 AND c.type = 2 AND c.id = #{regionId}")
    List<CommunityInfoDetailDto> getCommunityInfoDetail(@Param("regionId") Integer regionId);

    //查询社区信息
    List<CommunityDto> selectCommunityList(CommunityInfoParams param);

    @Select("SELECT DISTINCT a.district_code FROM region_group_info a LEFT JOIN user_manager b ON b.book_id=a.id AND a.category=2 AND b.`status`=1 WHERE b.user_id = #{userId}")
    String selectDistrictByUserId(@Param("userId") Integer userId);

    //查询书屋
    List<RegionGroupInfo> selectBookHouseByCityCode(@Param("cityCode") String cityCode);

    @Select("SELECT a.* FROM region_group_info a LEFT JOIN user_manager b ON a.id = b.book_id AND a.category=2 WHERE b.user_id = #{createId}")
    RegionGroupInfo getBookHouseNameByUserId(@Param("createId") Integer createId);

    List<RegionGroupInfoExtention> selectBookListByBookNameExample(ActivitySearchBookByExampleRequestDto param);

    List<RegionGroupInfo> getBookInfoListByRegionId(@Param("regionIds") List<Integer> regionIds);

    //查询小区信息
    List<CommunityDto> selectSmallCommunityByRegionId(CommunityInfoParams param);
}