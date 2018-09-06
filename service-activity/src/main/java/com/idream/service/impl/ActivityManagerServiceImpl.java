package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.activity.Manager.ActivityListRequestDto;
import com.idream.commons.lib.dto.rabbitmq.OperateWangyiIMInfoDto;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.DeleteGroupRequestParam;
import com.idream.commons.lib.enums.ActivityEnum;
import com.idream.commons.lib.enums.ActivityTimeEnum;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.enums.WangYiEnum;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.ActivityTask;
import com.idream.commons.lib.model.RegionInfo;
import com.idream.commons.lib.model.WximGroup;
import com.idream.commons.lib.util.PageRowsUtils;
import com.idream.commons.mvc.annotation.ActivityOperate;
import com.idream.rabbitmq.RabbitSendService;
import com.idream.service.ActivityAdminService;
import com.idream.service.ActivityManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: penghekai
 * @Date: 2018/7/5 16:39
 * @Description:
 */
@Service
public class ActivityManagerServiceImpl implements ActivityManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityManagerServiceImpl.class);

    @Autowired
    private RegionGroupRelationMapper regionGroupRelationMapper;
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Autowired
    private RegionGroupInfoMapper regionGroupInfoMapper;
    @Autowired
    private ActivityAdminService activityAdminService;
    @Autowired
    private ActivityDisplayRelationMapper activityDisplayRelationMapper;
    @Autowired
    private ActivityJoinRelationMapper activityJoinRelationMapper;
    @Autowired
    private ActivityTaskMapper activityTaskMapper;
    @Autowired
    private ActivityInformationRelationMapper activityInformationRelationMapper;
    @Autowired
    private ActivityThemeMapper activityThemeMapper;
    @Autowired
    private ActivityTagRelationMapper activityTagRelationMapper;
    @Autowired
    private UserManagerMapper userManagerMapper;
    @Autowired
    private WximGroupMapper wximGroupMapper;
    @Autowired
    private RabbitSendService rabbitSendService;

    @Override
    public PagesDto<FindAllActivityResponseDto> getActivityList(Integer userId, ActivityListRequestDto param) {
        param.setUserId(userId);
        //用户绑定的书屋id
        param.setBookId(userManagerMapper.getBookIdByUser(userId));
        List<FindAllActivityResponseDto> infos = activityInfoMapper.selectManagerActivityListByQuery(param);
        List<FindAllActivityResponseDto> list = infos.stream().peek(i -> {

            //1待审核 2审核中，3审核成功 4审核失败
            if (i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.PUBLISHED.getCode())
                    || i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.DRAFT.getCode())) {
                i.setCheckStatus(1);
            } else if (i.getStatus().byteValue() == ActivityEnum.ActivityDBStatus.SUBMIT.getCode()
                    || i.getStatus().byteValue() == ActivityEnum.ActivityDBStatus.RESUBMIT.getCode()) {
                i.setCheckStatus(2);
            } else if (i.getStatus().byteValue() == ActivityEnum.ActivityDBStatus.PASS.getCode()
                    || i.getStatus().byteValue() == ActivityEnum.ActivityDBStatus.PUTAWAY.getCode()
                    || i.getStatus().byteValue() == ActivityEnum.ActivityDBStatus.UNSHELVE.getCode()) {
                i.setCheckStatus(3);
            } else if (i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.FAIL.getCode())
                    || i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.REFAIL.getCode())) {
                i.setCheckStatus(4);
            }
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
            //活动状态 1待提交 2审核中,3未发布,4通过：待开始 进行中 已结束
            long currentTime = System.currentTimeMillis();
            if (i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.PUBLISHED.getCode()) ||
                    i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.FAIL.getCode()) || i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.REFAIL.getCode())) {
                i.setStatus(Integer.valueOf(ActivityEnum.ActivityDBStatus.DRAFT.getCode()));
            } else if (i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.RESUBMIT.getCode())) {
                i.setStatus(Integer.valueOf(ActivityEnum.ActivityDBStatus.SUBMIT.getCode()));
            } else if (i.getStatus().byteValue() == (ActivityEnum.ActivityDBStatus.UNSHELVE.getCode())) {
                i.setStatus(Integer.valueOf(ActivityEnum.ActivityDBStatus.PASS.getCode()));
            } else if (i.getStatus().byteValue() == ActivityEnum.ActivityDBStatus.PUTAWAY.getCode()) {
                //activityStatus 1待开始  2进行中 3已结束
                if (currentTime < i.getStartTime().getTime()) {
                    i.setActivityStatus(1);
                } else if (i.getEndTime().getTime() < currentTime) {
                    i.setActivityStatus(3);
                } else {
                    i.setActivityStatus(2);
                }
            }
        }).collect(Collectors.toList());
        List<FindAllActivityResponseDto> dtoList = PageRowsUtils.getPageObj(list, param.getPage(), param.getRows());
        return new PagesDto<>(dtoList, list.size(), param.getPage(), param.getRows());
    }


    @Override
    public String selectDistrictCodeByUserId(Integer userId) {

        return regionGroupInfoMapper.selectDistrictByUserId(userId);
    }

    @Override
    public List<RegionInfo> selectRegionListByUserId(Integer userId) {

        return regionGroupRelationMapper.selectBookRelateRegionByUserId(userId);
    }

    @Override
    public PagesDto<FindPublishedActivityDetailResponseDto> getPublishedActivityDetail(Integer userId, Integer page, Integer rows) {

        List<FindPublishedActivityDetailResponseDto> list = activityInfoMapper.getPublishedActivityDetailByUserId(userId);
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
        List<FindPublishedActivityDetailResponseDto> dtoList = PageRowsUtils.getPageObj(list,page,rows);
        return new PagesDto<>(dtoList, list.size(), page, rows);
    }

    @Override
    public List<AdminActivityStatisticsResponseDto> getActivityStatisticsByActivityId(Integer activityId) {
        return activityAdminService.getActivityStatisticsByActivityId(activityId);
    }

    @Override
    public void addCreateGroupChat(Integer userId, CreateGroupRequestParam param) {
        activityAdminService.addCreateGroupChat(userId, param);
    }

    @Override
    public Integer operateActivityCancel(AdminActivityOperateRecordRequestDto param) {
        Integer activityId = activityAdminService.operateActivityCancel(param);
        return activityId;
    }

    @Override
    public Integer addActivity(Integer createId, AdminActivityPublishDto aapd) {

        //保存活动信息
        Integer result = activityAdminService.addActivity(createId, aapd, SystemEnum.ClientChannelEnum.MANAGER_WEB.getCode());
        return result;
    }

    /*
     * 编辑活动
     */
    @Override
    public Integer updateActivity(JSONPublicParam<AdminActivityPublishDto> param) {
        Integer result = activityAdminService.updateActivity(param, SystemEnum.ClientChannelEnum.MANAGER_WEB.getCode());
        return result;
    }

    //编辑时回显
    @Override
    public AdminActivityDisplayDto getByActivityId(Integer activityId) {
        return activityAdminService.getByActivityId(activityId);
    }


    @Override
    @ActivityOperate(operateCategory = ActivityEnum.OperateCategory.EDIT, operateType = ActivityEnum.OperateType.SUBMIT)
    public Integer updateActivityStatusSubmit(Integer activityId) {
        activityInfoMapper.updateActivityStatus(activityId, ActivityEnum.ActivityDBStatus.SUBMIT.getCode());
        return activityId;
    }

    @Override
    @ActivityOperate(operateCategory = ActivityEnum.OperateCategory.EDIT, operateType = ActivityEnum.OperateType.UP)
    public void updateActivityStatusPutWay(Integer activityId) {
        activityInfoMapper.updateActivityStatus(activityId, ActivityEnum.ActivityDBStatus.PUTAWAY.getCode());
    }


    @Override
    @ActivityOperate(operateCategory = ActivityEnum.OperateCategory.CHECK, operateType = ActivityEnum.OperateType.DOWN)
    public Integer deleteActivity(Integer activityId) {
        Byte status = activityInfoMapper.selectByPrimaryKey(activityId).getStatus();
        if (status.equals(ActivityEnum.ActivityDBStatus.DRAFT.getCode()) || status.equals(ActivityEnum.ActivityDBStatus.FAIL.getCode()) || status.equals(ActivityEnum.ActivityDBStatus.PASS.getCode())) {
            //根据活动id删除相关信息
            try {
                activityInfoMapper.deleteByPrimaryKey(activityId);
                activityDisplayRelationMapper.deleteByActivityId(activityId);
                activityJoinRelationMapper.deleteByActivityId(activityId);
                activityInformationRelationMapper.deleteActivityInformationRelationByActivityId(activityId);
                activityTaskMapper.deleteActivityTaskByActivityId(activityId);
                activityThemeMapper.deleteActivityThemeByActivityId(activityId);
                activityTagRelationMapper.deleteActivityTagRelationByActivityId(activityId);

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
                e.printStackTrace();
                LOGGER.error("删除失败", e);
            }
        } else {
            activityInfoMapper.updateActivityStatus(activityId, ActivityEnum.ActivityDBStatus.DELETE.getCode());
        }
        return activityId;
    }


}

