package com.idream.service.impl;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.idream.commons.lib.base.IntegrationConfigCodeConstans;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.base.SystemConfigCodeConstans;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.admin.ActivityOperateRecordResponseDto;
import com.idream.commons.lib.dto.admin.ActivitySearchBookByExampleRequestDto;
import com.idream.commons.lib.dto.amap.AmapAddress2LocationDto;
import com.idream.commons.lib.dto.rabbitmq.OperateWangyiIMInfoDto;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.DeleteGroupRequestParam;
import com.idream.commons.lib.enums.*;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.*;
import com.idream.commons.lib.util.AmapUtils;
import com.idream.commons.lib.util.DateUtils;
import com.idream.commons.lib.util.PageRowsUtils;
import com.idream.commons.mvc.annotation.ActivityOperate;
import com.idream.rabbitmq.RabbitSendService;
import com.idream.service.ActivityAdminService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author penghekai
 */
@Service
public class ActivityAdminServiceImpl implements ActivityAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityAdminServiceImpl.class);
    private static final int DEFAULT_MANAGER = 2;
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Autowired
    private ActivityTypeMapper activityTypeMapper;
    @Autowired
    private ActivityTaskMapper activityTaskMapper;
    @Autowired
    private ActivityThemeMapper activityThemeMapper;
    @Autowired
    private ActivityMessageMapper activityMessageMapper;
    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;
    @Autowired
    private ActivityInvitationRecordMapper activityInvitationRecordMapper;
    @Autowired
    private ActivityInformationRelationMapper activityInformationRelationMapper;
    @Autowired
    private ActivityTagRelationMapper activityTagRelationMapper;
    @Autowired
    private IntegrationConfigMapper integrationConfigMapper;
    @Autowired
    private UserManagerMapper userManagerMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ActivityJoinRelationMapper activityJoinRelationMapper;
    @Autowired
    private ActivityDisplayRelationMapper activityDisplayRelationMapper;
    @Autowired
    private RabbitSendService rabbitSendService;
    @Autowired
    private RegionGroupInfoMapper regionGroupInfoMapper;
    @Autowired
    private RegionInfoMapper regionInfoMapper;
    @Autowired
    private ActivityOperateRecordMapper activityOperateRecordMapper;
    @Autowired
    private ActivityGroupRelationMapper activityGroupRelationMapper;
    @Autowired
    private WximGroupMapper wximGroupMapper;
    @Autowired
    private CommunityLifeMapper communityLifeMapper;
    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Autowired
    private ActivityTagMapper activityTagMapper;
    @Autowired
    private InformationRuleMapper informationRuleMapper;

    /**
     * 查询所有已发布活动的类型
     */
    @Override
    public List<ActivityType> findPublishedActivityType() {

        List<ActivityType> list = new ArrayList<>();
        //去重查询所有已发布活动类型id
        List<Integer> typeIds = activityInfoMapper.getTypeId();
        for (Integer typeId : typeIds) {
            ActivityType activityType = activityTypeMapper.selectByPrimaryKey(typeId);
            list.add(activityType);
        }
        return list;
    }

    /**
     * 分页查询活动主题列表
     */
    @Override
    public PagesDto<FindThemeResponseDto> findActivityTheme(Integer activityId, Integer page, Integer rows) {

        List<FindThemeResponseDto> list = new ArrayList<>();
        //根据活动id查询该活动下的所有主题
        List<ActivityThemesDto> activityThemes = activityThemeMapper.getActivityThemeByActivityId(activityId);
        for (ActivityThemesDto activityThemesDto : activityThemes) {
            FindThemeResponseDto ftrd = new FindThemeResponseDto();
            ftrd.setThemeId(activityThemesDto.getThemeId());
            //主题图片
            ftrd.setImage(activityThemesDto.getImage());
            //查询活动主题标题和发布者
            ThemePublisherDto themePublisherDto = activityThemeMapper.getTitleAndPublisherByThemeId(activityThemesDto.getThemeId());
            if (themePublisherDto != null) {
                //设置活动标题
                ftrd.setActivityTitle(themePublisherDto.getActivityTitle());
                //设置主题标题
                ftrd.setTitle(themePublisherDto.getTheme());
                //设置主题发布者
                ftrd.setPublisher(themePublisherDto.getPublisher());
            }
            list.add(ftrd);
        }
        List<FindThemeResponseDto> dtoList = PageRowsUtils.getPageObj(list, page, rows);
        return new PagesDto<>(dtoList, list.size(), page, rows);
    }

    /*
     * 新建活动
     */
    @Override
    @ActivityOperate(operateCategory = ActivityEnum.OperateCategory.EDIT, operateType = ActivityEnum.OperateType.CREATE)
    public Integer addActivity(Integer createId, AdminActivityPublishDto aapd, Byte deviceType) {
        // 拼装活动信息
        ActivityInfo activityInfo = convertActivityInfo(aapd);
        if (activityInfo.getStartTime() == null || activityInfo.getEndTime() == null) {
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "活动时间不能为空");
        }
        //社区去重校验
        long count = aapd.getRegionIds().stream().distinct().count();
        if (aapd.getRegionIds().size() != count) {
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "活动社区选择有误");
        }
        //自定义项重复和非空校验
        long count1 = aapd.getCustomInfoList().stream().map(i -> i.getInfoName()).distinct().count();
        if (aapd.getCustomInfoList().size() != count1) {
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "自定义项创建有误");
        }
        //小区活动 校验是否选择了小区
        if(aapd.getLevel() == 4 && CollectionUtils.isEmpty(aapd.getRegionGroupIds())){
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "请选择指定小区");
        }
        Date sysTime = new Date();
        activityInfo.setCreateTime(sysTime);
        activityInfo.setUpdateTime(sysTime);
        //活动创建人id
        activityInfo.setCreateId(createId);
        // 设置活动状态
        if (SystemEnum.ClientChannelEnum.ADMIN_WEB.getCode().equals(deviceType)) {
            activityInfo.setStatus(ActivityEnum.ActivityDBStatus.PASS.getCode());
            //平台创建的活动默认为官方用户 userId=2
            activityInfo.setUserId(DEFAULT_MANAGER);
        } else {
            activityInfo.setStatus(ActivityEnum.ActivityDBStatus.DRAFT.getCode());
            activityInfo.setUserId(createId);
        }
        // 保存活动信息
        int result = activityInfoMapper.insertSelective(activityInfo);
        if (result != 1) {
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "活动创建失败");
        }

        //处理活动展示，参加表 发布到社区
        solveActivityRegionRelation(aapd, activityInfo, deviceType, createId);
        //将信息保存到活动信息关系表中
        insertDefaultActivityInfomationRel(aapd.getDefaultInfoList(), activityInfo.getId(), activityInfo.getCreateTime());
        insertCustomActivityInfomationRel(aapd.getCustomInfoList(),activityInfo.getId(),activityInfo.getCreateTime());
        //将数据插入到活动主题表
        ActivityTheme theme = createActivityTheme(activityInfo);
        //创建打卡任务表
        solveActivityTask(aapd, activityInfo, theme.getId());
        //将标签信息插入到标签表
        insertActivityTagRel(aapd.getTagIds(), activityInfo);
        return activityInfo.getId();

    }

    /**
     * 保存活动标签关联
     *
     * @param tagIds
     * @param activityInfo
     */
    private void insertActivityTagRel(List<Integer> tagIds, ActivityInfo activityInfo) {
        String tagIdStr = tagIds.stream().map(id -> id.toString()).collect(Collectors.joining(","));
        //过滤出包含二级标签下的三级标签
        List<ActivityTag> activityTags = activityTagMapper.selectTagsByTagIds(tagIdStr);
        // 根据标签级别分组
        Map<Integer, List<ActivityTag>> tagMap = activityTags.stream().collect(Collectors.groupingBy(ActivityTag::getLevel));
        if (!tagMap.isEmpty()) {
            // 2级标签
            List<Integer> twoLevelTagId = tagMap.get(2).stream().map(t -> t.getId()).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(twoLevelTagId) && tagMap.size() > 1) {
                // 有2级标签的3级标签
                List<Integer> threeLevelTagId = tagMap.get(3).stream()
                        .filter(t -> twoLevelTagId.contains(t.getPid())).map(t -> t.getId()).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(threeLevelTagId)) {
                    twoLevelTagId.addAll(threeLevelTagId);
                }
            }
            for (Integer tagId : twoLevelTagId) {
                ActivityTagRelation activityTagRelation = new ActivityTagRelation();
                activityTagRelation.setActivityId(activityInfo.getId());
                activityTagRelation.setCreateTime(activityInfo.getCreateTime());
                activityTagRelation.setUpdateTime(activityInfo.getCreateTime());
                activityTagRelation.setTagId(tagId);
                activityTagRelationMapper.addActivityTagRelation(activityTagRelation);
            }
        }else {
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "请选择活动标签");
        }
    }

    /**
     * 保存活动主题
     *
     * @param activityInfo
     */
    private ActivityTheme createActivityTheme(ActivityInfo activityInfo) {
        ActivityTheme theme = new ActivityTheme();
        theme.setActivityId(activityInfo.getId());
        theme.setContent(activityInfo.getContent());
        theme.setTitle(activityInfo.getTitle());
        //1代表默认主题
        theme.setType(ActivityEnum.ActivityThemeTypeEnum.DEFAULT.getCode());
        theme.setCreateTime(activityInfo.getCreateTime());
        theme.setUpdateTime(activityInfo.getCreateTime());
        theme.setUserId(activityInfo.getCreateId());
        theme.setImage(activityInfo.getImage());
        theme.setAddress(activityInfo.getAddress());
        theme.setLongitude(activityInfo.getLongitude());
        theme.setLatitude(activityInfo.getLatitude());
        activityThemeMapper.insertSelective(theme);
        return theme;
    }

    /**
     * 组装活动信息
     *
     * @param aapd
     */
    private ActivityInfo convertActivityInfo(AdminActivityPublishDto aapd) {
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setContent(aapd.getContent());
        activityInfo.setImage(aapd.getImage());
        activityInfo.setTypeId(aapd.getTypeId());
        activityInfo.setSubtitle(aapd.getSubtitle());
        activityInfo.setTitle(aapd.getTitle());
        activityInfo.setCityCode(aapd.getCityCode());
        activityInfo.setCity(aapd.getCity());
        activityInfo.setCount(aapd.getCount());
        //活动类型 默认1官方
        activityInfo.setType(aapd.getType().byteValue());
        activityInfo.setTimeType(aapd.getTimeType().byteValue());
        activityInfo.setDistrict(aapd.getDistrict());
        activityInfo.setDistrictCode(aapd.getDistrictCode());
        if(aapd.getLevel()==2){
            AmapAddress2LocationDto location = AmapUtils.getLocation(aapd.getCity());
            activityInfo.setLatitude(location.getLatitude());
            activityInfo.setLongitude(location.getLongitude());
        }else {
            activityInfo.setLongitude(aapd.getLongitude());
            activityInfo.setLatitude(aapd.getLatitude());
        }
        activityInfo.setAddress(aapd.getAddress());
        activityInfo.setProvince(aapd.getProvince());
        activityInfo.setProvinceCode(aapd.getProvinceCode());
        activityInfo.setLevel(aapd.getLevel());

        // 星期排序
        if (StringUtils.isNotBlank(aapd.getWeek())) {
            String weekStr = Stream.of(aapd.getWeek().split(",")).sorted().collect(Collectors.joining(","));
            aapd.setWeek(weekStr);
            activityInfo.setWeekDay(weekStr);
        }
        try {
            if (ActivityTimeEnum.ActivityTimeRuleStatus.MUTIPLE_TYPE.getCode().equals(aapd.getTimeType())) {
                //type=2 多次活动
                List<MultiDate> collect = aapd.getMultiDates().stream().sorted(Comparator.comparing(i -> i.getDate())).collect(Collectors.toList());
                long count = collect.stream().map(i -> i.getDate()).distinct().count();
                if (collect.size() != count) {
                    throw new BusinessException(RetCodeConstants.ADD_FAIL, "活动时间选择有误");
                }
                aapd.setMultiDates(collect);
                activityInfo.setStartTime(DateUtils.mergeDateTime(collect.get(0).getDate(), collect.get(0).getStartTime()));
                activityInfo.setEndTime(DateUtils.mergeDateTime(collect.get(collect.size() - 1).getDate(), collect.get(collect.size() - 1).getEndTime()));
            } else if(ActivityTimeEnum.ActivityTimeRuleStatus.SINGLE_TYPE.getCode().equals(aapd.getTimeType())){
                //type=1 一次活动
                activityInfo.setStartTime(DateUtils.mergeDateTime(aapd.getStartDate(), aapd.getStartTime()));
                activityInfo.setEndTime(DateUtils.mergeDateTime(aapd.getEndDate(), aapd.getEndTime()));
            } else if(ActivityTimeEnum.ActivityTimeRuleStatus.PERIOD_TYPE.getCode().equals(aapd.getTimeType())){
                //type=3 周期活动
                List<Date> intervalWeekDates = DateUtils.listIntervalWeekDates(aapd.getStartDate(), aapd.getEndDate(), aapd.getWeek());
                if (CollectionUtils.isEmpty(intervalWeekDates)){
                    throw new BusinessException(RetCodeConstants.ADD_FAIL,"该时间段内没有选择的日期");
                }
                Date startTime = DateUtils.mergeDateTime(intervalWeekDates.get(0), aapd.getStartTime());
                Date endTime = DateUtils.mergeDateTime(intervalWeekDates.get(intervalWeekDates.size()-1), aapd.getEndTime());
                activityInfo.setStartTime(startTime);
                activityInfo.setEndTime(endTime);
            }
        } catch (Exception e) {
            LOGGER.error("创建失败:..........", e);
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "请选择正确的活动时间");
        }
        return activityInfo;
    }

    private void solveActivityRegionRelation(AdminActivityPublishDto aapd, ActivityInfo activityInfo, Byte deviceType, Integer userId) {

        //保存活动参加， 显示 关联表
        insertActivityJoinRelAndDisplayRel(aapd, activityInfo);

        //社区或小区活动时
        if (aapd.getLevel() == 3 || aapd.getLevel() == 4) {
            //根据社区id查询所关联的书屋
            List<RegionGroupInfo> bookInfos;
            if (SystemEnum.ClientChannelEnum.ADMIN_WEB.getCode().equals(deviceType)) {
                bookInfos = regionGroupInfoMapper.getBookInfoListByRegionId(aapd.getRegionIds());
            } else {
                bookInfos = userManagerMapper.selectBookInfoByUserId(userId);
            }
            for (RegionGroupInfo book : bookInfos) {
                insertActivityGroupRel(
                        activityInfo.getId(), ActivityGroupTypeEnum.ActivityGroupType.BOOKHOUSE.getCode(),
                        book.getId(), book.getName(), activityInfo.getCreateTime());
            }
            // 活动社区映射
            for (Integer regionId : aapd.getRegionIds()) {
                String regionName = regionInfoMapper.selectByPrimaryKey(regionId).getRegionName();
                insertActivityGroupRel(activityInfo.getId(), ActivityGroupTypeEnum.ActivityGroupType.REGION.getCode(),
                        regionId, regionName, activityInfo.getCreateTime());
            }
            // 活动小区映射
            if (CollectionUtils.isNotEmpty(aapd.getRegionGroupIds())) {
                for (Integer regionGroupId : aapd.getRegionGroupIds()) {
                    String regionGroupName = regionGroupInfoMapper.selectByPrimaryKey(regionGroupId).getName();
                    insertActivityGroupRel(activityInfo.getId(), ActivityGroupTypeEnum.ActivityGroupType.GROUP.getCode(),
                            regionGroupId, regionGroupName, activityInfo.getCreateTime());
                }
            }
        }

    }

    /**
     * 新增 活动组织关系表
     *
     * @param activityId
     * @param groupType
     * @param groupId
     * @param name
     * @param createTime
     */
    private void insertActivityGroupRel(Integer activityId, Byte groupType, Integer groupId, String name, Date createTime) {
        ActivityGroupRelation groupRelation = new ActivityGroupRelation();
        groupRelation.setActivityId(activityId);
        groupRelation.setGroupType(groupType);
        groupRelation.setGroupId(groupId);
        groupRelation.setGroupName(name);
        groupRelation.setCreateTime(createTime);
        activityGroupRelationMapper.insertSelective(groupRelation);
    }

    /**
     * 新增活动参加/ 显示表
     *
     * @param aapd
     * @param activityInfo
     */
    private void insertActivityJoinRelAndDisplayRel(AdminActivityPublishDto aapd, ActivityInfo activityInfo) {

        List<ActivityJoinRelation> activityJoinRelations = Lists.newArrayList();

        List<ActivityDisplayRelation> activityDisplayRelations = Lists.newArrayList();
        // 查询展示时间到活动结束某些天后
        Integer days = systemConfigMapper.selectIntegerValueByConfigCode(SystemConfigCodeConstans.ACTIVITY_SHOW_ADD_DAYS);
        // 指定活动展示的结束时间
        Date endTime = DateUtils.getAfterADate(activityInfo.getEndTime(), days);

        // 组装 活动参加关联表数据
        ActivityJoinRelation joinRelation = new ActivityJoinRelation();
        joinRelation.setActivityId(activityInfo.getId());
        joinRelation.setStartTime(activityInfo.getCreateTime());
        joinRelation.setEndTime(activityInfo.getEndTime());
        joinRelation.setCreateTime(activityInfo.getCreateTime());

        // 组装 活动展示关联表数据
        ActivityDisplayRelation displayRelation = new ActivityDisplayRelation();
        displayRelation.setActivityId(activityInfo.getId());
        displayRelation.setStartTime(activityInfo.getCreateTime());
        displayRelation.setEndTime(endTime);
        displayRelation.setCreateTime(activityInfo.getCreateTime());
        displayRelation.setFromRegionId(null);
        byte levelType = aapd.getLevel();
        //全国活动join_id=86
        if (levelType == 1) {
            //全国活动, 全国可见, 全国可参加
            joinRelation.setJoinId(86);
            joinRelation.setJoinType(ActivityEnum.JoinDisplayType.ALL.getCode());
            activityJoinRelations.add(joinRelation);

            displayRelation.setDisplayId(86);
            displayRelation.setDisplayType(ActivityEnum.JoinDisplayType.ALL.getCode());
            activityDisplayRelations.add(displayRelation);
        } else if (levelType == 2 || levelType == 3) {
            // 城市活动 社区活动 城市可见, 城市可参加
            joinRelation.setJoinId(Integer.parseInt(activityInfo.getCityCode()));
            joinRelation.setJoinType(ActivityEnum.JoinDisplayType.CITY.getCode());
            activityJoinRelations.add(joinRelation);

            displayRelation.setDisplayId(Integer.parseInt(activityInfo.getCityCode()));
            displayRelation.setDisplayType(ActivityEnum.JoinDisplayType.CITY.getCode());
            activityDisplayRelations.add(displayRelation);
        } else if(levelType == 4){
        //小区活动，小区可见，小区可参加
            for (Integer smallRegionId : aapd.getRegionGroupIds()) {
                ActivityJoinRelation ajr = new ActivityJoinRelation();
                BeanUtils.copyProperties(joinRelation, ajr);
                ajr.setJoinId(smallRegionId);
                ajr.setJoinType(ActivityEnum.JoinDisplayType.GROUP.getCode());
                activityJoinRelations.add(ajr);

                ActivityDisplayRelation adr = new ActivityDisplayRelation();
                BeanUtils.copyProperties(displayRelation, adr);
                adr.setDisplayId(smallRegionId);
                adr.setDisplayType(ActivityEnum.JoinDisplayType.GROUP.getCode());
                activityDisplayRelations.add(adr);
            }
        }
        for (ActivityJoinRelation relation : activityJoinRelations) {
            activityJoinRelationMapper.insertSelective(relation);
        }
        for (ActivityDisplayRelation relation : activityDisplayRelations) {
            activityDisplayRelationMapper.insertSelective(relation);
        }
    }

    /**
     * 保存活动打卡记录
     *
     * @param publishDto
     * @param activityInfo
     * @param themeId
     */
    private void solveActivityTask(AdminActivityPublishDto publishDto, ActivityInfo activityInfo, Integer themeId) {
        if (publishDto.getTimeType().equals(ActivityTimeEnum.ActivityTimeRuleStatus.SINGLE_TYPE.getCode())) {
            insertActivityTask(activityInfo, themeId, publishDto.getStartTime(), publishDto.getEndTime());
        } else if (publishDto.getTimeType().equals(ActivityTimeEnum.ActivityTimeRuleStatus.MUTIPLE_TYPE.getCode())) {
            //timeRule==2
            List<MultiDate> multiDates = publishDto.getMultiDates();
            if (multiDates.isEmpty()) {
                throw new BusinessException("9999", "没有选活动时间");
            }
            for (MultiDate multiDate : multiDates) {
                Date startTime = DateUtils.mergeDateTime(multiDate.getDate(), multiDate.getStartTime());
                Date endTime = DateUtils.mergeDateTime(multiDate.getDate(), multiDate.getEndTime());
                insertActivityTask(activityInfo, themeId, startTime, endTime);
            }
        } else {
            List<Date> intervalWeekDates = DateUtils.listIntervalWeekDates(publishDto.getStartDate(), publishDto.getEndDate(), publishDto.getWeek());
            for (Date intervalWeekDate : intervalWeekDates) {
                Date startTime = DateUtils.mergeDateTime(intervalWeekDate, publishDto.getStartTime());
                Date endTime = DateUtils.mergeDateTime(intervalWeekDate, publishDto.getEndTime());
                insertActivityTask(activityInfo, themeId, startTime, endTime);
            }
        }
    }

    /**
     * 保存活动打卡记录
     *
     * @param activityInfo
     * @param themeId
     * @param startTime
     * @param endTime
     */
    private void insertActivityTask(ActivityInfo activityInfo, int themeId, Date startTime, Date endTime) {
        ActivityTask task = new ActivityTask();
        task.setActivityId(activityInfo.getId());
        task.setThemeId(themeId);
        task.setCreateTime(activityInfo.getCreateTime());
        task.setUpdateTime(activityInfo.getCreateTime());
        task.setEnabled(true);
        task.setStatus(BusinessEnum.ActivityTaskStatusEnum.NOT_STARTED.getCode());
        task.setStartTime(startTime);
        task.setEndTime(endTime);
        activityTaskMapper.insertSelective(task);
    }

    /*
     * 编辑活动
     */
    @Override
    @ActivityOperate(operateCategory = ActivityEnum.OperateCategory.EDIT, operateType = ActivityEnum.OperateType.MODIFY)
    public Integer updateActivity(JSONPublicParam<AdminActivityPublishDto> param, Byte deviceType) {
        AdminActivityPublishDto aapd = param.getRequestParam();
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(aapd.getActivityId());
        //判断活动是否发布成功后临时取消，如果发布过报名人数比已报名人数大1
        Integer operateRecord = activityOperateRecordMapper.selectCountOperateRecordByActivityId(
                aapd.getActivityId(),ActivityEnum.OperateCategory.EDIT.getCode(),ActivityEnum.OperateType.UP.getCode());
        //已报名人数
        Integer signNum = userActivityRecordMapper.countActivityRecordByActivityId(aapd.getActivityId());
        if(operateRecord > 0){
            if(aapd.getCount() != 0 && (aapd.getCount() <= signNum)){
                throw new BusinessException(RetCodeConstants.ADD_FAIL, "报名人数不能小于已参加人数");
            }
        }
        //小区活动 校验是否选择了小区
        if(aapd.getLevel() == 4 && CollectionUtils.isEmpty(aapd.getRegionGroupIds())){
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "请选择指定小区");
        }
        //修改活动信息表
        ActivityInfo info = convertActivityInfo(aapd);
        if (info.getStartTime() == null || info.getEndTime() == null) {
            LOGGER.error("未选择活动开始和结束时间");
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "未选择活动开始和结束时间");
        }
        info.setCreateTime(activityInfo.getCreateTime());
        info.setUpdateTime(new Date());
        info.setId(activityInfo.getId());
        info.setUserId(activityInfo.getUserId());
        info.setCreateId(activityInfo.getCreateId());
        if (SystemEnum.ClientChannelEnum.ADMIN_WEB.getCode().equals(deviceType)) {
            if (activityInfo.getStatus().equals(ActivityEnum.ActivityDBStatus.PUTAWAY.getCode())) {
                info.setStatus(ActivityEnum.ActivityDBStatus.UNSHELVE.getCode());
            }
        } else {
            info.setStatus(ActivityEnum.ActivityDBStatus.DRAFT.getCode());
        }
        activityInfoMapper.updateByPrimaryKeySelective(info);

        //处理活动社区关系表
        activityGroupRelationMapper.deleteByActivityId(activityInfo.getId());
        activityDisplayRelationMapper.deleteByActivityId(activityInfo.getId());
        activityJoinRelationMapper.deleteByActivityId(activityInfo.getId());

        solveActivityRegionRelation(aapd, info, deviceType, param.getAuthUserInfo().getUserId());

        //修改活动信息关系表 先删后新增
        activityInformationRelationMapper.deleteActivityInformationRelationByActivityId(aapd.getActivityId());
        insertDefaultActivityInfomationRel(aapd.getDefaultInfoList(), info.getId(), activityInfo.getCreateTime());
        insertCustomActivityInfomationRel(aapd.getCustomInfoList(), info.getId(), activityInfo.getCreateTime());

        activityThemeMapper.deleteActivityThemeByActivityId(aapd.getActivityId());
        ActivityTheme theme = createActivityTheme(info);

        //根据活动id删除打卡任务
        activityTaskMapper.deleteActivityTaskByActivityId(info.getId());
        //处理活动打卡时间表
        solveActivityTask(aapd, info, theme.getId());

        //修改活动标签表
        activityTagRelationMapper.deleteActivityTagRelationByActivityId(aapd.getActivityId());
        insertActivityTagRel(aapd.getTagIds(), info);

        return activityInfo.getId();
    }

    /**
     * 保存活动默认信息关联表
     *
     * @param infoRuleList
     * @param activityId
     * @param createTime
     */
    private void insertDefaultActivityInfomationRel(List<AdminPublishActivityInfoRuleRequestDto> infoRuleList, Integer activityId, Date createTime) {
        List<InformationRule> ruleList = informationRuleMapper.selectInfoListByType(InformationRuleEnum.CollectionType.ACTIVITY.getCode());
        Map<Integer,String> map = new HashMap();
        for (InformationRule rule : ruleList) {
            map.put(rule.getId(),rule.getName());
        }
        for (AdminPublishActivityInfoRuleRequestDto infoRule : infoRuleList) {
            ActivityInformationRelation activityInformationRelation = new ActivityInformationRelation();
            activityInformationRelation.setActivityId(activityId);
            activityInformationRelation.setInfoId(infoRule.getInfoId());
            activityInformationRelation.setInfoName(map.get(infoRule.getInfoId()));
            activityInformationRelation.setCreateTime(createTime);
            activityInformationRelation.setUpdateTime(createTime);
            activityInformationRelationMapper.insertSelective(activityInformationRelation);
        }
    }

    /**
     * 保存活动自定义信息关联表
     *
     * @param infoRuleList
     * @param activityId
     * @param createTime
     */
    private void insertCustomActivityInfomationRel(List<AdminPublishActivityInfoRuleRequestDto> infoRuleList, Integer activityId, Date createTime) {
        for (AdminPublishActivityInfoRuleRequestDto infoRule : infoRuleList) {
            if(StringUtils.isNotBlank(infoRule.getInfoName())){
                ActivityInformationRelation activityInformationRelation = new ActivityInformationRelation();
                activityInformationRelation.setActivityId(activityId);
                activityInformationRelation.setInfoId(infoRule.getInfoId());
                activityInformationRelation.setInfoName(infoRule.getInfoName());
                activityInformationRelation.setCreateTime(createTime);
                activityInformationRelation.setUpdateTime(createTime);
                activityInformationRelationMapper.insertSelective(activityInformationRelation);
            }
        }
    }

    /*
     * 删除活动
     */
    @Override
    @ActivityOperate(operateCategory = ActivityEnum.OperateCategory.EDIT, operateType = ActivityEnum.OperateType.REMOVE)
    public Integer deleteActivity(Integer activityId) {

        Byte status = activityInfoMapper.selectByPrimaryKey(activityId).getStatus();
        if (ActivityEnum.ActivityDBStatus.UNSHELVE.getCode().equals(status) || status.equals(ActivityEnum.ActivityDBStatus.PUBLISHED.getCode())) {
            activityInfoMapper.updateActivityStatus(activityId, ActivityEnum.ActivityDBStatus.DELETE.getCode());
        } else {
            //根据活动id删除相关信息
            try {
                activityInfoMapper.deleteByPrimaryKey(activityId);
                activityDisplayRelationMapper.deleteByActivityId(activityId);
                activityJoinRelationMapper.deleteByActivityId(activityId);
                activityInformationRelationMapper.deleteActivityInformationRelationByActivityId(activityId);
                activityTaskMapper.deleteActivityTaskByActivityId(activityId);
                activityThemeMapper.deleteActivityThemeByActivityId(activityId);
                activityTagRelationMapper.deleteActivityTagRelationByActivityId(activityId);

                //删除活动群聊
                WximGroup wximGroup = wximGroupMapper.selectByActivityId(activityId);
                if(wximGroup != null){
                    OperateWangyiIMInfoDto operateWangyiIMInfoDto = new OperateWangyiIMInfoDto();
                    operateWangyiIMInfoDto.setType(WangYiEnum.OperateWangyiIMType.DELETE_IM_GROUP.getType());
                    DeleteGroupRequestParam param = new DeleteGroupRequestParam();
                    param.setTid(wximGroup.getTid());
                    operateWangyiIMInfoDto.setObj(param);
                    rabbitSendService.sendWangyiIm(JSON.toJSONString(operateWangyiIMInfoDto));
                }
            } catch (Exception e) {
                LOGGER.error("删除失败.............." + e);
                e.printStackTrace();
                throw new BusinessException("删除活动出错");
            }
        }
        return activityId;
    }

    //提交审核
    @Override
    public void updateActivityStatusSubmit(Integer activityId) {
        activityInfoMapper.updateActivityStatus(activityId, ActivityEnum.ActivityDBStatus.SUBMIT.getCode());
    }

    @Override
    public List<ActivityType> getActivityTypeList() {
        return activityTypeMapper.queryActivityType();
    }

    @Override
    public List<AdminActivityStatisticsResponseDto> getActivityStatisticsByActivityId(Integer activityId) {

        AdminActivityStatisticsResponseDto dto = activityInfoMapper.getActivityStatisticsByActivityId(activityId);
        List<AdminActivityStatisticsResponseDto> dtoList = new ArrayList<>();
        if (dto != null) {
            dtoList.add(dto);
        }
        return dtoList;
    }

    /*
     * 发布活动
     */
    @Override
    @ActivityOperate(operateCategory = ActivityEnum.OperateCategory.EDIT, operateType = ActivityEnum.OperateType.UP)
    public Integer updateActivityStatusPutWay(Integer activityId) {

        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        if (activityInfo.getStatus().equals(ActivityEnum.ActivityDBStatus.PASS.getCode())
                || activityInfo.getStatus().equals(ActivityEnum.ActivityDBStatus.UNSHELVE.getCode())) {
            activityInfoMapper.updateActivityStatus(activityId, ActivityEnum.ActivityDBStatus.PUTAWAY.getCode());
        } else {
            throw new BusinessException(RetCodeConstants.UPDATE_ACTIVITY_STATUS_ERROR, "审核通过的活动才能发布");
        }
        return activityId;
    }

    /*
     * 查询主题留言信息
     */
    @Override
    public PagesDto<FindThemeMessageResponseDto> findThemeMessage(Integer themeId, Integer page, Integer rows) {

        List<FindThemeMessageResponseDto> list = new ArrayList<>();
        List<Integer> messageIds = activityMessageMapper.getThemeMessageIds(themeId);
        for (Integer messageId : messageIds) {
            FindThemeMessageResponseDto ftmrd = activityMessageMapper.getMessageById(messageId);
            list.add(ftmrd);
        }
        //封装分页查询返回参数
        List<FindThemeMessageResponseDto> dtoList = PageRowsUtils.getPageObj(list, page, rows);
        return new PagesDto<>(dtoList, list.size(), page, rows);
    }

    /*
     * 创建主题
     */
    @Override
    public void addTheme(JSONPublicParam<AddThemeRequestDto> params) {

        long currentTimeMillis = System.currentTimeMillis();

        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(params.getRequestParam().getActivityId());
        Date startTime = activityInfo.getStartTime();
        Date endTime = activityInfo.getEndTime();
        Byte timeType = activityInfo.getTimeType();

        if (currentTimeMillis < startTime.getTime()) {
            //没有开的活动都可以创建主题
            createTheme(params);
        } else if (currentTimeMillis > startTime.getTime() && currentTimeMillis < endTime.getTime() && !Integer.valueOf(timeType.intValue()).equals(ActivityTimeEnum.ActivityTimeRuleStatus.SINGLE_TYPE.getCode())) {
            //已经开始的, 并且timeType!=1 的可以创建主题
            createTheme(params);
        } else if (currentTimeMillis > endTime.getTime()) {
            //已经结束的活动不能创建主题
            LOGGER.info("已经结束的活动不能创建主题......................");
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "已经结束的活动不能创建主题");
        } else {
            LOGGER.info("进行中的长期活动不能创建主题..........................................");
            throw new BusinessException(RetCodeConstants.ADD_FAIL, "进行中的长期活动不能创建主题");
        }
    }

    private void createTheme(JSONPublicParam<AddThemeRequestDto> params) {
        AuthUserInfo authUserInfo = params.getAuthUserInfo();
        AddThemeRequestDto atrd = params.getRequestParam();
        //创建人id
        atrd.setUserId(authUserInfo.getUserId());
        Integer activityId = atrd.getActivityId();
        ActivityInfo info = activityInfoMapper.selectByPrimaryKey(activityId);
        Date time = new Date();
        ActivityTheme activityTheme = new ActivityTheme();
        activityTheme.setActivityId(atrd.getActivityId());
        activityTheme.setContent(atrd.getContent());
        activityTheme.setCreateTime(time);
        activityTheme.setTitle(atrd.getThemeTitle());
        //1代表默认主题 2代表自定义主题
        activityTheme.setType(ActivityEnum.ActivityThemeTypeEnum.USER_DEFINED.getCode());
        //设置主题封面
        activityTheme.setImage(atrd.getImage());
        activityTheme.setAddress(atrd.getTaskAddress());
        activityTheme.setLongitude(atrd.getLongitude());
        activityTheme.setLatitude(atrd.getLatitude());
        activityTheme.setUpdateTime(time);
        //插入创建人userId
        activityTheme.setUserId(authUserInfo.getUserId());
        activityThemeMapper.insertSelective(activityTheme);
        reflushActivityTask(atrd.getTaskTime(), activityTheme.getId(), atrd.isTaskRecord(), time);
    }

    @Override
    public void updateTheme(JSONPublicParam<FindThemeDetailResponseDto> param) {

        FindThemeDetailResponseDto ftdrd = param.getRequestParam();
        ActivityTheme theme = activityThemeMapper.selectByPrimaryKey(ftdrd.getThemeId());

        Date time = new Date();
        ActivityTheme record = new ActivityTheme();
        record.setId(ftdrd.getThemeId());
        record.setContent(ftdrd.getContent());
        record.setTitle(ftdrd.getThemeTitle());
        record.setUpdateTime(time);
        record.setImage(ftdrd.getImage());
        record.setAddress(ftdrd.getTaskAddress());
        record.setLongitude(ftdrd.getLongitude());
        record.setLatitude(ftdrd.getLatitude());
        activityThemeMapper.updateByPrimaryKeySelective(record);

        reflushActivityTask(ftdrd.getTaskTime(), theme.getId(), ftdrd.getIsTask(), time);
    }

    private void reflushActivityTask(List<Long> taskTimeList, Integer themeId, boolean enabled,
                                     Date time) {
        Integer activityId = activityThemeMapper.selectByPrimaryKey(themeId).getActivityId();
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        Date startDate = activityInfo.getStartTime();
        Date endDate = activityInfo.getEndTime();
        for (Long taskTime : taskTimeList) {
            ActivityTask task = new ActivityTask();
            task.setActivityId(activityId);
            task.setEnabled(enabled);
            task.setThemeId(themeId);

            Date date = new Date(taskTime);
            int i = activityTaskMapper.selectExistByActivityIdAndDate(activityId, date);
            if (i > 0) {
                task.setStartTime(date);
                activityTaskMapper.updateByActivityIdAndStartTime(task);
            } else {
                task.setCreateTime(time);
                task.setUpdateTime(time);
                task.setStatus(1);
                if (activityInfo.getTimeType() != 1) {
                    task.setStartTime(DateUtils.mergeDateTime(date, startDate));
                    task.setEndTime(DateUtils.mergeDateTime(date, endDate));
                } else {
                    task.setStartTime(startDate);
                    task.setEndTime(endDate);
                }
                activityTaskMapper.insert(task);
            }
        }
    }

    /*
     * 分页查看报名明细
     */
    @Override
    public PagesDto<FindRegisterResponseDto> getRegistInfo(Integer activityId, Integer page, Integer rows) {

        List<FindRegisterResponseDto> resultList = Lists.newArrayList();
        List<Integer> userIds = userActivityRecordMapper.getUserIdsFromUserActivityRecordByActivityId(activityId);
        if (CollectionUtils.isNotEmpty(userIds)) {
            for (Integer userId : userIds) {
                FindRegisterResponseDto frrd = userActivityRecordMapper.getRegistInfoByUserId(userId, activityId);
                resultList.add(frrd);
            }
        }
        List<FindRegisterResponseDto> dtoList = PageRowsUtils.getPageObj(resultList, page, rows);
        return new PagesDto<>(dtoList, resultList.size(), page, rows);
    }

    /*
     * 查询邀请明细
     */
    @Override
    public PagesDto<FindInvitationResponseDto> getInvitationInfo(Integer activityId, Integer page, Integer rows) {

        List<FindInvitationResponseDto> list = activityInvitationRecordMapper.getInvitationInfoByActivityId(activityId);
        List<FindInvitationResponseDto> dtoList = PageRowsUtils.getPageObj(list, page, rows);
        return new PagesDto<>(dtoList, list.size(), page, rows);
    }

    /*
     * 设置打卡获得积分规则
     */
    @Override
    public void updateTaskScore(Integer taskScore) {
        integrationConfigMapper.updateValueByCode(IntegrationConfigCodeConstans.ACTIVITY_TASK_SCORE, taskScore);
    }

    /*
     * 查询管理员发布活动明细
     */
    @Override
    public PagesDto<FindPublishedActivityDetailResponseDto> getPublishedActivityDetail(UserPublishActivityRequestDto param) {

        List<FindPublishedActivityDetailResponseDto> list = activityInfoMapper.getPublishedActivityDetailByUserId(param.getUserId());
        //审核状态 2提交审核,3审核通过4上架,5下架,6审核失败,
        //1待审核，2审核成功，3审核失败
        for (FindPublishedActivityDetailResponseDto dto : list) {
            if (dto.getStatus() == 6) {
                dto.setStatus((byte) 3);
            } else if (dto.getStatus() == 2) {
                dto.setStatus((byte) 1);
            } else {
                dto.setStatus((byte) 2);
            }
            //timerule==2
            if (dto.getTimeRule() == 2) {
                List<MultiDate> multiDates = new ArrayList<>();
                List<ActivityTask> taskList = activityTaskMapper.getActivityTaskByActivityId(dto.getActivityId());
                if (taskList.size() != 0) {
                    for (ActivityTask activityTask : taskList) {
                        MultiDate multiDate = new MultiDate();
                        multiDate.setStartTime(activityTask.getStartTime());
                        multiDate.setEndTime(activityTask.getEndTime());
                        multiDates.add(multiDate);
                    }
                    dto.setMultiDate(multiDates);
                }
            }
        }
        List<FindPublishedActivityDetailResponseDto> dtoList = PageRowsUtils.getPageObj(list, param.getPage(), param.getRows());
        return new PagesDto<>(dtoList, list.size(), param.getPage(), param.getRows());
    }

    /*
     * 根据活动id查询活动信息
     */
    @Override
    public AdminActivityDisplayDto getByActivityId(Integer activityId) {

        AdminActivityDisplayDto aadd = new AdminActivityDisplayDto();
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        //查询活动是否有取消记录
        List<ActivityOperateRecordResponseDto> cancelRecordList = activityOperateRecordMapper.getCancelRecordByActivityId(activityId);
        if (cancelRecordList.size() != 0) {
            aadd.setIsCancel((byte) 1);
            aadd.setCancelRecords(cancelRecordList);
        } else {
            aadd.setIsCancel((byte) 0);
            aadd.setCancelRecords(null);
        }
        //查询活动是否有审核记录
        List<ActivityOperateRecordResponseDto> checkRecordList = activityOperateRecordMapper.getCheckRecordByActivityId(activityId);
        if (checkRecordList.size() != 0) {
            aadd.setIsCheck((byte) 1);
            aadd.setCheckRecords(checkRecordList);
        } else {
            aadd.setIsCheck((byte) 0);
            aadd.setCheckRecords(null);
        }
        //查询所有大社区以及包含的所有小社区
        List<RegionInfoExtention> regionInfoExtentionDtoList = new ArrayList<>();
        for (Integer regionId : activityGroupRelationMapper.selectRegionIdsByActivityId(activityId)) {
            RegionInfoExtention regionInfoExtentionDto = new RegionInfoExtention();
            regionInfoExtentionDto.setRegionId(regionId);
            regionInfoExtentionDto.setRegionName(regionInfoMapper.selectByPrimaryKey(regionId).getRegionName());
            List<RegionGroupInfoExtention> groupList = activityGroupRelationMapper.getRegionGroupInfoByRegionId(regionId);
            regionInfoExtentionDto.setRegionGroupInfoList(groupList);
            regionInfoExtentionDtoList.add(regionInfoExtentionDto);
        }
        aadd.setRegionInfoExtentionDtoList(regionInfoExtentionDtoList);

        //所有选中的小区
        List<RegionGroupInfo> regionGroupInfoList = new ArrayList<>();
        List<Integer> regionGroupIdList = activityGroupRelationMapper.selectRegionGroupIdsByActivityId(activityId);
        for (Integer regionGroupId : regionGroupIdList) {
            regionGroupInfoList.add(regionGroupInfoMapper.selectByPrimaryKey(regionGroupId));
        }
        aadd.setRegionGroupSelectList(regionGroupInfoList);

        //已报名活动人数
        aadd.setWillNum(userActivityRecordMapper.countActivityRecordByActivityId(activityId));
        if (activityInfo != null) {
            aadd.setActivityId(activityInfo.getId());
            //活动发布级别
            aadd.setLevel(activityInfo.getLevel());
            aadd.setImage(activityInfo.getImage());
            aadd.setProvince(activityInfo.getProvince());
            aadd.setProvinceCode(activityInfo.getProvinceCode());
            aadd.setCity(activityInfo.getCity());
            aadd.setCityCode(activityInfo.getCityCode());
            aadd.setDistrict(activityInfo.getDistrict());
            aadd.setDistrictCode(activityInfo.getDistrictCode());
            aadd.setSubtitle(activityInfo.getSubtitle());
            aadd.setContent(activityInfo.getContent());
            aadd.setTitle(activityInfo.getTitle());
            aadd.setTypeId(activityInfo.getTypeId());
            aadd.setUserId(activityInfo.getUserId());
            aadd.setStartTime(activityInfo.getStartTime().getTime());
            aadd.setEndTime(activityInfo.getEndTime().getTime());
            aadd.setCount(activityInfo.getCount());
            aadd.setAddress(activityInfo.getAddress());
            aadd.setLatitude(activityInfo.getLatitude());
            aadd.setLongitude(activityInfo.getLongitude());
            aadd.setType(activityInfo.getTimeType().intValue());
            aadd.setCount(activityInfo.getCount());
        }
        List<ActivityInformationRelation> defaultRelationList = activityInformationRelationMapper.getActivityInformationRelationByActivityId(activityId);
        if (CollectionUtils.isNotEmpty(defaultRelationList)) {
            List<AdminPublishActivityInfoRuleRequestDto> defaultInfoList = new ArrayList();
            for (ActivityInformationRelation activityInformationRelation : defaultRelationList) {
                AdminPublishActivityInfoRuleRequestDto dto = new AdminPublishActivityInfoRuleRequestDto();
                dto.setInfoId(activityInformationRelation.getInfoId());
                dto.setInfoName(activityInformationRelation.getInfoName());
                defaultInfoList.add(dto);
            }
            //默认报名设置
            aadd.setDefaultInfoList(defaultInfoList);
        }
        List<ActivityInformationRelation> customRelationList = activityInformationRelationMapper.getCustomInfoListByActivityId(activityId);
        if(CollectionUtils.isNotEmpty(customRelationList)){
            List<AdminPublishActivityInfoRuleRequestDto> customInfoList = new ArrayList();
            for (ActivityInformationRelation activityInformationRelation : customRelationList) {
                AdminPublishActivityInfoRuleRequestDto dto = new AdminPublishActivityInfoRuleRequestDto();
                dto.setInfoId(activityInformationRelation.getInfoId());
                dto.setInfoName(activityInformationRelation.getInfoName());
                customInfoList.add(dto);
            }
            //自定义报名设置
            aadd.setCustomInfoList(customInfoList);
        }

        if (activityInfo.getTimeType() == 1) {
            aadd.setStartTime(activityInfo.getStartTime().getTime());
            aadd.setEndTime(activityInfo.getEndTime().getTime());
        } else if (activityInfo.getTimeType() == 3) {
            aadd.setWeek(activityInfo.getWeekDay());
        } else if (activityInfo.getTimeType() == 2) {
            List<MultiDate> multiDates = new ArrayList<>();
            List<ActivityTask> taskList = activityTaskMapper.getActivityTaskByActivityId(aadd.getActivityId());
            if (taskList.size() != 0) {
                for (ActivityTask activityTask : taskList) {
                    MultiDate multiDate = new MultiDate();
                    multiDate.setStartTime(activityTask.getStartTime());
                    multiDate.setEndTime(activityTask.getEndTime());
                    multiDates.add(multiDate);
                }
                aadd.setMultiDates(multiDates);
            }
        }
        List<Integer> tagIds = activityTagRelationMapper.listActivityTagIds(activityId);
        aadd.setTagIds(tagIds);
        if (tagIds.size() != 0) {
            List<ActivityTag> secondTagSelectedList = Lists.newArrayList();
            List<ActivityTag> thirdTagSelectedList = Lists.newArrayList();
            for (Integer tagId : tagIds) {
                ActivityTag tag = activityTagMapper.selectByPrimaryKey(tagId);
                if (tag.getLevel() == 2) {
                    //已选中的二级标签
                    secondTagSelectedList.add(tag);
                } else {
                    //已选中的三级标签
                    thirdTagSelectedList.add(tag);
                }
            }
            aadd.setSecondTagSelectList(secondTagSelectedList);
            aadd.setThirdTagSelectList(thirdTagSelectedList);
        }
        return aadd;
    }

    @Override
    public PagesDto<FindAllActivityResponseDto> getActivityCount(QueryActivityPage query) {

        List<FindAllActivityResponseDto> infos = activityInfoMapper.selectActivityByQuery(query);
        List<FindAllActivityResponseDto> list = infos.stream().map(i -> {
            if (i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.UNSHELVE.getCode())) {
                i.setStatus(Integer.valueOf(ActivityEnum.ActivityDBStatus.PASS.getCode()));
            } else if (i.getStatus().byteValue() == ActivityEnum.ActivityDBStatus.PUBLISHED.getCode()) {
                i.setStatus(Integer.valueOf(ActivityEnum.ActivityDBStatus.DRAFT.getCode()));
            }

            //过滤掉社区中的书屋
            List<ActivityGroupRelation> regionList = Lists.newArrayList();
            for (ActivityGroupRelation relation : i.getRegions()) {
                //40为社区
                if(relation.getGroupType().equals(ActivityEnum.JoinDisplayType.REGION.getCode())){
                    regionList.add(relation);
                }
            }
            i.setRegions(regionList);
            //查询活动日期,一次,2多次,3周期
            if (i.getTimeRule().equals(ActivityTimeEnum.ActivityTimeRuleStatus.MUTIPLE_TYPE.getCode())) {
                List<MultiDate> multiDates = new ArrayList<>();
                List<ActivityTask> taskList = activityTaskMapper.getActivityTaskByActivityId(i.getActivityId());
                if (taskList.size() != 0) {
                    for (ActivityTask activityTask : taskList) {
                        MultiDate multiDate = new MultiDate();
                        multiDate.setStartTime(activityTask.getStartTime());
                        multiDate.setEndTime(activityTask.getEndTime());
                        multiDates.add(multiDate);
                    }
                    i.setMultiDate(multiDates);
                }
            }
            //活动状态处理 1未发布 2待开始,3进行中,4已结束
            long currentTime = System.currentTimeMillis();
            if (i.getStatus().byteValue() == ActivityEnum.ActivityDBStatus.PUTAWAY.getCode()) {
                if (currentTime < i.getStartTime().getTime()) {
                    i.setActivityStatus(2);
                } else if (i.getEndTime().getTime() < currentTime) {
                    i.setActivityStatus(4);
                } else {
                    i.setActivityStatus(3);
                }
            } else if (i.getStatus().byteValue() == ActivityEnum.ActivityDBStatus.PASS.getCode()) {
                i.setActivityStatus(1);
            }
            i.setThemeCount(activityThemeMapper.countTheme(i.getActivityId()));
            return i;
        }).collect(Collectors.toList());
        List<FindAllActivityResponseDto> dtoList = PageRowsUtils.getPageObj(list, query.getPage(), query.getRows());
        return new PagesDto<>(dtoList, list.size(), query.getPage(), query.getRows());
    }

    @Override
    public PagesDto<ActivityListCheckResponseDto> getCheckActivityList(ActivityListCheckRequestDto param) {

        // 活动统计信息
        List<ActivityListCheckResponseDto> infos = activityInfoMapper.selectActivityCheckListByQuery(param);
        List<ActivityListCheckResponseDto> list = infos.stream().map(i -> {
            if (i.getTimeRule().equals(ActivityTimeEnum.ActivityTimeRuleStatus.MUTIPLE_TYPE.getCode())) {
                List<MultiDate> multiDates = new ArrayList<>();
                List<ActivityTask> taskList = activityTaskMapper.getActivityTaskByActivityId(i.getActivityId());
                if (taskList.size() != 0) {
                    for (ActivityTask activityTask : taskList) {
                        MultiDate multiDate = new MultiDate();
                        multiDate.setStartTime(activityTask.getStartTime());
                        multiDate.setEndTime(activityTask.getEndTime());
                        multiDates.add(multiDate);
                    }
                    i.setMultiDate(multiDates);
                }
            }

            if (i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.RESUBMIT.getCode())) {
                i.setStatus(Integer.valueOf(ActivityEnum.ActivityDBStatus.SUBMIT.getCode()));
            } else if (i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.REFAIL.getCode())) {
                i.setStatus(Integer.valueOf(ActivityEnum.ActivityDBStatus.FAIL.getCode()));
            }
            //数据库状态 1创建完成,2提交审核,3审核通过4上架,5下架,6审核失败'
            //checkStatus 1 待审核 2 审核通过 3 审核未通过
            if (i.getStatus().byteValue() < ActivityEnum.ActivityDBStatus.PASS.getCode()) {
                i.setCheckStatus(1);
            } else if (i.getStatus().byteValue() > ActivityEnum.ActivityDBStatus.UNSHELVE.getCode()) {
                i.setCheckStatus(3);
            } else {
                i.setCheckStatus(2);
            }
            return i;
        }).collect(Collectors.toList());
        //开启分页
        List<ActivityListCheckResponseDto> dtoList = PageRowsUtils.getPageObj(list, param.getPage(), param.getRows());
        return new PagesDto<>(dtoList, list.size(), param.getPage(), param.getRows());
    }

    @Override
    public PreCreateThemeDto getPreCreateThemeDtoByActivityId(Integer activityId) {

        ActivityInfo info = activityInfoMapper.selectByPrimaryKey(activityId);
        PreCreateThemeDto dto = new PreCreateThemeDto();
        if (info != null) {
            ActivityType type = activityTypeMapper.selectByPrimaryKey(info.getTypeId());
            List<Long> dates = Collections.emptyList();
            //type==3
            if (info.getTimeType().equals(ActivityTimeEnum.ActivityTimeRuleStatus.PERIOD_TYPE.getCode().byteValue())) {
                dates = DateUtils.listIntervalWeekDates(new Date(), info.getWeekDay(), 9).stream().filter(l -> l.getTime() < info.getEndTime().getTime()).map(i -> DateUtils.mergeDateTime(i, info.getStartTime())).map(l -> l.getTime()).collect(Collectors.toList());
            } else {
                dates = activityTaskMapper.selectTaskTimeByActivityId(activityId).stream().map(l -> l.getTime()).collect(Collectors.toList());
            }
            dto.setTitle(info.getTitle());
            dto.setActivityType(type.getName());
            dto.setDates(dates);
        }
        return dto;
    }

    @Override
    public List<InformationRule> listInformations() {
        List<InformationRule> infos = informationRuleMapper.selectInfoListByType(InformationRuleEnum.CollectionType.ACTIVITY.getCode());
        return infos;
    }

    @Override
    public List<ActivityManagerDto> listActivityManagerByRegionId(List<Integer> regionIds) {

        if (regionIds.size() != 0) {
            List<UserManager> list = userManagerMapper.selectUserManagerByRegionId(regionIds);
            UserManager e = new UserManager();
            //2代表官方用户
            e.setUserId(DEFAULT_MANAGER);
            list.add(e);
            List<ActivityManagerDto> resultList = list.stream().map(a -> {
                ActivityManagerDto dto = new ActivityManagerDto();
                String realName = userInfoMapper.selectByPrimaryKey(a.getUserId()).getRealName();
                dto.setUserId(a.getUserId());
                dto.setRealName(realName);
                if (a.getUserId() == DEFAULT_MANAGER) {
                    dto.setDefaulted(true);
                }
                return dto;
            }).collect(Collectors.toList());
            return resultList;
        } else {
            List<ActivityManagerDto> resultList = new ArrayList<>();
            ActivityManagerDto dto = new ActivityManagerDto();
            dto.setUserId(DEFAULT_MANAGER);
            dto.setRealName(userInfoMapper.selectByPrimaryKey(dto.getUserId()).getRealName());
            dto.setDefaulted(true);
            resultList.add(dto);
            return resultList;
        }
    }

    @Override
    public PagesDto<FindParticipateActivityDetailResponseDto> getParticipateActivityDetail(Integer userId, Integer page, Integer rows) {

        //查询是否参与了活动
        Integer record = activityMessageMapper.selectUserActivityRecordList(userId);
        if (record == 0) {
            LOGGER.error("该用户没有参加活动记录,params=" + JSON.toJSONString(record));
            throw new BusinessException(RetCodeConstants.ACCOUNT_USER_NOT_ACTIVITY, "该用户没有参加活动记录");
        }
        List<FindParticipateActivityDetailResponseDto> list = activityInfoMapper.participateActivityDetailList(userId);
        List<FindParticipateActivityDetailResponseDto> resultList = list.stream().map(i -> {
            if (i.getTimeRule() == ActivityTimeEnum.ActivityTimeRuleStatus.MUTIPLE_TYPE.getCode().byteValue()) {
                List<MultiDate> multiDates = new ArrayList<>();
                List<ActivityTask> taskList = activityTaskMapper.getActivityTaskByActivityId(i.getActivityId());
                if (taskList.size() != 0) {
                    for (ActivityTask activityTask : taskList) {
                        MultiDate multiDate = new MultiDate();
                        multiDate.setStartTime(activityTask.getStartTime());
                        multiDate.setEndTime(activityTask.getEndTime());
                        multiDates.add(multiDate);
                    }
                    i.setMultiDate(multiDates);
                }
            }
            return i;
        }).collect(Collectors.toList());
        List<FindParticipateActivityDetailResponseDto> dtoList = PageRowsUtils.getPageObj(resultList, page, rows);
        return new PagesDto<>(dtoList, resultList.size(), page, rows);
    }

    @Override
    public FindThemeDetailResponseDto getByThemeId(Integer themeId) {

        FindThemeDetailResponseDto ftdrd = new FindThemeDetailResponseDto();
        ftdrd = activityThemeMapper.getThemeDetailByThemeId(themeId);
        ActivityInfo activityInfo = activityInfoMapper.getActivityTitleAndImageByThemeId(themeId);
        if (ftdrd.getThemeTitle() == null) {
            ftdrd.setThemeTitle(activityInfo.getTitle());
        }
        if (ftdrd.getImage() == null) {
            ftdrd.setImage(activityInfo.getImage());
        }
        List<Date> taskTime = activityThemeMapper.getThemeTaskTimeByThemeId(themeId);
        ftdrd.setTaskTime(taskTime.stream().map(i -> i.getTime()).collect(Collectors.toList()));
        return ftdrd;
    }

    @Override
    public Integer getTaskScore() {
        Integer i = integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.ACTIVITY_TASK_SCORE);
        return i;
    }

    @Override
    public PagesDto<FindActivityMessageResponseDto> findActivityCommunityLife(ActivityAdminCommunityLifeRequestDto requestDto){
        List<FindActivityMessageResponseDto> list =communityLifeMapper.getCommunityLife(requestDto);
        List<FindActivityMessageResponseDto> dtoList = PageRowsUtils.getPageObj(list, requestDto.getPage(), requestDto.getRows());
        return new PagesDto<>(dtoList,list.size(),requestDto.getPage(),requestDto.getRows());
    }

    @Override
    public List<RegionInfo> selectRegionInfoList(AdminActivityCommunityListRequestDto param) {
        return regionGroupInfoMapper.selectRegionInfoList(param);
    }

    @Override
    public List<RegionGroupInfo> selectRegionGroupByRegionId(Integer regionId) {
        return regionGroupInfoMapper.selectRegionGroupByRegionId(regionId);
    }

    @Override
    public Integer operateActivityCancel(AdminActivityOperateRecordRequestDto param) {

        try {
            ActivityOperateRecord record = new ActivityOperateRecord();
            record.setActivityId(param.getActivityId());
            record.setCategory(ActivityEnum.OperateCategory.EDIT.getCode());
            record.setContent(param.getContent());
            record.setCreateTime(new Date());
            record.setUserId(param.getUserId());
            record.setType(ActivityEnum.OperateType.DOWN.getCode());
            activityOperateRecordMapper.insertSelective(record);

            activityInfoMapper.updateActivityStatus(param.getActivityId(), ActivityEnum.ActivityDBStatus.UNSHELVE.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("活动取消失败......", e);
            throw new BusinessException(RetCodeConstants.ACTIVITY_CANCEL_ERROR, "活动取消失败");
        }
        return param.getActivityId();
    }

    @Override
    public void addCreateGroupChat(Integer userId, CreateGroupRequestParam requestParam) {
        Integer activityId = requestParam.getActivityId();
        if (wximGroupMapper.selectByActivityId(activityId) == null) {
            LOGGER.info("创建活动群组.............................: " + JSON.toJSONString(requestParam));
            //创建群聊
            OperateWangyiIMInfoDto operateWangyiIMInfoDto = new OperateWangyiIMInfoDto();
            operateWangyiIMInfoDto.setType(WangYiEnum.OperateWangyiIMType.CTREATE_GROUP.getType());
            CreateGroupRequestParam param = new CreateGroupRequestParam();
            param.setActivityId(activityId);
            param.setTitle(requestParam.getTitle());
            operateWangyiIMInfoDto.setObj(param);
            rabbitSendService.sendWangyiIm(JSON.toJSONString(operateWangyiIMInfoDto));
        } else {
            LOGGER.info("群聊已存在.............................: " + JSON.toJSONString(requestParam));
            throw new BusinessException(RetCodeConstants.IM_GROUP_ALREADY_CREATE, "群聊已存在");
        }
    }

    @Override
    public void updateGroupNameByGroupId(UpdateGroupNameRequestParam params) {

        try {
            LOGGER.info("修改群组名称........................: " + JSON.toJSONString(params));
            //编辑群聊
            Integer activityId = params.getActivityId();
            WximGroup wximGroup = wximGroupMapper.selectByActivityId(activityId);
            if (wximGroup == null) {
                throw new BusinessException(RetCodeConstants.WANGYI_NOGROUP_FAILED, "该活动没有趣聊");
            }
            String groupName = params.getGroupName();
            OperateWangyiIMInfoDto operateWangyiIMInfoDto = new OperateWangyiIMInfoDto();
            operateWangyiIMInfoDto.setType(WangYiEnum.OperateWangyiIMType.UPDATE_GROUP.getType());
            UpdateGroupInfoRequestParam param = new UpdateGroupInfoRequestParam();
            param.setActivityId(activityId);
            param.setGroupName(groupName);
            operateWangyiIMInfoDto.setObj(param);
            rabbitSendService.sendWangyiIm(JSON.toJSONString(operateWangyiIMInfoDto));
        } catch (Exception e) {
            LOGGER.info("修改群组名称失败........................: " + JSON.toJSONString(params));
            throw e;
        }
    }

    @Override
    public UpdateGroupNameRequestParam getGroupInfoByActivityId(Integer activityId) {

        WximGroup wximGroup = wximGroupMapper.selectByActivityId(activityId);
        if (wximGroup == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_NOGROUP_FAILED, "该活动没有趣聊");
        }
        UpdateGroupNameRequestParam dto = new UpdateGroupNameRequestParam();
        dto.setGroupName(wximGroup.getGroupName());
        dto.setActivityId(activityId);
        return dto;
    }

    @Override
    public List<RegionGroupInfoExtention> getBookNameByExample(ActivitySearchBookByExampleRequestDto param) {
        List<RegionGroupInfoExtention> bookList = regionGroupInfoMapper.selectBookListByBookNameExample(param);
        return bookList;
    }

    @Override
    public void updateCommunityLifeStatusShield(Integer lifeId) {
        CommunityLife communityLife = communityLifeMapper.selectByPrimaryKey(lifeId);
        if(communityLife != null){
            communityLife.setStatus(CommunityEnum.CommunityLifeStatusEnum.CLOSED.getCode());
            communityLifeMapper.updateStatus(communityLife);
        }else {
            throw new BusinessException(RetCodeConstants.OPER_FAIL, "操作失败");
        }
    }

    @Override
    public void updateCommunityLifeStatusCancelShield(Integer lifeId) {
        CommunityLife communityLife = communityLifeMapper.selectByPrimaryKey(lifeId);
        if(communityLife != null){
            communityLife.setStatus(CommunityEnum.CommunityLifeStatusEnum.NORMAL.getCode());
            communityLifeMapper.updateStatus(communityLife);
        }else {
            throw new BusinessException(RetCodeConstants.OPER_FAIL, "操作失败");
        }
    }

    @Override
    @ActivityOperate(operateCategory = ActivityEnum.OperateCategory.CHECK, operateType = ActivityEnum.OperateType.SUCCESS)
    public Integer checkActivityPassByActivityId(Integer activityId, Integer userId) {
        activityInfoMapper.updateActivityStatus(activityId, ActivityEnum.ActivityDBStatus.PASS.getCode());
        return activityId;
    }

    @Override
    public Integer checkActivityFailByActivityId(AdminActivityOperateRecordRequestDto param) {

        try {
            Integer activityId = param.getActivityId();
            Integer userId = param.getUserId();
            String content = param.getContent();
            Date date = new Date();

            ActivityOperateRecord operateRecord = new ActivityOperateRecord();
            operateRecord.setUserId(userId);
            operateRecord.setCreateTime(date);
            operateRecord.setType(ActivityOperateRecordEnum.ActivityOperateType.CHECK_FAIL.getCode());
            operateRecord.setContent(content);
            operateRecord.setCategory(ActivityOperateRecordEnum.ActivityOperateType.CHECK_FAIL.getCode());
            operateRecord.setActivityId(activityId);
            activityOperateRecordMapper.insertSelective(operateRecord);
            activityInfoMapper.updateActivityStatus(activityId, ActivityEnum.ActivityDBStatus.FAIL.getCode());
        } catch (Exception e) {
            LOGGER.error("操作失败......", e);
            throw new BusinessException(RetCodeConstants.OPER_FAIL, "操作失败");
        }
        return param.getActivityId();
    }

}
