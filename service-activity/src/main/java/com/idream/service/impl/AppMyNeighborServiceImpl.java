package com.idream.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.AdminMyDynamicDto;
import com.idream.commons.lib.dto.activity.AdminMyNeighborDynamicDto;
import com.idream.commons.lib.dto.activity.AdminMyNeighborDynamicParam;
import com.idream.commons.lib.dto.activity.AdminThumbUpDetailDto;
import com.idream.commons.lib.dto.activity.AdminThumbUpParam;
import com.idream.commons.lib.dto.activity.AppCommentDto;
import com.idream.commons.lib.dto.activity.AppCommunityLifeDetailDto;
import com.idream.commons.lib.dto.activity.AppCommunityLifeDto;
import com.idream.commons.lib.dto.activity.AppCommunityLifeParams;
import com.idream.commons.lib.dto.activity.AppDynamicParam;
import com.idream.commons.lib.dto.activity.AppImageParam;
import com.idream.commons.lib.dto.activity.AppLifeIdParam;
import com.idream.commons.lib.dto.activity.AppLifeTypeDto;
import com.idream.commons.lib.dto.activity.AppMyCommunityLifeByTimeDto;
import com.idream.commons.lib.dto.activity.AppNeighborInfoDto;
import com.idream.commons.lib.dto.activity.AppNeighborRequestParams;
import com.idream.commons.lib.dto.activity.AppNewCommunityLifeDto;
import com.idream.commons.lib.dto.activity.AppNewLifeDetailDto;
import com.idream.commons.lib.dto.activity.AppThumbUpDetailDto;
import com.idream.commons.lib.dto.marketing.CommunityDto;
import com.idream.commons.lib.dto.marketing.CommunityInfoParams;
import com.idream.commons.lib.dto.rabbitmq.CommunityLifeSyncDto;
import com.idream.commons.lib.dto.user.UserTagDto;
import com.idream.commons.lib.enums.CommunityEnum;
import com.idream.commons.lib.enums.EventEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.CommunityLife;
import com.idream.commons.lib.model.CommunityLifeImage;
import com.idream.commons.lib.model.CommunityLifeLikeRecord;
import com.idream.commons.lib.model.CommunityLifeTimeLine;
import com.idream.commons.lib.util.PageRowsUtils;
import com.idream.commons.mvc.annotation.Achievement;
import com.idream.rabbitmq.RabbitSendService;
import com.idream.service.AppMyNeighborService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/2.
 */
