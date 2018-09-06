package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.activity.ActivityByRegionAndCityCodeDto;
import com.idream.commons.lib.dto.activity.ActivityByRegionAndCityCodeResonDto;
import com.idream.commons.lib.dto.activity.AppAuthBookHouseParams;
import com.idream.commons.lib.dto.activity.AppAuthCommunityDto;
import com.idream.commons.lib.dto.activity.AppAuthCommunityParams;
import com.idream.commons.lib.dto.activity.AppCityDto;
import com.idream.commons.lib.dto.app.CommunityInfoResponseDto;
import com.idream.commons.lib.enums.CommunityEnum;
import com.idream.commons.lib.enums.EventEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.AchievementInfoMapper;
import com.idream.commons.lib.mapper.AchievementUserMapper;
import com.idream.commons.lib.mapper.ActivityDisplayRelationMapper;
import com.idream.commons.lib.mapper.ActivityInfoMapper;
import com.idream.commons.lib.mapper.BookExtensionMapper;
import com.idream.commons.lib.mapper.CommunityInfoMapper;
import com.idream.commons.lib.mapper.CommunityLifeLikeRecordMapper;
import com.idream.commons.lib.mapper.HotRegionMapper;
import com.idream.commons.lib.mapper.OpenCityMapper;
import com.idream.commons.lib.mapper.RegionGroupInfoMapper;
import com.idream.commons.lib.mapper.RegionGroupRelationMapper;
import com.idream.commons.lib.mapper.RegionInfoMapper;
import com.idream.commons.lib.mapper.UnityGeographyInfoMapper;
import com.idream.commons.lib.mapper.UnityRegionInfoMapper;
import com.idream.commons.lib.mapper.UserCommunityRelationMapper;
import com.idream.commons.lib.mapper.UserCommunityRelationRecordMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.mapper.UserRegionRelationMapper;
import com.idream.commons.lib.mapper.UserTagRelationMapper;
import com.idream.commons.lib.model.BookExtension;
import com.idream.commons.lib.model.HotRegion;
import com.idream.commons.lib.model.RegionGroupInfo;
import com.idream.commons.lib.model.RegionInfo;
import com.idream.commons.lib.model.UserCommunityRelation;
import com.idream.commons.lib.model.UserCommunityRelationRecord;
import com.idream.commons.lib.model.UserRegionRelation;
import com.idream.commons.mvc.annotation.Achievement;
import com.idream.service.AppCommunityService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author charles
 */
@Service
public class AppCommunityServiceImpl implements AppCommunityService {
    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;
    @Autowired
    private CommunityInfoMapper communityInfoMapper;
    @Autowired
    private OpenCityMapper openCityMapper;
    @Autowired
    private UserCommunityRelationRecordMapper userCommunityRelationRecordMapper;
    @Autowired
    private UserTagRelationMapper userTagRelationMapper;
    @Autowired
    private AchievementUserMapper achievementUserMapper;
    @Autowired
    private AchievementInfoMapper achievementInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RegionInfoMapper regionInfoMapper;
    @Autowired
    private UserRegionRelationMapper userRegionRelationMapper;
    @Autowired
    private RegionGroupRelationMapper regionGroupRelationMapper;

    @Autowired
    private ActivityDisplayRelationMapper activityDisplayRelationMapper;
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Autowired
    private RegionGroupInfoMapper regionGroupInfoMapper;
    @Autowired
    private BookExtensionMapper bookExtensionMapper;
    @Autowired
    private HotRegionMapper hotRegionMapper;
    @Autowired
    private CommunityLifeLikeRecordMapper communityLifeLikeRecordMapper;
    @Autowired
    private UnityGeographyInfoMapper unityGeographyInfoMapper;
    @Autowired
    private UnityRegionInfoMapper unityRegionInfoMapper;

