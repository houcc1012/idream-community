package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.activity.ActivityTagAddRequestDto;
import com.idream.commons.lib.dto.admin.IntegrationConfigDto;
import com.idream.commons.lib.dto.admin.IntegrationConfigParams;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.enums.ActivityEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.*;
import com.idream.commons.lib.util.PageRowsUtils;
import com.idream.service.ActivityTagService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityTagServiceImpl implements ActivityTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTagServiceImpl.class);
    @Autowired
    private ActivityTypeMapper activityTypeMapper;
    @Autowired
    private ActivityTagMapper activityTagMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ActivityTypeTagRelationMapper activityTypeTagRelationMapper;
    @Autowired
    private IntegrationConfigMapper integrationConfigMapper;

    public static final String signScore = "activity_task_score";

    public static final String firstShareScore = "activity_first_share_score";

    public static final String shareScore = "activity_share_score";

    public static final String maxShareScore = "activity_max_share_score";

    @Override
    public PagesDto selectActivityTagListByActivityTag(String label, Integer type, int page, int rows) {

        List<ActivityTagResponseDto> list = activityTypeTagRelationMapper.selectActivityTypeTagByLabelAndType(label, type);
        List<ActivityTagResponseDto> dtoList = PageRowsUtils.getPageObj(list, page, rows);
        return new PagesDto(dtoList, list.size(), page, rows);
    }

    @Override
    public int insertActivityTag(Integer authUserId, String label, Integer type) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authUserId);
        String nickName = userInfo.getNickName();
        Date date = new Date();
        int a = 0;
        if (type == 1) {
            ActivityType activityType = new ActivityType();
            activityType.setName(label);
            activityType.setAdminUserId(authUserId);
            activityType.setAdminNickName(nickName);
            activityType.setStatus(ActivityEnum.ActivityTypeStatus.NORMAL.getCode());
            activityType.setCreateTime(date);
            activityType.setUpdateTime(date);
            a = activityTypeMapper.insertSelective(activityType);
        } else if (type == 2) {
            ActivityTag activityTag = new ActivityTag();
            activityTag.setLabel(label);
            activityTag.setAdminUserId(authUserId);
            activityTag.setAdminNickName(nickName);
            activityTag.setStatus(ActivityEnum.ActivityTagStatus.NORMAL.getCode());
            activityTag.setCreateTime(date);
            activityTag.setUpdateTime(date);
            a = activityTagMapper.insertSelective(activityTag);
        }
        return a;
    }

    @Override
    public int updateActivityTag(Integer authUserId, String label, Integer type, Integer id) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authUserId);
        String nickName = userInfo.getNickName();
        Date date = new Date();
        int a = 0;
        if (type == 1) {
            ActivityType activityType = new ActivityType();
            activityType.setName(label);
            activityType.setAdminUserId(authUserId);
            activityType.setAdminNickName(nickName);
            activityType.setStatus(ActivityEnum.ActivityTypeStatus.NORMAL.getCode());
            activityType.setUpdateTime(date);
            activityType.setId(id);
            a = activityTypeMapper.updateByPrimaryKeySelective(activityType);
        } else if (type == 2) {
            ActivityTag activityTag = new ActivityTag();
            activityTag.setLabel(label);
            activityTag.setAdminUserId(authUserId);
            activityTag.setAdminNickName(nickName);
            activityTag.setStatus(ActivityEnum.ActivityTagStatus.NORMAL.getCode());
            activityTag.setUpdateTime(date);
            activityTag.setId(id);
            a = activityTagMapper.updateByPrimaryKeySelective(activityTag);
        }
        return a;
    }

    @Override
    public int updateActivityTagStatus(Integer id, Integer type) {
        int a = 0;
        if (type == 1) {
            ActivityType activityType = new ActivityType();
            activityType.setId(id);
            activityType.setStatus(ActivityEnum.ActivityTypeStatus.DELETE.getCode());
            a = activityTypeMapper.updateByPrimaryKeySelective(activityType);
        } else if (type == 2) {
            ActivityTag activityTag = new ActivityTag();
            activityTag.setId(id);
            activityTag.setStatus(ActivityEnum.ActivityTagStatus.DELETE.getCode());
            a = activityTagMapper.updateByPrimaryKeySelective(activityTag);
        }
        return a;
    }

    //通过一级标签id查询二级标签,并且对已关联一级标签的二级标签做区分
    @Override
    public ActivityTagLinkedDto selectSecondTagToFirstTag(Integer id) {
        List<ActivityTag> list = activityTagMapper.selectTagsList();
        List<ActivityTag> selectSecondTagToFirstTag = activityTagMapper.selectSecondTagToFirstTag(id);
        ActivityTagLinkedDto dto = new ActivityTagLinkedDto();
        dto.setAllTags(list);
        dto.setSelectedTags(selectSecondTagToFirstTag.stream().map(ActivityTag::getId).collect(Collectors.toList()));
        return dto;
    }

    //关联标签
    @Override
    public int insertActivitySecondTag(SecondActivityTagDto secondActivityTagDto) {
        //一级标签id
        Integer typeId = secondActivityTagDto.getId();
        activityTypeTagRelationMapper.deleteByTypeId(typeId);
        Date date = new Date();
        List<Integer> activityTagList = secondActivityTagDto.getActivityTagList();
        int a = 0;
        for (Integer tagId : activityTagList) {
            //二级标签id
            ActivityTypeTagRelation activityTypeTagRelation = new ActivityTypeTagRelation();
            activityTypeTagRelation.setTagId(tagId);
            activityTypeTagRelation.setTypeId(typeId);
            activityTypeTagRelation.setCreateTime(date);
            activityTypeTagRelation.setUpdateTime(date);
            a = activityTypeTagRelationMapper.insertSelective(activityTypeTagRelation);
        }
        return a;
    }

    @Override
    public PagesDto selectActivityTagAllList(int page, int rows) {
        PageInfo pageInfo = null;
        PageHelper.startPage(page, rows);
        List<ActivityTagResponseDto> selectActivityTagAllList = activityTagMapper.selectActivityTagAllList();
        if (selectActivityTagAllList.size() != 0 && selectActivityTagAllList != null) {
            pageInfo = new PageInfo<>(selectActivityTagAllList);
        } else {
            return new PagesDto(selectActivityTagAllList, 0, page, rows);
        }
        return new PagesDto(selectActivityTagAllList, pageInfo.getTotal(), page, rows);
    }

    @Override
    public List<AppActivityTypeRelateTagResponseDto> listTagByTypeId(Integer typeId) {

        //展示该类型下关联的所有二级标签以及三级标签
        List<AppActivityTypeRelateTagResponseDto> dtoList = activityTagMapper.getActivityTypeRelateTagList(typeId);
        for (AppActivityTypeRelateTagResponseDto responseDto : dtoList) {
            List<ActivityTag> thirdTagList = activityTagMapper.getActivityTagListByPid(responseDto.getId());
            responseDto.setChildTagList(thirdTagList);
        }
        return dtoList;
    }

    @Override
    public PagesDto<ActivityTypeLibraryResponseDto> selectActivityTypeLibraryByExample(String type, int page, int rows) {

        List<ActivityTypeLibraryResponseDto> list = activityTagMapper.selectActivityTypeLibrary(type);
        List<ActivityTypeLibraryResponseDto> dtoList = PageRowsUtils.getPageObj(list, page, rows);
        return new PagesDto(dtoList, list.size(), page, rows);
    }

    @Override
    public int insertActivityType(JSONPublicParam<ActivityTypeAddRequestDto> param) {

        try {
            AuthUserInfo authUserInfo = param.getAuthUserInfo();
            ActivityTypeAddRequestDto requestParam = param.getRequestParam();
            Date date = new Date();
            //查询该类名是否存在
            ActivityType bean = activityTypeMapper.getActivityTypeByTypeName(requestParam.getName());
            if (bean != null && bean.getStatus() == 2) {
                //1为正常
                bean.setStatus((byte) 1);
                int i = activityTypeMapper.updateByPrimaryKeySelective(bean);
                return i;
            } else if (bean != null && bean.getStatus() == 1) {
                throw new BusinessException(RetCodeConstants.ACTIVITY_TAG_REPEAT, "活动类型已存在");
            } else {
                ActivityType activityType = new ActivityType();
                activityType.setAdminUserId(authUserInfo.getUserId());
                activityType.setAdminNickName(userInfoMapper.selectByPrimaryKey(authUserInfo.getUserId()).getNickName());
                if (requestParam.getKind() == 0) {
                    //不选择类型属性，默认为2辅类型
                    requestParam.setKind((byte) 2);
                }
                activityType.setKind(requestParam.getKind());
                activityType.setIcon(requestParam.getIcon());
                activityType.setIconLight(requestParam.getIconLight());
                activityType.setBackground(requestParam.getBackground());
                activityType.setName(requestParam.getName());
                activityType.setDescription(requestParam.getDescription());
                activityType.setCreateTime(date);
                activityType.setUpdateTime(date);
                //1代表正常，2代表删除
                activityType.setStatus((byte) 1);
                return activityTypeMapper.insertSelective(activityType);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            LOGGER.error("创建失败:......." + e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("创建失败:......." + e);
        }
        return 0;
    }

    @Override
    public int updateActivityType(JSONPublicParam<ActivityUpdateTypeRequestDto> param) {
        AuthUserInfo authUserInfo = param.getAuthUserInfo();
        ActivityUpdateTypeRequestDto requestParam = param.getRequestParam();

        Date date = new Date();
        ActivityType activityType = activityTypeMapper.selectByPrimaryKey(requestParam.getId());
        activityType.setAdminUserId(authUserInfo.getUserId());
        activityType.setAdminNickName(userInfoMapper.selectUserInfoByUserId(authUserInfo.getUserId()).getNickName());
        activityType.setIcon(requestParam.getIcon());
        activityType.setKind(requestParam.getKind());
        activityType.setName(requestParam.getName());
        activityType.setIconLight(requestParam.getIconLight());
        activityType.setBackground(requestParam.getBackground());
        activityType.setDescription(requestParam.getDescription());
        activityType.setCreateTime(date);
        activityType.setUpdateTime(date);
        //1代表正常，2代表删除
        activityType.setStatus((byte) 1);
        return activityTypeMapper.updateByPrimaryKeySelective(activityType);
    }

    @Override
    public int deleteActivityTypeById(Integer typeId) {
        ActivityType activityType = activityTypeMapper.selectByPrimaryKey(typeId);
        try {
            activityType.setStatus((byte) 2);
            activityTypeTagRelationMapper.deleteByTypeId(typeId);
            return activityTypeMapper.updateByPrimaryKeySelective(activityType);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除活动类型失败，请重试");
        }
        return 0;
    }

    @Override
    public List<ActivityTagTreeResponseDto> selectActivityTagsByTypeId(Integer typeId) {

        List<ActivityTagTreeResponseDto> resultDto = new ArrayList<>();
        List<Integer> firstTagIds = activityTypeTagRelationMapper.selectFirstTagIdsByTypeId(typeId);
        List<Integer> secondTagIds = activityTypeTagRelationMapper.selectSecondTagIdsByTypeId(typeId);
        for (Integer firstTagId : firstTagIds) {
            ActivityTag activityTag = activityTagMapper.selectByPrimaryKey(firstTagId);
            ActivityTagTreeResponseDto firstTagTree = new ActivityTagTreeResponseDto();
            firstTagTree.setId(activityTag.getId());
            firstTagTree.setPid(activityTag.getPid());
            firstTagTree.setLevel(activityTag.getLevel());
            firstTagTree.setLabel(activityTag.getLabel());
            List<ActivityTagTreeResponseDto> secondTagList = new ArrayList<>();
            for (Integer secondTagId : secondTagIds) {
                ActivityTag secondTag = activityTagMapper.selectByPrimaryKey(secondTagId);
                if (secondTag.getPid().equals(firstTagId)) {
                    ActivityTagTreeResponseDto secondTagTree = new ActivityTagTreeResponseDto();
                    secondTagTree.setId(secondTag.getId());
                    secondTagTree.setPid(secondTag.getPid());
                    secondTagTree.setLabel(secondTag.getLabel());
                    secondTagTree.setLevel(secondTag.getLevel());
                    secondTagTree.setChildActivityTagList(null);
                    secondTagList.add(secondTagTree);
                }
            }
            firstTagTree.setChildActivityTagList(secondTagList);
            resultDto.add(firstTagTree);
        }
        return resultDto;
    }

    /*
     * 处理二级活动标签
     * @param parentPid
     * @param activityTagList
     * @return
     */
    private List<ActivityTagTreeResponseDto> buildActivityTagTree(Integer parentPid, List<ActivityTag> activityTagList) {
        List<ActivityTagTreeResponseDto> list = new ArrayList<>();
        for (ActivityTag activityTag : activityTagList) {
            if (parentPid.equals(activityTag.getPid())) {
                ActivityTagTreeResponseDto activityTagTreeResponseDto = new ActivityTagTreeResponseDto();
                activityTagTreeResponseDto.setId(activityTag.getId());
                activityTagTreeResponseDto.setLabel(activityTag.getLabel());
                activityTagTreeResponseDto.setPid(activityTag.getPid());
                activityTagTreeResponseDto.setLevel(activityTag.getLevel());
                activityTagTreeResponseDto.setChildActivityTagList(buildActivityTagTree(activityTag.getId(), activityTagMapper.getActivityTagListByPid(activityTag.getId())));
                list.add(activityTagTreeResponseDto);
            }
        }
        return list;
    }

    /*
     *查询所有的一二级活动标签
     */
    @Override
    public List<ActivityTagTreeResponseDto> selectAllActivityTags() {
        List<ActivityTagTreeResponseDto> dtoList = new ArrayList<>();
        //查询所有的一级活动标签
        List<ActivityTag> firstActivityTagList = activityTagMapper.selectAllFirstActivityTags();
        if (firstActivityTagList.size() != 0) {
            for (ActivityTag firstActivityTag : firstActivityTagList) {
                ActivityTagTreeResponseDto firstTagTreeResponseDto = new ActivityTagTreeResponseDto();
                firstTagTreeResponseDto.setId(firstActivityTag.getId());
                firstTagTreeResponseDto.setPid(firstActivityTag.getPid());
                firstTagTreeResponseDto.setLabel(firstActivityTag.getLabel());
                firstTagTreeResponseDto.setLevel(firstActivityTag.getLevel());
                List<ActivityTag> secondActivityTagList = activityTagMapper.getActivityTagListByPid(firstActivityTag.getId());
                if (secondActivityTagList.size() != 0) {
                    List<ActivityTagTreeResponseDto> list = new ArrayList<>();
                    for (ActivityTag tag : secondActivityTagList) {
                        ActivityTagTreeResponseDto secondTagTreeResponseDto = new ActivityTagTreeResponseDto();
                        secondTagTreeResponseDto.setId(tag.getId());
                        secondTagTreeResponseDto.setPid(tag.getPid());
                        secondTagTreeResponseDto.setLabel(tag.getLabel());
                        secondTagTreeResponseDto.setLevel(tag.getLevel());
                        secondTagTreeResponseDto.setChildActivityTagList(null);
                        list.add(secondTagTreeResponseDto);
                    }
                    firstTagTreeResponseDto.setChildActivityTagList(list);
                }
                dtoList.add(firstTagTreeResponseDto);
            }
        }
        return dtoList;
    }

    @Override
    public void insertActivityTypeRelateTag(ActivityTypeRelateTagRequestDto requestDto) {

        Integer typeId = requestDto.getTypeId();
        List<Integer> firstTagIds = requestDto.getFirstTagIds();
        List<Integer> secondTagIds = requestDto.getSecondTagIds();
        Date date = new Date();
        activityTypeTagRelationMapper.deleteByTypeId(typeId);
        addActivityTypeRelateTag(typeId, firstTagIds, date);
        addActivityTypeRelateTag(typeId, secondTagIds, date);
    }

    private void addActivityTypeRelateTag(Integer typeId, List<Integer> tagIds, Date date) {

        for (Integer tagId : tagIds) {
            ActivityTypeTagRelation activityTypeTagRelation = new ActivityTypeTagRelation();
            activityTypeTagRelation.setTypeId(typeId);
            activityTypeTagRelation.setTagId(tagId);
            activityTypeTagRelation.setCreateTime(date);
            activityTypeTagRelation.setUpdateTime(date);
            activityTypeTagRelationMapper.insertSelective(activityTypeTagRelation);
        }
    }

    @Override
    public int insertAllKindActivityTag(JSONPublicParam<ActivityTagAddRequestDto> param) {

        try {
            AuthUserInfo authUserInfo = param.getAuthUserInfo();
            ActivityTagAddRequestDto requestParam = param.getRequestParam();

            ActivityTag record = activityTagMapper.selectActivityTagByLabel(requestParam.getLabel());
            if (record != null && record.getStatus().equals(2)) {
                if (!record.getPid().equals(requestParam.getPid())) {
                    record.setPid(requestParam.getPid());
                }
                if (!record.getLevel().equals(requestParam.getLevel())) {
                    record.setLevel(requestParam.getLevel());
                }
                record.setStatus((byte) 1);
                return activityTagMapper.updateByPrimaryKeySelective(record);
            } else if (record != null && record.getStatus().equals(3)) {
                if (!record.getPid().equals(requestParam.getPid())) {
                    record.setPid(requestParam.getPid());
                }
                if (!record.getLevel().equals(requestParam.getLevel())) {
                    record.setLevel(requestParam.getLevel());
                }
                record.setStatus((byte) 1);
                return activityTagMapper.updateByPrimaryKeySelective(record);
            } else if (record != null && record.getStatus().equals((byte) 1)) {
                throw new BusinessException("活动标签已存在");
            } else {
                ActivityTag activityTag = new ActivityTag();
                Date date = new Date();
                activityTag.setLabel(requestParam.getLabel());
                activityTag.setPid(requestParam.getPid());
                activityTag.setAdminUserId(authUserInfo.getUserId());
                String nickName = userInfoMapper.selectUserInfoByUserId(authUserInfo.getUserId()).getNickName();
                String realName = userInfoMapper.selectUserInfoByUserId(authUserInfo.getUserId()).getRealName();
                if (StringUtils.isNotBlank(nickName)) {
                    activityTag.setAdminNickName(nickName);
                } else if (StringUtils.isNotBlank(realName)) {
                    activityTag.setAdminNickName(realName);
                } else {
                    activityTag.setAdminNickName("");
                }
                activityTag.setStatus(ActivityEnum.ActivityTagStatus.NORMAL.getCode());
                activityTag.setLevel(requestParam.getLevel());
                activityTag.setCreateTime(date);
                activityTag.setUpdateTime(date);
                return activityTagMapper.insertSelective(activityTag);
            }
        } catch (BusinessException e) {
            LOGGER.error("新增失败......" + e);
            throw e;
        }
    }

    @Override
    public int updateActivityTagById(JSONPublicParam<ActivityTagUpdateRequestDto> param) {

        Integer authUserId = param.getAuthUserInfo().getUserId();
        ActivityTagUpdateRequestDto requestParam = param.getRequestParam();

        //查看是否已存在的标签名
        ActivityTag record = activityTagMapper.selectActivityTagByLabel(requestParam.getLabel());
        if (record == null) {
            Date date = new Date();
            ActivityTag activityTag = activityTagMapper.selectByPrimaryKey(requestParam.getId());
            activityTag.setLabel(requestParam.getLabel());
            activityTag.setAdminUserId(authUserId);
            activityTag.setLevel(requestParam.getLevel());
            activityTag.setAdminNickName(userInfoMapper.selectUserInfoByUserId(authUserId).getNickName());
            activityTag.setUpdateTime(date);
            return activityTagMapper.updateByPrimaryKeySelective(activityTag);
        } else {
            throw new BusinessException(RetCodeConstants.ACTIVITY_TAG_REPEAT, "已存在的标签名");
        }
    }

    @Override
    public void deleteActivityTagById(Integer tagId) {

        try {
            ActivityTag activityTag = activityTagMapper.selectByPrimaryKey(tagId);
            if (activityTag != null) {
                //修改一级活动标签状态
                if (activityTag.getLevel().equals(1)) {
                    activityTag.setStatus(ActivityEnum.ActivityTagStatus.DELETE.getCode());
                    activityTagMapper.updateByPrimaryKeySelective(activityTag);
                    //修改二级活动标签状态
                    List<ActivityTag> secondTagList = activityTagMapper.getActivityTagListByPid(activityTag.getId());
                    if (secondTagList.size() != 0) {
                        for (ActivityTag secondTag : secondTagList) {
                            secondTag.setStatus(ActivityEnum.ActivityTagStatus.DELETE.getCode());
                            activityTagMapper.updateByPrimaryKeySelective(secondTag);
                            List<ActivityTag> thirdTagList = activityTagMapper.getActivityTagListByPid(secondTag.getId());
                            if (thirdTagList.size() != 0) {
                                for (ActivityTag thirdTag : thirdTagList) {
                                    thirdTag.setStatus(ActivityEnum.ActivityTagStatus.DELETE.getCode());
                                    activityTagMapper.updateByPrimaryKeySelective(thirdTag);
                                }
                            }
                        }
                    }
                } else if (activityTag.getLevel().equals(2)) {
                    activityTag.setStatus(ActivityEnum.ActivityTagStatus.DELETE.getCode());
                    activityTagMapper.updateByPrimaryKeySelective(activityTag);
                    List<ActivityTag> thirdTagList = activityTagMapper.getActivityTagListByPid(activityTag.getId());
                    if (thirdTagList.size() != 0) {
                        for (ActivityTag thirdTag : thirdTagList) {
                            thirdTag.setStatus(ActivityEnum.ActivityTagStatus.DELETE.getCode());
                            activityTagMapper.updateByPrimaryKeySelective(thirdTag);
                        }
                    }
                } else if (activityTag.getLevel().equals(3)) {
                    activityTag.setStatus(ActivityEnum.ActivityTagStatus.DELETE.getCode());
                    activityTagMapper.updateByPrimaryKeySelective(activityTag);
                }
            } else {
                throw new BusinessException("活动标签不存在");
            }
        } catch (BusinessException e) {
            LOGGER.error("删除失败..." + e);
            throw e;
        }
    }

    @Override
    public PagesDto selectActivityTagListByExample(String label, int page, int rows) {

        List<ActivityTagTreeResponseDto> tagListTree = new ArrayList<>();
        if (!StringUtils.isEmpty(label)) {
            //模糊查询所有的标签，包含一级，二级，和三级标签
            List<ActivityTag> tagList = activityTagMapper.selectActivityTagLibraryByExample(label);
            if (tagList.size() != 0) {
                for (ActivityTag activityTag : tagList) {
                    //当前标签为一级活动标签
                    if (activityTag.getLevel().equals(1)) {
                        ActivityTagTreeResponseDto firstTagTreeResponseDto = new ActivityTagTreeResponseDto();
                        firstTagTreeResponseDto.setId(activityTag.getId());
                        firstTagTreeResponseDto.setPid(activityTag.getPid());
                        firstTagTreeResponseDto.setLabel(activityTag.getLabel());
                        firstTagTreeResponseDto.setLevel(activityTag.getLevel());
                        //查询二级活动标签
                        List<ActivityTag> secondActivityTagList = activityTagMapper.getActivityTagListByPid(activityTag.getId());
                        if (secondActivityTagList.size() != 0) {
                            List<ActivityTagTreeResponseDto> secondTreeList = new ArrayList<>();
                            for (ActivityTag secondTag : secondActivityTagList) {
                                ActivityTagTreeResponseDto secondTagTreeResponseDto = new ActivityTagTreeResponseDto();
                                secondTagTreeResponseDto.setId(secondTag.getId());
                                secondTagTreeResponseDto.setPid(secondTag.getPid());
                                secondTagTreeResponseDto.setLevel(secondTag.getLevel());
                                secondTagTreeResponseDto.setLabel(secondTag.getLabel());
                                //查询三级标签
                                List<ActivityTag> thirdActivityTagList = activityTagMapper.getActivityTagListByPid(secondTag.getId());
                                if (thirdActivityTagList.size() != 0) {
                                    List<ActivityTagTreeResponseDto> thirdTreeList = new ArrayList<>();
                                    for (ActivityTag thirdTag : thirdActivityTagList) {
                                        ActivityTagTreeResponseDto thirdTagTreeResponseDto = new ActivityTagTreeResponseDto();
                                        thirdTagTreeResponseDto.setId(thirdTag.getId());
                                        thirdTagTreeResponseDto.setLabel(thirdTag.getLabel());
                                        thirdTagTreeResponseDto.setLevel(thirdTag.getLevel());
                                        thirdTagTreeResponseDto.setPid(thirdTag.getPid());
                                        thirdTagTreeResponseDto.setChildActivityTagList(null);
                                        thirdTreeList.add(thirdTagTreeResponseDto);
                                    }
                                    secondTagTreeResponseDto.setChildActivityTagList(thirdTreeList);
                                }
                                secondTreeList.add(secondTagTreeResponseDto);
                            }
                            firstTagTreeResponseDto.setChildActivityTagList(secondTreeList);
                        }
                        tagListTree.add(firstTagTreeResponseDto);
                        //模糊查询的标签是二级标签
                    } else if (activityTag.getLevel().equals(2)) {
                        ActivityTagTreeResponseDto firstTagTreeResponseDto = new ActivityTagTreeResponseDto();
                        firstTagTreeResponseDto.setId(activityTag.getPid());
                        ActivityTag firstTag = activityTagMapper.selectByPrimaryKey(activityTag.getPid());
                        firstTagTreeResponseDto.setPid(firstTag.getPid());
                        firstTagTreeResponseDto.setLabel(firstTag.getLabel());
                        firstTagTreeResponseDto.setLevel(firstTag.getLevel());

                        List<ActivityTagTreeResponseDto> secondTreeList = new ArrayList<>();
                        ActivityTagTreeResponseDto secondTagTreeResponseDto = new ActivityTagTreeResponseDto();
                        secondTagTreeResponseDto.setId(activityTag.getId());
                        secondTagTreeResponseDto.setPid(activityTag.getPid());
                        secondTagTreeResponseDto.setLabel(activityTag.getLabel());
                        secondTagTreeResponseDto.setLevel(activityTag.getLevel());

                        List<ActivityTag> thirdActivityTagList = activityTagMapper.getActivityTagListByPid(activityTag.getId());
                        if (thirdActivityTagList.size() != 0) {
                            List<ActivityTagTreeResponseDto> thirdTreeList = new ArrayList<>();
                            for (ActivityTag thirdTag : thirdActivityTagList) {
                                ActivityTagTreeResponseDto thirdTagTreeResponseDto = new ActivityTagTreeResponseDto();
                                thirdTagTreeResponseDto.setId(thirdTag.getId());
                                thirdTagTreeResponseDto.setLabel(thirdTag.getLabel());
                                thirdTagTreeResponseDto.setLevel(thirdTag.getLevel());
                                thirdTagTreeResponseDto.setPid(thirdTag.getPid());
                                thirdTagTreeResponseDto.setChildActivityTagList(null);
                                thirdTreeList.add(thirdTagTreeResponseDto);
                            }
                            secondTagTreeResponseDto.setChildActivityTagList(thirdTreeList);
                        }
                        secondTreeList.add(secondTagTreeResponseDto);
                        firstTagTreeResponseDto.setChildActivityTagList(secondTreeList);
                        tagListTree.add(firstTagTreeResponseDto);
                        //模糊查询的标签为三级标签
                    } else {
                        List<ActivityTagTreeResponseDto> thirdTreeList = new ArrayList<>();
                        //三级标签
                        ActivityTagTreeResponseDto thirdTagTreeResponseDto = new ActivityTagTreeResponseDto();
                        thirdTagTreeResponseDto.setId(activityTag.getId());
                        thirdTagTreeResponseDto.setPid(activityTag.getPid());
                        thirdTagTreeResponseDto.setLabel(activityTag.getLabel());
                        thirdTagTreeResponseDto.setLevel(activityTag.getLevel());
                        thirdTagTreeResponseDto.setChildActivityTagList(null);
                        thirdTreeList.add(thirdTagTreeResponseDto);

                        //二级标签
                        ActivityTagTreeResponseDto secondTagTreeResponseDto = new ActivityTagTreeResponseDto();
                        ActivityTag secondTag = activityTagMapper.selectByPrimaryKey(activityTag.getPid());
                        secondTagTreeResponseDto.setId(secondTag.getId());
                        secondTagTreeResponseDto.setPid(secondTag.getPid());
                        secondTagTreeResponseDto.setLabel(secondTag.getLabel());
                        secondTagTreeResponseDto.setLevel(secondTag.getLevel());
                        secondTagTreeResponseDto.setChildActivityTagList(thirdTreeList);
                        List<ActivityTagTreeResponseDto> secondTreeList = new ArrayList<>();
                        secondTreeList.add(secondTagTreeResponseDto);

                        //一级标签
                        ActivityTagTreeResponseDto firstTagTreeResponseDto = new ActivityTagTreeResponseDto();
                        ActivityTag firstTag = activityTagMapper.selectByPrimaryKey(activityTagMapper.selectByPrimaryKey(activityTag.getPid()).getPid());
                        firstTagTreeResponseDto.setId(firstTag.getId());
                        firstTagTreeResponseDto.setPid(firstTag.getPid());
                        firstTagTreeResponseDto.setLabel(firstTag.getLabel());
                        firstTagTreeResponseDto.setLevel(firstTag.getLevel());
                        firstTagTreeResponseDto.setChildActivityTagList(secondTreeList);
                        tagListTree.add(firstTagTreeResponseDto);
                    }
                }
            }
            List<ActivityTagTreeResponseDto> dtoList = PageRowsUtils.getPageObj(tagListTree, page, rows);
            return new PagesDto(dtoList, tagListTree.size(), page, rows);
        }
        List<ActivityTagTreeResponseDto> dto = new ArrayList<>();
        List<ActivityTag> activityTagList = activityTagMapper.selectAllFirstActivityTags();
        if (activityTagList.size() != 0) {
            dto = buildActivityTagTree(0, activityTagList);
        }
        List<ActivityTagTreeResponseDto> resultList = PageRowsUtils.getPageObj(dto, page, rows);
        return new PagesDto(resultList, dto.size(), page, rows);
    }

    @Override
    public IntegrationConfigDto selectActivityScore() {
        IntegrationConfigDto config = new IntegrationConfigDto();

        config.setSignScore(integrationConfigMapper.getIntegerByCode(signScore));
        config.setFirstShareScore(integrationConfigMapper.getIntegerByCode(firstShareScore));
        config.setShareScore(integrationConfigMapper.getIntegerByCode(shareScore));
        config.setMaxShareScore(integrationConfigMapper.getIntegerByCode(maxShareScore));

        return config;
    }

    @Override
    public void saveActivityScore(IntegrationConfigParams params) {
        //校验积分合理性
        double maxScore = params.getMaxShareScore();
        double firstScore = params.getFirstShareScore();
        double noFirstScore = params.getShareScore();
        double obj = 0;
        if (noFirstScore > 0 && (maxScore - firstScore > 0)) {
            obj = (maxScore - firstScore) / noFirstScore;
        }else if(noFirstScore > 0 && (maxScore - firstScore <= 0)){
            throw new BusinessException("非首次分享积分大于0，最大积分必须大于首次分享积分！");
        } else if(noFirstScore ==0 && (maxScore - firstScore != 0)){
            throw new BusinessException("非首次分享为0，最大积分必须等于首次分享积分！");
        }
        if (obj%1 == 0 ) {
            integrationConfigMapper.updateValueByCode(signScore, params.getSignScore());
            integrationConfigMapper.updateValueByCode(firstShareScore, params.getFirstShareScore());
            integrationConfigMapper.updateValueByCode(shareScore, params.getShareScore());
            integrationConfigMapper.updateValueByCode(maxShareScore, params.getMaxShareScore());
        } else {
            throw new BusinessException("非首次分享积分必须是最大获得积分减去首次分享积分之后的整数倍！");
        }

    }

}
