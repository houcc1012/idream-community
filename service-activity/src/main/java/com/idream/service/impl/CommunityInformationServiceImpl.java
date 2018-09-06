package com.idream.service.impl;

import com.idream.commons.lib.dto.app.CommunityIndexDto;
import com.idream.commons.lib.mapper.*;
import com.idream.service.CommunityInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: jeffery
 * @Date: 2018/6/5 19:43
 */
@Service
public class CommunityInformationServiceImpl implements CommunityInformationService {

    @Autowired
    private CommunityRegionInfoMapper communityRegionInfoMapper;
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Autowired
    private CommunityInfoMapper communityInfoMapper;
    @Autowired
    private CommunityLifeLikeRecordMapper communityLifeLikeRecordMapper;
    @Autowired
    private RegionInfoMapper regionInfoMapper;

    /**
     * 查询社区指数
     *
     * @param regionId
     */
    @Override
    public CommunityIndexDto getCommunityIndexById(Integer regionId) {
        CommunityIndexDto communityIndexDto = new CommunityIndexDto();
        Integer activityCount = 0;
        Integer activityRecordCount = 0;
        Integer activityCircleCount = 0;
        Integer communityUserCount = 0;
        Integer neighborLifeCount = 0;
        Integer lifeLikeRecordCount = 0;
        //查询该社区中 活动数量
        Integer count1 = activityInfoMapper.getActivityCountByRegionId(regionId);
        activityCount += count1;
        //查询该社区中 活动报名数量统计
        Integer count2 = activityInfoMapper.getCountActivityRecordByRegionId(regionId);
        activityRecordCount += count2;
        //查询该社区中 活动圈数量
        Integer count3 = activityInfoMapper.getCountCommunityLifeByRegionId(regionId);
        activityCircleCount += count3;
        //查询该社区中 社区用户数
        Integer count4 = activityInfoMapper.getCountCommunityUser(regionId);
        communityUserCount += count4;
        //查询该社区中 邻里圈数(动态圈)
        Integer count5 = activityInfoMapper.getCountNeighborLifeByRegionId(regionId);
        neighborLifeCount += count5;
        //查询该社区中 点赞数
        Integer count6 = communityLifeLikeRecordMapper.getCountLifeLikeByRegionId(regionId);
        lifeLikeRecordCount += count6;
        Integer entertainment = 100 + activityCount * 1 + activityRecordCount * 10 + activityCircleCount * 10;
        Integer treasureBox = 100 + communityUserCount * 1;
        Integer activeValue = communityUserCount * 1 + activityCount * 1 + activityRecordCount * 10 + neighborLifeCount * 1 + lifeLikeRecordCount * 1;
        communityIndexDto.setActiveValue(activeValue);
        communityIndexDto.setEntertainment(entertainment);
        communityIndexDto.setTreasureBox(treasureBox);
        return communityIndexDto;
       /*if(regionId != null){
             List<Integer> communityIdList = regionInfoMapper.getListCommunityIdByRegionId(regionId);
        for (Integer cid : communityIdList) {
            //查询该社区中 活动数量
            Integer count1 = activityInfoMapper.getActivityCountByCommunityId(cid);
            activityCount += count1;
            //查询该社区中 活动报名数量统计
            Integer count2 = activityInfoMapper.getCountActivityRecordByCommunityId(cid);
            activityRecordCount += count2;
            //查询该社区中 活动圈数量
            Integer count3 = activityInfoMapper.getCountCommunityLifeByCommunityId(cid);
            activityCircleCount += count3;
            //查询该社区中 社区用户数
            Integer count4 = communityInfoMapper.getCountCommunityUser(cid);
            communityUserCount += count4;
            //查询该社区中 邻里圈数(动态圈)
            Integer count5 = activityInfoMapper.getCountNeighborLifeByCommunityId(cid);
            neighborLifeCount += count5;
            //查询该社区中 点赞数
            Integer count6 = communityLifeLikeRecordMapper.getCountLifeLikeByCommunityId(cid);
            lifeLikeRecordCount += count6;
        }
            Integer entertainment = 100 + activityCount*1 + activityRecordCount*10 + activityCircleCount*10;
            Integer treasureBox = 100 + communityUserCount*1;
            Integer activeValue = communityUserCount*1 + activityCount*1 + activityRecordCount*10 + neighborLifeCount*1 + lifeLikeRecordCount*1;
            communityIndexDto.setActiveValue(activeValue);
            communityIndexDto.setEntertainment(entertainment);
            communityIndexDto.setTreasureBox(treasureBox);
            return communityIndexDto;
        }else{
            //查询该社区中 活动数量
            activityCount = activityInfoMapper.getActivityCountByCommunityId(communityId);
            //查询该社区中 活动报名数量统计
            activityRecordCount = activityInfoMapper.getCountActivityRecordByCommunityId(communityId);
            //查询该社区中 活动圈数量
            activityCircleCount = activityInfoMapper.getCountCommunityLifeByCommunityId(communityId);
            //查询该社区中 社区用户数
            communityUserCount = communityInfoMapper.getCountCommunityUser(communityId);
            //查询该社区中 邻里圈数(动态圈)
            neighborLifeCount = activityInfoMapper.getCountNeighborLifeByCommunityId(communityId);
            //查询该社区中 点赞数
            lifeLikeRecordCount = communityLifeLikeRecordMapper.getCountLifeLikeByCommunityId(communityId);
            Integer entertainment = 100 + activityCount*1 + activityRecordCount*10 + activityCircleCount*10;
            Integer treasureBox = 100 + communityUserCount*1;
            Integer activeValue = communityUserCount*1 + activityCount*1 + activityRecordCount*10 + neighborLifeCount*1 + lifeLikeRecordCount*1;
            communityIndexDto.setActiveValue(activeValue);
            communityIndexDto.setEntertainment(entertainment);
            communityIndexDto.setTreasureBox(treasureBox);
            return communityIndexDto;
        }*/
    }
}