    @Override
    @Achievement(eventType = EventEnum.EventType.COMMUNITY_AUTH)
    public void addUserCommunityRelation(Integer userId, AppAuthCommunityParams requestParam) {
        UserCommunityRelation relation = userCommunityRelationMapper.selectByUserIdAndCommunityId(userId, requestParam.getCommunityId());
        if (relation != null) {
            throw new BusinessException(RetCodeConstants.AUTH_COMMUNITY_IS_EXIST, "已认证过该社区");
        }

        int count = userCommunityRelationMapper.countUserOfCommunityByCommunityId(requestParam.getCommunityId());

        relation = new UserCommunityRelation();
        relation.setCommunityId(requestParam.getCommunityId());
        relation.setUserId(userId);
        relation.setLocalNumber(count + 1);
        Date date = new Date();
        relation.setCreateTime(date);
        relation.setUpdateTime(date);
        relation.setUserType(requestParam.getUserType().byteValue());
        relation.setType(CommunityEnum.AuthCommunityAuthType.BASIS.getCode());
        relation.setStatus(CommunityEnum.AuthCommunityStatus.CHECKING.getCode());
        relation.setFromType(requestParam.getAuthType().byteValue());
        userCommunityRelationMapper.insert(relation);

        //添加认证记录
        UserCommunityRelationRecord record = new UserCommunityRelationRecord();
        BeanUtils.copyProperties(relation, record);
        record.setAuthId(relation.getId());
        record.setDeleted(false);
        userCommunityRelationRecordMapper.insert(record);


        Integer communityId = requestParam.getCommunityId();
        //添加认证的大社区
        addUserRegion(userId, communityId);
    }

    private void addUserRegion(Integer userId, Integer communityId) {
        List<RegionInfo> regionInfos = userRegionRelationMapper.selectRegionInfoListByUserId(userId);
        List<Integer> ids = regionGroupRelationMapper.selectRegionIdsByGroupId(communityId);
        boolean b = regionInfos.stream().map(RegionInfo::getId).noneMatch(ids::contains);
        if (b && !ids.isEmpty()) {
            ids.forEach(i -> {
                UserRegionRelation regionRecord = new UserRegionRelation();
                regionRecord.setCreateTime(new Date());
                regionRecord.setUserId(userId);
                regionRecord.setRegionId(i);
                userRegionRelationMapper.insertSelective(regionRecord);
            });

        }
    }

    private void deleteUserRegion(Integer userId) {
        List<RegionInfo> regionInfos = userRegionRelationMapper.selectRegionInfoListByUserId(userId);
        List<RegionInfo> regions = userCommunityRelationMapper.selectAuthRegionByUserId(userId);

        List<Integer> preIds = regionInfos.stream().map(RegionInfo::getId).collect(Collectors.toList());
        List<Integer> nowIds = regions.stream().map(RegionInfo::getId).collect(Collectors.toList());
        if (preIds.size() > nowIds.size()) {
            List<Integer> collect = preIds.stream().filter(i -> !nowIds.contains(i)).collect(Collectors.toList());

            collect.forEach(i -> {
                userRegionRelationMapper.deleteByUserIdAndRegionId(userId, i);
            });

        }
    }

    @Override
    public PagesDto<AppAuthCommunityDto> listAuthCommunity(Integer userId, PagesParam param) {
        PageHelper.startPage(param.getPage(), param.getRows());
        List<UserCommunityRelation> list = userCommunityRelationMapper.selectByUserId(userId);
        List<AppAuthCommunityDto> appAuthCommunityDtos = Lists.newArrayList();
        for(UserCommunityRelation relation : list){
            RegionGroupInfo communityInfo = regionGroupInfoMapper.selectByPrimaryKey(relation.getCommunityId());
            if(communityInfo == null){
                continue;
            }
            AppAuthCommunityDto dto = new AppAuthCommunityDto();
            dto.setAuthType(relation.getFromType().intValue());
            dto.setUserType(relation.getUserType().intValue());
            dto.setCommunityId(relation.getCommunityId());
            dto.setStatus(relation.getStatus().intValue());
            dto.setCommunityName(communityInfo.getName());
            //查询小社区关联的大社区
            List<Integer> regionIds = regionGroupRelationMapper.selectRegionIdsByGroupId(dto.getCommunityId());
            if (CollectionUtils.isNotEmpty(regionIds)) {
                StringBuilder ids = new StringBuilder();
                regionIds.forEach(item -> ids.append(item).append(","));
                ids.deleteCharAt(ids.length() - 1);
                String bookNames = regionGroupRelationMapper.selectBookNamesByRegionIds(ids.toString());
                dto.setBookHouseName(bookNames);
            }
            appAuthCommunityDtos.add(dto);
        }
        PageInfo<AppAuthCommunityDto> info = new PageInfo<>(appAuthCommunityDtos);
        return new PagesDto<>(info.getList(), info.getTotal(), param.getPage(), param.getRows());
    }

    @Override
    public List<AppCityDto> listOpenCity() {
        List<AppCityDto> list = openCityMapper.listOpenCity();
        return list;
    }