@Service
public class AppMyNeighborServiceImpl implements AppMyNeighborService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    //默认图片
    private static final String DEFAULT_IMAGE = "community_life_image";
    @Autowired
    private CommunityLifeMapper communityLifeMapper;

    @Autowired
    private CommunityLifeImageMapper communityLifeImageMapper;

    @Autowired
    private CommunityLifeLikeRecordMapper communityLifeLikeRecordMapper;

    @Autowired
    private UserAttentionMapper userAttentionMapper;

    @Autowired
    private ActivityTypeMapper activityTypeMapper;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Autowired
    private CommunityLifeTimeLineMapper communityLifeTimeLineMapper;

    @Autowired
    private AchievementUserMapper achievementUserMapper;

    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;

    @Resource
    private RabbitSendService rabbitSendService;

    @Resource
    private UserManagerMapper userManagerMapper;

    public static int MAX_IMAGE_NUM = 9;

    @Resource
    private RegionGroupInfoMapper regoinGroupInfoMapper;

    /**
     * @param : appCommunityLifeParams
     */
    @Override
    @Achievement(eventType = EventEnum.EventType.DYNAMIC)
    public Integer addMyDynamic(JSONPublicParam<AppCommunityLifeParams> appCommunityLifeParams) {

        Integer userId = appCommunityLifeParams.getAuthUserInfo().getUserId(); //用户ID
        AppCommunityLifeParams params = appCommunityLifeParams.getRequestParam(); //业务参数
        //动态内容
        String content = params.getContent();
        List<AppImageParam> images = params.getImageList();
        //存储动态
        Date date = new Date();
        CommunityLife clife = new CommunityLife();
        clife.setStatus(CommunityEnum.CommunityLifeStatusEnum.NORMAL.getCode());
        //设置隐私级别为默认值1
        clife.setPrivacyLevel(CommunityEnum.PrivacyLevelEnum.OPEN.getCode());
        clife.setCreateTime(date);
        clife.setUpdateTime(date);
        clife.setUserId(userId);
        clife.setContent(content);
        clife.setTypeId(params.getTypeId());
        clife.setFromType(params.getFromType());
        clife.setActivityId(params.getActivityId());
        communityLifeMapper.insert(clife);

        //插入图片数据
        if (CollectionUtils.isNotEmpty(images)) {
            for (AppImageParam image : images) {
                CommunityLifeImage communityLifeImage = new CommunityLifeImage();
                communityLifeImage.setImageUrl(image.getImageUrl());
                if (image.getCoverImg() != null) {
                    communityLifeImage.setCoverImg(image.getCoverImg());
                } else {
                    communityLifeImage.setCoverImg(false);
                }
                communityLifeImage.setLifeId(clife.getId());
                communityLifeImage.setUserId(userId);
                communityLifeImage.setCreateTime(date);
                communityLifeImage.setUpdateTime(date);
                //新增动态图片
                communityLifeImageMapper.insert(communityLifeImage);
            }
        }
        //如果类型是生活则同步到时间线表，活动则不需要
        //if (params.getFromType().equals(CommunityEnum.CommunityLifeFromType.LIFE.getCode())) {
            //将自己发布的同步新增到时间线表
            CommunityLifeTimeLine timeLine = new CommunityLifeTimeLine();
            timeLine.setLifeId(clife.getId());
            timeLine.setUserId(userId);
            timeLine.setCommunityId(null);
            timeLine.setCreateTime(clife.getCreateTime());
            communityLifeTimeLineMapper.insertSelective(timeLine);
            //同步到时间线
            try {
                rabbitSendService.sendCommunityLife(
                        JSON.toJSONString(new CommunityLifeSyncDto(clife.getId(), userId, clife.getPrivacyLevel(), null, date)));
            } catch (Exception e) {
                logger.error("发送同步邻里圈动态时间线消息失败!", e);
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "发布邻里圈失败!");
            }
       // }
        return clife.getId();
    }

    /**
     * 同步邻里圈时间线数据
     *
     * @param lifeId       动态ID
     * @param userId       发布用户ID
     * @param privacyLevel 隐私级别
     * @param communityIds 隐私级别为指定社区是, 指定社区集合
     * @param date         发布时间
     */
    @Override
    public void insertCommunityLifeTimeLine(Integer lifeId, Integer userId, Byte privacyLevel, List<Integer> communityIds, Date date) {
        List<CommunityLifeTimeLine> communityLifeTimeLines = Lists.newArrayList();
        //根据隐私级查询需要同步的数据
        if (CommunityEnum.PrivacyLevelEnum.OPEN.getCode().equals(privacyLevel)) {
            //同一个小社区
            List<Integer> regionIds = userCommunityRelationMapper.selectIdByUserId(userId);
            for (int regionId : regionIds) {
                CommunityLifeTimeLine line = new CommunityLifeTimeLine();
                line.setCreateTime(date);
                line.setCommunityId(regionId);
                line.setLifeId(lifeId);
                communityLifeTimeLines.add(line);
            }
            //关注我的人
            List<Integer> userAttentionIds = userAttentionMapper.selectIdByTargetUserId(userId);
            for (int userAttentionId : userAttentionIds) {
                CommunityLifeTimeLine line = new CommunityLifeTimeLine();
                line.setCreateTime(date);
                line.setUserId(userAttentionId);
                line.setLifeId(lifeId);
                communityLifeTimeLines.add(line);
            }
        }
        if (CollectionUtils.isNotEmpty(communityLifeTimeLines)) {
            //同步时间线表
            communityLifeTimeLineMapper.insertBatch(communityLifeTimeLines);
        }
    }

    /**
     * @param : id
     */
    @Override
    public void deleteMyDynamic(JSONPublicParam<AppLifeIdParam> param) {

        CommunityLife communityLife = new CommunityLife();
        communityLife.setId(param.getRequestParam().getId());
        communityLife.setStatus(CommunityEnum.CommunityLifeStatusEnum.DELETED.getCode());
        int result = communityLifeMapper.updateByPrimaryKeySelective(communityLife);
        communityLifeTimeLineMapper.deleteByLifeId(communityLife.getId());
        if (result == 0) {
            throw new BusinessException(RetCodeConstants.SAVE_FAILED, "删除失败(已删除或者动态不存在)");
        }
    }

    /**
     * @param : authUserId
     * @param : page
     * @param : rows
     */
    @Override
    public PagesDto<AppMyCommunityLifeByTimeDto> getMyDynamicList(int authUserId, int page, int rows) {

        // PageHelper.startPage(page,rows);
        AppNeighborRequestParams params = new AppNeighborRequestParams();
        params.setAuthUserId(authUserId);
        params.setPage(PageRowsUtils.getPage(page, rows));
        params.setRows(rows);
        List<AppMyCommunityLifeByTimeDto> list = communityLifeMapper.getMyDynamicList(params);
        // PageInfo<AppMyCommunityLifeByTimeDto> pageInfo = new PageInfo<>(list);
        int count = communityLifeMapper.getMyDynamicListCount(params);
        return new PagesDto(list, count, page, rows);
    }

    /**
     * @param : appDynamicParam
     */
    @Override
    public PagesDto<AppCommunityLifeDto> getMyNeighborDynamicList(int page, int rows, int userId) {
        AppNeighborRequestParams params = new AppNeighborRequestParams();
        params.setAuthUserId(userId);

        List<AppCommunityLifeDto> list = communityLifeMapper.getMyNeighborDynamicList(params);
        List<AppCommunityLifeDto> dolist = PageRowsUtils.getPageObj(list, page, rows);
        return new PagesDto(dolist, list.size(), page, rows);
    }

    /**
     * @param : id
     */
    @Override
    public AppCommunityLifeDetailDto getDynamicDetail(int id, int userId) {

        AppCommunityLifeDetailDto life = null;
        life = communityLifeMapper.selectById(id, userId);
        if (null != life) {
            List<AppImageParam> images = communityLifeImageMapper.selectByLifeId(id);
            //int count = communityLifeLikeRecordMapper.countByLifeId(id);
            List<AppNeighborInfoDto> usersInfo = communityLifeLikeRecordMapper.selectLikeUsersInfo(id);
            List<UserTagDto> labels = achievementUserMapper.selectAchievementByUserId(life.getUserId()); //UserTagMapper.selectUserTagsByUserId(userId);
            life.setImageList(images);
            life.setThumbupNum(getThumbUpNum(id));
            life.setNeighborInfoList(usersInfo);
            life.setUserLabelList(labels);
        } else {
            throw new BusinessException(RetCodeConstants.SELECT_FAILED, "该动态不存在！");
        }
        return life;
    }

    /**
     * @param : id
     */
    @Override
    public List<AppCommentDto> getDynamicCommentList(int id) {
        return null;
    }

    /**
     * @param : id
     */
    @Override
    public AppCommentDto getDynamicCommentDetail(int id) {

        return null;
    }

    /**
     * @param : id
     */
    @Override
    public String getThumbUpNum(int id) {
        Integer count = communityLifeLikeRecordMapper.countByLifeId(id);
        String res = count.toString();
        if (1000 < count.intValue() && count.intValue() < 10000) {
            res = "1K+";
        }
        if (10000 < count.intValue() && count.intValue() < 100000) {
            res = "1W+";
        }
        if (100000 < count.intValue()) {
            res = "10W+";
        }
        return res;
    }

    /**
     * @param : id
     */
    @Override
    public int getCommentNum(int id) {

        return 0;
    }

    /**
     * @param : userId
     */
    @Override
    public String getMsgThumbUpNum(int userId) {
        Integer count = communityLifeLikeRecordMapper.getCountAllByUserId(userId);
        String res = count.toString();
        if (1000 < count.intValue() && count.intValue() < 10000) {
            res = "1K+";
        }
        if (10000 < count.intValue() && count.intValue() < 100000) {
            res = "1W+";
        }
        if (100000 < count.intValue()) {
            res = "10W+";
        }
        return res;
    }

    /**
     * @param : userId
     */
    @Override
    public List<AppThumbUpDetailDto> getMsgThumbUpList(int userId) {
        List<AppThumbUpDetailDto> list = communityLifeLikeRecordMapper.getMsgThumbUpList(userId);
        for (AppThumbUpDetailDto detail : list) {
            //动态图片列表依次按封面和最先保存为优先级排序
            List<AppImageParam> images = communityLifeImageMapper.selectByLifeId(detail.getLifeId());
            if (images.size() > 0) {
                //设置点赞动态封面
                detail.setFrontCoverImage(images.get(0).getImageUrl());
            }
        }
        return list;
    }

    /**
     * @param : authUserId
     * @param : lifeId
     */
    @Override
    @Achievement(eventType = EventEnum.EventType.LIKE)
    public Integer addMyLike(JSONPublicParam<AppLifeIdParam> param) {
        Integer lifeId = param.getRequestParam().getId();
        Integer userId = param.getAuthUserInfo().getUserId();
        Integer id = communityLifeLikeRecordMapper.selectIdByOtherId(userId, lifeId);
        CommunityLife life = communityLifeMapper.selectByPrimaryKey(lifeId);
        if (null == id) {
            if (null != life) {
                CommunityLifeLikeRecord record = new CommunityLifeLikeRecord();
                Date date = new Date();
                record.setCreateTime(date);
                record.setUpdateTime(date);
                record.setUserId(userId);
                record.setLifeId(lifeId);
                record.setOwnerId(life.getUserId());
                communityLifeLikeRecordMapper.insert(record);
            } else {
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "该动态不存在，请检查");
            }
        } else {
            throw new BusinessException(RetCodeConstants.THUMBUP_ERROR, "已点过赞");
        }
        return lifeId;
    }

    /**
     * @param : authUserId
     * @param : lifeId
     */
    @Override
    public void deleteMyLike(JSONPublicParam<AppLifeIdParam> param) {

        Integer id = communityLifeLikeRecordMapper.selectIdByOtherId(param.getAuthUserInfo().getUserId(), param.getRequestParam().getId());
        int res = 0;
        if (null != id) {
            res = communityLifeLikeRecordMapper.deleteByPrimaryKey(id);
        }
        if (res == 0) {
            throw new BusinessException(RetCodeConstants.OPER_FAIL, "取消失败(未点过赞或者已取消)");
        }
    }

    /**
     * @param :
     */
    @Override
    public List<AppLifeTypeDto> getLifeType() {

        return activityTypeMapper.selectLifeType();
    }

    /**
     * @param : userId
     * @param : page
     * @param : rows
     */
    @Override
    public PagesDto<AdminMyDynamicDto> getMyDynamicByUserId(int userId, int page, int rows) {
        AppNeighborRequestParams params = new AppNeighborRequestParams();
        params.setAuthUserId(userId);
        params.setPage(PageRowsUtils.getPage(page, rows));
        params.setRows(rows);
        List<AdminMyDynamicDto> list = communityLifeMapper.getMyDynamicByUserId(params);
        //PageHelper.startPage(page,rows);
        PageInfo<AdminMyDynamicDto> pageInfo = new PageInfo<>(list);
        int count = communityLifeMapper.getMyDynamicListCount(params);
        return new PagesDto(list, count, page, rows);
    }

    /**
     * @param : param
     */
    @Override
    public PagesDto<AdminMyNeighborDynamicDto> getAllNeighborDynamicList(AdminMyNeighborDynamicParam param) {
        int page = param.getPage();
        int rows = param.getRows();
        List<AdminMyNeighborDynamicDto> list = new ArrayList<>();
        Integer bookId = userManagerMapper.getBookIdByUser(param.getUserId());
        if (bookId == null && param.getType() != null) {
            return new PagesDto(list, 0, page, rows);
        } else {
            param.setBookId(bookId);
            list = communityLifeMapper.getAllNeighborDynamicList(param);
        }
        List<AdminMyNeighborDynamicDto> doList = PageRowsUtils.getPageObj(list, page, rows);
        return new PagesDto(doList, list.size(), page, rows);
    }

    /**
     * @param : id
     * @param : page
     * @param : rows
     */
    @Override
    public PagesDto<AdminThumbUpDetailDto> getThumbUpDetailList(int id, int page, int rows) {

        AdminThumbUpParam param = new AdminThumbUpParam();
        param.setId(id);
        param.setPage(PageRowsUtils.getPage(page, rows));
        param.setRows(rows);
        List<AdminThumbUpDetailDto> list = communityLifeLikeRecordMapper.getThumbUpDetailList(param);
        int count = communityLifeLikeRecordMapper.getThumbUpCount(param);
        // PageHelper.startPage(page,rows);
        PageInfo<AdminThumbUpDetailDto> pageInfo = new PageInfo<>(list);
        return new PagesDto(list, count, page, rows);
    }

    /**
     * @param : id
     * @param : status
     */
    @Override
    public void updateStatus(int id, byte status) {

        CommunityLife life = new CommunityLife();

        life.setId(id);
        life.setStatus(status);
        int result = communityLifeMapper.updateStatus(life);
        if (result == 0) {
            throw new BusinessException(RetCodeConstants.OPER_FAIL, "操作失败(已屏蔽或者不存在该动态)");
        }
    }

    @Override
    public List<CommunityDto> selectSmallCommunityByRegionId(CommunityInfoParams code) {
        return regoinGroupInfoMapper.selectSmallCommunityByRegionId(code);
    }
}