    @Override
    public void updateAuthCommunity(Integer userId, AppAuthCommunityParams param) {
        UserCommunityRelation relation = userCommunityRelationMapper.selectByUserIdAndCommunityId(userId, param.getCommunityId());
        if (relation == null) {
            throw new BusinessException(RetCodeConstants.AUTH_COMMUNITY_NO_EXIST, "认证信息不存在");
        }
        relation.setUserType(param.getUserType().byteValue());
        relation.setStatus(CommunityEnum.AuthCommunityStatus.CHECKING.getCode());
        relation.setUpdateTime(new Date());
        userCommunityRelationMapper.updateByPrimaryKey(relation);
    }

    @Override
    public void deleteAuthCommunity(Integer userId, Integer communityId) {
        UserCommunityRelation relation = userCommunityRelationMapper.selectByUserIdAndCommunityId(userId, communityId);
        if (relation == null) {
            throw new BusinessException(RetCodeConstants.AUTH_COMMUNITY_NO_EXIST, "认证信息不存在");
        }
        userCommunityRelationMapper.deleteByPrimaryKey(relation.getId());
        //删除认证的社区记录
        UserCommunityRelationRecord record = new UserCommunityRelationRecord();
        record.setAuthId(relation.getId());
        record.setDeleted(true);
        Date date = new Date();
        record.setUpdateTime(date);
        record.setDeletedTime(date);
        userCommunityRelationRecordMapper.updateDeletedStatusByAuthId(record);
        //删除认证的大社区
        deleteUserRegion(userId);
    }

    @Override
    public List<CommunityInfoResponseDto> listCommunitiesByBookCode(AppAuthBookHouseParams param) {
        BookExtension b = bookExtensionMapper.getBookHouseByCode(param.getCode());
        if (b == null) {
            throw new BusinessException(RetCodeConstants.AUTH_COMMUNITY_BOOKHOUSE_NO_EXIST, "没有这个书屋");
        }
        RegionGroupInfo r = regionGroupInfoMapper.selectByPrimaryKey(b.getBookId());
        //List<Integer> regionIds = regionGroupRelationMapper.selectRegionIdsByGroupId(b.getBookId());
        //List<CommunityInfoResponseDto> communitys = regionIds.stream().flatMap(i -> regionGroupRelationMapper.selectCommunityRegionId(i,param.getLongitude(),param.getLatitude()).stream()).collect(Collectors.toList());
        List<CommunityInfoResponseDto> communitys = regionGroupRelationMapper.selectCommunityRegionId(b.getBookId(), param.getLongitude(), param.getLatitude());
        for (CommunityInfoResponseDto c : communitys) {
            c.setBookId(b.getBookId());
            c.setBookName(r.getName());
        }
        return communitys;
    }


    @Override
    public List<ActivityByRegionAndCityCodeResonDto> getActivityByRegionId(Integer regionId) {

        RegionInfo regionInfo = regionInfoMapper.selectByPrimaryKey(regionId);
        String cityCode = regionInfo.getCityCode();
        ActivityByRegionAndCityCodeDto dto = new ActivityByRegionAndCityCodeDto();
        dto.setCityCode(Integer.parseInt(cityCode));
        dto.setRegionId(regionId);
        return activityDisplayRelationMapper.getActivityIdByRegionIdAndCityCode(dto);


    }

    @Override
    public void updateOpenCity() {
        openCityMapper.deleteAll();
        openCityMapper.insertRefresh();

    }

    @Override
    public void updateHotRegion() {
        //hot_region全部删除
        hotRegionMapper.deleteAll();
        //查询region_info表中的所有regionId
        List<RegionInfo> regionInfoList = regionInfoMapper.getAllRegionInfo();
        Date date = new Date();
        for (RegionInfo regionInfo : regionInfoList) {
            HotRegion hotRegion = new HotRegion();
            Integer regionId = regionInfo.getId();
            hotRegion.setRegionId(regionId);
            //计算active_value
            Integer communityUserCount = activityInfoMapper.getCountCommunityUser(regionId);
            Integer activityCount = activityInfoMapper.getActivityCountByRegionId(regionId);
            Integer activityRecordCount = activityInfoMapper.getCountActivityRecordByRegionId(regionId);
            Integer neighborLifeCount = activityInfoMapper.getCountNeighborLifeByRegionId(regionId);
            Integer lifeLikeRecordCount = communityLifeLikeRecordMapper.getCountLifeLikeByRegionId(regionId);
            Integer activeValue = communityUserCount * 1 + activityCount * 1 + activityRecordCount * 10 + neighborLifeCount * 1 + lifeLikeRecordCount * 1;
            hotRegion.setActiveValue(activeValue);
            hotRegion.setCreateTime(date);
            //插入所有数据
            hotRegionMapper.insertSelective(hotRegion);
        }
    }

}
