package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.activity.UpdateGroupInfo;
import com.idream.commons.lib.dto.activity.UpdateGroupInfoRequestParam;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.wangyi.ActivityGroupInfoRequestParam;
import com.idream.commons.lib.dto.wangyi.ActivityGroupInfoResponseDto;
import com.idream.commons.lib.dto.wangyi.AddGroupManagerRequestDto;
import com.idream.commons.lib.dto.wangyi.AddGroupManagerRequestParam;
import com.idream.commons.lib.dto.wangyi.AddUsersToGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.CreateGroupResponseDto;
import com.idream.commons.lib.dto.wangyi.CreateUserRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateUserResponseDto;
import com.idream.commons.lib.dto.wangyi.DeleteGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.DeleteGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.GroupAdviceRequestDto;
import com.idream.commons.lib.dto.wangyi.GroupMuteListResponseDto;
import com.idream.commons.lib.dto.wangyi.JoinGroupByTidRequestParam;
import com.idream.commons.lib.dto.wangyi.JoinGroupListResposeDto;
import com.idream.commons.lib.dto.wangyi.JoinGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.KickOutUserFromGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.KickOutUserFromGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.LeaveGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.LeaveGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.MuteGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.MuteGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.MuteTeamRequestDto;
import com.idream.commons.lib.dto.wangyi.MuteTeamRequestParam;
import com.idream.commons.lib.dto.wangyi.MuteUserRequestDto;
import com.idream.commons.lib.dto.wangyi.MuteUserRequestParam;
import com.idream.commons.lib.dto.wangyi.QueryAccidByUserIdResponseDto;
import com.idream.commons.lib.dto.wangyi.QueryActivityIdByTidResponseDto;
import com.idream.commons.lib.dto.wangyi.QueryGroupAndUsersDetailResponseDto;
import com.idream.commons.lib.dto.wangyi.QueryGroupByActivityId;
import com.idream.commons.lib.dto.wangyi.RemoveManagerFromGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.RemoveManagerFromGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.UpdateGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.UpdateGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.UpdateGroupUserNickNameRequestDto;
import com.idream.commons.lib.dto.wangyi.UpdateGroupUserNickNameRequestParam;
import com.idream.commons.lib.dto.wangyi.WangYiCommonResponseDto;
import com.idream.commons.lib.dto.wangyi.WangYiUserInfo;
import com.idream.commons.lib.enums.WangYiEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.ActivityInfoMapper;
import com.idream.commons.lib.mapper.UserActivityRecordMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.mapper.UserWyimAccountMapper;
import com.idream.commons.lib.mapper.WximGroupMapper;
import com.idream.commons.lib.mapper.WximGroupMembersMapper;
import com.idream.commons.lib.model.ActivityInfo;
import com.idream.commons.lib.model.UserActivityRecord;
import com.idream.commons.lib.model.UserInfo;
import com.idream.commons.lib.model.UserWyimAccount;
import com.idream.commons.lib.model.WximGroup;
import com.idream.commons.lib.model.WximGroupMembers;
import com.idream.feign.FeignThirdInterfaceService;
import com.idream.service.UserIMGroupService;
import com.idream.service.UserIMService;
import com.idream.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: ZhengGaosheng
 * @Date: 2018/5/15 14:32
 * @Description:
 */
@Service
public class UserIMGroupServiceImpl implements UserIMGroupService {

    @Autowired
    private FeignThirdInterfaceService feignThirdInterfaceService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserWyimAccountMapper userWyimAccountMapper;
    @Autowired
    private WximGroupMapper wximGroupMapper;
    @Autowired
    private WximGroupMembersMapper wximGroupMembersMapper;
    @Autowired
    private UserIMService userIMService;
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;

    private final static Logger logger = LoggerFactory.getLogger(UserIMGroupServiceImpl.class);

    @Override
    public CreateGroupResponseDto createIMGroup(CreateGroupRequestParam param) {
        String title = param.getTitle();
        Integer activityId = param.getActivityId();
        //查询活动信息
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        if (activityInfo == null) {
            logger.error("活动信息不存在, activityId=" + activityId);
            throw new BusinessException(RetCodeConstants.SELECT_FAILED, "活动信息不存在");
        }
        Integer userId = activityInfo.getUserId();

        //查询当前用户有没有注册网易用户
        WangYiUserInfo imUser = userIMService.doGetIMUser(userId);

        //根据活动ID查询网易群相关信息
        WximGroup wximGroup = wximGroupMapper.selectByActivityId(activityId);
        if (wximGroup != null) {
            logger.info(JSON.toJSONString(wximGroup));
            throw new BusinessException(RetCodeConstants.IM_GROUP_ALREADY_CREATE, "当前活动已经创建了群聊");
        }

        String groupDesc = activityInfo.getTitle() + "活动的群组";
        //活动副标题
        String subtitle = activityInfo.getSubtitle();
        //群资料入库
        wximGroup = new WximGroup();
        wximGroup.setBusinessType(WangYiEnum.BusinessType.BUSINESSTYPE.getCode()); //群所属业务类型 1-活动
        wximGroup.setBusinessId(activityInfo.getId());
        wximGroup.setGroupName(title);
        wximGroup.setGroupAnnouncement(subtitle);
        wximGroup.setGroupDesc(groupDesc);
        wximGroup.setImgaeUrl(activityInfo.getImage());
        wximGroup.setInviteRole(WangYiEnum.InviteRole.MANAGER.getCode());//邀请入群权限 0-管理员(默认),1-所有人
        wximGroup.setUpdateGroupinfoRole(WangYiEnum.UpdateGroupinfoRole.MANAGER.getCode()); //群资料修改权限 0-管理员(默认),1-所有人
        Date date = new Date();
        wximGroup.setCreateTime(date);
        wximGroup.setUpdateTime(date);
        wximGroup.setJoinRole(WangYiEnum.JoinRole.NO_VERIFY.getCode());//不需要验证
        wximGroup.setInviteRole(WangYiEnum.InviteRole.EVERYBODY.getCode());//邀请人的群贤
        wximGroup.setMagreeRole(WangYiEnum.MagreeRole.NO_NEED.getCode());//不需要
        wximGroup.setMuteType(WangYiEnum.MuteType.NO_BAN.getCode()); //没有禁言
        wximGroupMapper.insert(wximGroup);

        //封装参数
        CreateGroupRequestDto dto = getCreateGroupRequestDto(title, activityInfo, imUser, wximGroup);
        //调用第三方接口创建群组
        CreateGroupResponseDto group = feignThirdInterfaceService.createGroup(dto);

        if (group != null && group.getTid().equals("806")) {
            logger.info("更换管理员的accid和token.........................................");
            //创建新的网易用户,替换admin的accid和token
            CreateUserResponseDto createUserResponseDto = createWangYiImUser(activityInfo.getUserId());
            imUser.setAccid(createUserResponseDto.getAccid());
            imUser.setToken(createUserResponseDto.getToken());
            //封装参数, 重新请求创建群
            CreateGroupRequestDto requestDto = getCreateGroupRequestDto(title, activityInfo, imUser, wximGroup);
            //调用第三方接口创建群组
            group = feignThirdInterfaceService.createGroup(requestDto);
        }
        if (group == null || StringUtils.isEmpty(group.getTid())) {
            logger.error("创建网易群组失败, params=" + JSON.toJSONString(dto));
            throw new BusinessException(RetCodeConstants.WANGYI_REFRESHTOKEN_FAILED, "创建群组失败...");
        }
        //修改网易tid
        wximGroupMapper.updateTidById(group.getTid(), wximGroup.getId());
        //群主入库
        WximGroupMembers wximGroupMembers = new WximGroupMembers();
        wximGroupMembers.setUserId(userId);
        wximGroupMembers.setAccId(imUser.getAccid());
        wximGroupMembers.setGroupId(Integer.parseInt(group.getTid()));
        wximGroupMembers.setMemo(imUser.getNickName());
        wximGroupMembers.setGroupIdentity(WangYiEnum.WangYiGroupEnum.OWNER.getCode());
        wximGroupMembers.setToken(imUser.getToken());
        wximGroupMembers.setAnExcuse(false);
        wximGroupMembers.setCreateTime(date);
        wximGroupMembers.setUpdateTime(date);
        wximGroupMembersMapper.insert(wximGroupMembers);
        return group;
    }

    private CreateGroupRequestDto getCreateGroupRequestDto(String title, ActivityInfo activityInfo, WangYiUserInfo imUser, WximGroup wximGroup) {
        CreateGroupRequestDto dto = new CreateGroupRequestDto();
        dto.setOwner(imUser.getAccid());
        dto.setIcon(activityInfo.getImage());
        dto.setAnnouncement(activityInfo.getTitle());
        dto.setIntro(wximGroup.getGroupAnnouncement());
        dto.setInvitemode(WangYiEnum.InviteRole.MANAGER.getCode().intValue());//谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
        dto.setJoinmode(WangYiEnum.JoinRole.NO_VERIFY.getCode().intValue());//群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
        dto.setMagree(WangYiEnum.MagreeRole.NO_NEED.getCode().intValue());//管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
        dto.setMembers(imUser.getAccid()); //全当创建的时候,把群主当做成员
        if (StringUtils.isNotBlank(title)) {
            dto.setTname(title);
        } else {
            dto.setTname(activityInfo.getTitle());
        }
        dto.setMsg("进入活动群...");
        dto.setUptinfomode(WangYiEnum.UpdateGroupinfoRole.MANAGER.getCode().intValue());//谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
        dto.setBeinvitemode(WangYiEnum.MagreeRole.NO_NEED.getCode().intValue());
        dto.setCustom(wximGroup.getId() + "");
        return dto;
    }

    @Override
    public int doJoinGroup(JoinGroupRequestParam param) {

        //用户id
        Integer userId = param.getUserId();
        //活动id
        Integer activityId = param.getActivityId();

        //判断用户是否参加了活动
        UserActivityRecord record = userActivityRecordMapper.getRecordByUserIdAndActivityId(userId, activityId);
        if (record == null) {
            throw new BusinessException(RetCodeConstants.USER_NOT_ATTEND_ACTIVITY, "该用户未参加本活动，无法进入群聊");
        }


        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        if (activityInfo == null) {
            logger.error("活动信息不存在, activityId=" + activityId);
            throw new BusinessException(RetCodeConstants.SELECT_FAILED, "活动信息不存在");
        }
        if (System.currentTimeMillis() > activityInfo.getEndTime().getTime()) {
            logger.error("活动已经结束了...., activityId=" + activityId);
            throw new BusinessException(RetCodeConstants.ERROR_ACTIVITEND, "活动已经结束了哦");
        }
        logger.info("加入群组用户id....................." + userId + " ...activityId: " + activityId);
        //根据活动id查询群组id
        WximGroup wximGroup = wximGroupMapper.selectByActivityId(activityId);
        if (wximGroup == null) {
            logger.error("该活动没有对应的群组....userId: " + userId + "  activityId:" + activityId);
            throw new BusinessException(RetCodeConstants.WANGYI_ADDUSERSTOGROUP_FAILED, "该活动没有对应的群组....");
        }
        String tid = wximGroup.getTid();
        WximGroupMembers wximGroupMembers1 = wximGroupMembersMapper.selectByTidAndUserId(tid, userId);
        if (wximGroupMembers1 != null) {
            logger.error("您已参加了该群组....userId :" + userId + "  tid:" + tid);
            throw new BusinessException(RetCodeConstants.WANGYI_ADDUSERSTOGROUP_FAILED, "您已参加了该群组....");
        }
        //查询当前用户信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        String nickName = userInfo.getNickName();
        //根据用户id查询用户的accid
        UserWyimAccount userWyimAccount = userWyimAccountMapper.selectByUserId(userId);
        if (userWyimAccount == null) {
            logger.error("这里是加入群组的时候创建的网易账户................" + userId);
            userIMService.doGetIMUser(userId);
            userWyimAccount = userWyimAccountMapper.selectByUserId(userId);
        }
        String accId = userWyimAccount.getAccId();
        //根据群组id查询群主的accid
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
        //群主accid
        String ownerAccid = wximGroupMembers.getAccId();

        //封装加入群组的参数
        AddUsersToGroupRequestDto requestDto = new AddUsersToGroupRequestDto();
        requestDto.setTid(tid);
        requestDto.setMembers(accId);
        requestDto.setMsg("参加活动加群啦....");
        requestDto.setMagree(WangYiEnum.MagreeRole.NO_NEED.getCode().intValue());
        requestDto.setOwner(ownerAccid);
        //调用第三方服务
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.addUsersToGroup(requestDto);
        if (wangYiCommonResponseDto == null || !wangYiCommonResponseDto.isFlag()) {
            throw new BusinessException(RetCodeConstants.WANGYI_ADDUSERSTOGROUP_FAILED, "往群组中添加成员失败...");
        }
        if (wangYiCommonResponseDto != null && "801".equals(wangYiCommonResponseDto.getMsg())) {
            throw new BusinessException(RetCodeConstants.IM_GROUP_OVERAGE, "群人数达到上限");
        }
        //入库
        WximGroupMembers groupMembers = new WximGroupMembers();
        groupMembers.setUserId(userId);
        groupMembers.setAccId(accId);
        groupMembers.setGroupId(Integer.parseInt(tid));
        groupMembers.setMemo(userInfo.getNickName());
        groupMembers.setGroupIdentity(WangYiEnum.WangYiGroupEnum.MEMBER.getCode());
        groupMembers.setAnExcuse(false);
        Date date = new Date();
        groupMembers.setCreateTime(date);
        groupMembers.setUpdateTime(date);
        //异步发送群组通知
        new Thread(() -> {
            try {
                //发送群组通知.  XXX进入了群组
                GroupAdviceRequestDto dto = new GroupAdviceRequestDto();
                dto.setFrom(ownerAccid);
                dto.setTo(tid);
                dto.setBody("{\"msg\":\"" + nickName + "加入了群聊\"}");
                dto.setOpe(WangYiEnum.Ope.GROUPMESSAGE.getCode());
                dto.setType(WangYiEnum.Ope.CUSTOMER_TYPE.getCode());
                // String ext = "{\"nikeName\":"+userInfo.getNickName()+",\"msg\":\"加入了群聊\"}";
                String ext = "{\"nikeName\":\"" + nickName + "\",\"msg\":\"加入了群聊\"}";
                dto.setExt(ext);
                WangYiCommonResponseDto groupAdvice = feignThirdInterfaceService.groupAdvice(dto);
                if (groupAdvice == null) {
                    logger.error(RetCodeConstants.WANGYI_GROUPADVICE_FAILED, "群组发送通知失败");
                }
            } catch (Exception e) {
                logger.error("发送消息异常....", e);
            }
        }).start();
        return wximGroupMembersMapper.insert(groupMembers);

    }

    @Override
    public int doLeaveGroup(LeaveGroupRequestParam param) {

        Integer activityId = param.getActivityId();
        Integer userId = param.getAuthUserInfo().getUserId();
        //查询群组id
        WximGroup wximGroup = wximGroupMapper.selectByActivityId(activityId);
        if (wximGroup == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_NOGROUP_FAILED, "该活动没有对应的群组......");
        }
        String tid = wximGroup.getTid();
        //查询群组的userId
        WximGroupMembers selectOwnerAccidByTid = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
        Integer userId1 = selectOwnerAccidByTid.getUserId();
        //如果当前活动的群组的创建者的useId和传来的useId相同, 说明是群主, 不允许退群
        if (userId1.equals(userId)) {
            logger.error("群主不能退群.......userId: " + userId + " tid: " + tid);
            throw new BusinessException(RetCodeConstants.GROUPOWNER_NOTALLOW_LEAVEGROUP, "群主不能退群......");
        }

        //查询当前用户的accid , 昵称
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectByTidAndUserId(tid, userId);
        if (wximGroupMembers == null) {
            logger.error("群组没没有该用户...userId: " + userId + " ....tid: " + tid);
            throw new BusinessException(RetCodeConstants.GROUP_NO_USER, "群组内没有该用户......");
        }
        String accId = wximGroupMembers.getAccId();
        String nickName = wximGroupMembers.getMemo();
        //将用户从群组用户表中删除
        int i = wximGroupMembersMapper.deleteByAccidAndTid(accId, tid);
        //调第三方服务
        LeaveGroupRequestDto requestDto = new LeaveGroupRequestDto();
        requestDto.setTid(tid);
        requestDto.setAccid(accId);
        WangYiCommonResponseDto responseDto = feignThirdInterfaceService.leaveGroup(requestDto);
        if (responseDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_LEAVEGROUP_FAILED, "退群失败...");
        }
        //查询群主accid
        WximGroupMembers wximGroupMembers1 = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
        String ownerAccid = wximGroupMembers1.getAccId();
        new Thread(() -> {
            try {
                //发布退群通知  XXX离开了群组
                GroupAdviceRequestDto dto = new GroupAdviceRequestDto();
                dto.setFrom(ownerAccid);
                dto.setTo(tid);
                dto.setBody("{\"msg\":\" " + nickName + " 离开了群聊... \"}");
                dto.setOpe(WangYiEnum.Ope.GROUPMESSAGE.getCode());
                dto.setType(WangYiEnum.Ope.CUSTOMER_TYPE.getCode());
                //String result = "{\"nikeName\":"+nickName+",\"msg\":\"离开了群聊\"}";
                String ext = "{\"nikeName\":\"" + nickName + "\",\"msg\":\"离开了群聊\"}";
                dto.setExt(ext);
                WangYiCommonResponseDto advice = feignThirdInterfaceService.groupAdvice(dto);
                if (advice == null) {
                    logger.error(RetCodeConstants.WANGYI_GROUPADVICE_FAILED, "群组发送通知失败");
                }
            } catch (Exception e) {
                logger.error("群组发送通知失败", e);
            }
        }).start();
        return i;
    }

    @Override
    public QueryGroupAndUsersDetailResponseDto queryGroupAndUsersDetail(String tid) {
        return feignThirdInterfaceService.queryGroupAndUsersDetail(tid);
    }

    @Override
    public int updateGroupInformation(JSONPublicParam<UpdateGroupRequestParam> param) {
        //获取用户信息
        AuthUserInfo authUserInfo = param.getAuthUserInfo();
        Integer userId = authUserInfo.getUserId();
        //获取业务参数
        UpdateGroupRequestParam requestParam = param.getRequestParam();
        //获取群组id

        String tid = requestParam.getTid();
        //查询accid
        WximGroupMembers groupMembers = wximGroupMembersMapper.selectByTidAndUserId(tid + "", userId);
        if (groupMembers == null) {
            logger.error("更新群组信息...群组内没有该用户userId:" + userId + "...tid" + tid);
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEGROUP_FAILED, "群组没有该用户");
        }
        //获取用户身份
        Byte groupIdentity = groupMembers.getGroupIdentity();
        if (groupIdentity.equals(WangYiEnum.WangYiGroupEnum.MEMBER.getCode())) {
            //吃瓜群众不能修改
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEGROUP_FAILED, "吃瓜群众没有权限修改群资料...");
        }
        //查询群组accid
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
        String ownerAccid = wximGroupMembers.getAccId();

        //业务信息
        Integer uptinfomode = requestParam.getUptinfomode();
        String icon = requestParam.getIcon();
        Integer invitemode = requestParam.getInvitemode();
        Integer magree = requestParam.getMagree();
        String announcement = requestParam.getAnnouncement();
        String intro = requestParam.getIntro();
        Integer joinmode = requestParam.getJoinmode();
        String tname = requestParam.getTname();
        Integer beinvitemode = requestParam.getBeinvitemode();
        //封装参数
        UpdateGroupRequestDto dto = new UpdateGroupRequestDto();
        dto.setTid(tid + "");
        dto.setTname(tname);
        dto.setOwner(ownerAccid);
        dto.setAnnouncement(announcement);
        dto.setIntro(intro);
        dto.setJoinmode(joinmode);
        dto.setIcon(icon);
        dto.setMagree(magree);
        dto.setInvitemode(invitemode);
        dto.setUptinfomode(uptinfomode);
        dto.setBeinvitemode(beinvitemode);
        //调用第三方服务.
        WangYiCommonResponseDto responseDto = feignThirdInterfaceService.updateGroupInformation(dto);
        if (responseDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEGROUP_FAILED, "修改群信息失败...");
        }

        WximGroup wximGroup = wximGroupMapper.selectByTid(Integer.parseInt(tid));
        //更新数据库
        wximGroup.setGroupName(tname);
        wximGroup.setGroupAnnouncement(announcement);
        wximGroup.setGroupDesc(intro);
        wximGroup.setImgaeUrl(icon);
        wximGroup.setJoinRole(joinmode.byteValue());
        wximGroup.setInviteRole(invitemode.byteValue());
        wximGroup.setUpdateGroupinfoRole(uptinfomode.byteValue());
        wximGroup.setMagreeRole(magree.byteValue());
        wximGroup.setUpdateTime(new Date());
        return wximGroupMapper.updateByPrimaryKey(wximGroup);
    }

    @Override
    public int addGroupManager(AddGroupManagerRequestParam param) {
        //获取当前用户信息
        AuthUserInfo authUserInfo = param.getAuthUserInfo();
        //当前用户id
        Integer userId = authUserInfo.getUserId();
        //当前群组id
        String tid = param.getTid();
        //要任命为管理员的userId
        String memberUserId = param.getMemberUserId();

        //查询当前操作的用户是不是管理员或者群主
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectByTidAndUserId(tid, userId);
        //获取群主accid
        String ownerAccId;
        //获取用户身份
        Byte groupIdentity = wximGroupMembers.getGroupIdentity();
        if (groupIdentity.equals(WangYiEnum.WangYiGroupEnum.MEMBER.getCode())) {
            //吃瓜群众不能添加管理员
            throw new BusinessException(RetCodeConstants.WANGYI_ADDGROUPMANAGER_FAILED, "吃瓜群众没有权限添加管理员...");
        }
        //判断当前用户是不是已经是管理员了
        WximGroupMembers groupMembers = wximGroupMembersMapper.selectByTidAndUserId(tid, Integer.parseInt(memberUserId));
        if (groupMembers.getGroupIdentity().equals(WangYiEnum.WangYiGroupEnum.MANAGER.getCode())) {
            //已经是管理员了,不必重复添加
            throw new BusinessException(RetCodeConstants.WANGYI_ADDGROUPMANAGER_FAILED, "该用户已经是管理员了...");
        }
        if (groupIdentity == WangYiEnum.WangYiGroupEnum.OWNER.getCode()) {
            ownerAccId = wximGroupMembers.getAccId();
        } else {
            //获取当前群组的群主的accid
            WximGroupMembers wximGroup = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
            ownerAccId = wximGroup.getAccId();
        }

        //查询要添加为管理员用户的accid
        WximGroupMembers members = wximGroupMembersMapper.selectByTidAndUserId(tid, Integer.parseInt(memberUserId));
        String membersAccId = members.getAccId();

        AddGroupManagerRequestDto requestDto = new AddGroupManagerRequestDto();
        requestDto.setTid(tid);
        requestDto.setMembers(membersAccId);
        requestDto.setOwner(ownerAccId);
        //调用第三方服务
        WangYiCommonResponseDto responseDto = feignThirdInterfaceService.addGroupManager(requestDto);
        if (responseDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_ADDGROUPMANAGER_FAILED, "添加管理员失败....");
        }
        //修改数据库,把身份改为管理员
        members.setGroupIdentity(WangYiEnum.WangYiGroupEnum.MANAGER.getCode());
        return wximGroupMembersMapper.updateByPrimaryKey(members);
    }

    @Override
    public int removeManagerFromGroup(RemoveManagerFromGroupRequestParam param) {
        //获取当前用户的信息
        AuthUserInfo authUserInfo = param.getAuthUserInfo();
        //当前用户的
        Integer userId = authUserInfo.getUserId();
        //群组id
        String tid = param.getTid();
        //管理员的userId
        String memberUserId = param.getMemberUserId();

        //校验当前用户是不是群主
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectByTidAndUserId(tid, userId);
        Byte groupIdentity = wximGroupMembers.getGroupIdentity();
        if (!groupIdentity.equals(WangYiEnum.WangYiGroupEnum.OWNER.getCode())) {
            throw new BusinessException(RetCodeConstants.WANGYI_ADDGROUPMANAGER_FAILED, "移除管理员失败,只有群主才能移除管理员....");
        }
        //群主accid
        String ownerAccid = wximGroupMembers.getAccId();

        //查询当前管理员的accid
        WximGroupMembers members = wximGroupMembersMapper.selectByTidAndUserId(tid, Integer.parseInt(memberUserId));
        String membersAccId = members.getAccId();
        RemoveManagerFromGroupRequestDto requestDto = new RemoveManagerFromGroupRequestDto();
        requestDto.setTid(tid);
        requestDto.setOwner(ownerAccid);
        requestDto.setMembers(membersAccId);
        //调用第三方服务
        WangYiCommonResponseDto responseDto = feignThirdInterfaceService.removeManagerFromGroup(requestDto);
        if (responseDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_ADDGROUPMANAGER_FAILED, "移除管理员失败.....");
        }
        //把管理员的身份改成吃瓜群众
        members.setGroupIdentity(WangYiEnum.WangYiGroupEnum.MEMBER.getCode());
        return wximGroupMembersMapper.updateByPrimaryKey(members);
    }

    @Override
    public int updateGroupMemberNickName(UpdateGroupUserNickNameRequestParam param) {
        //获取当前用户资料
        AuthUserInfo authUserInfo = param.getAuthUserInfo();
        //当前用户id
        Integer userId = authUserInfo.getUserId();
        //当前群组id
        String tid = param.getTid();
        //要修改为的昵称
        String nickName = param.getNickName();
        //查询当前群的群主的accid
        WximGroupMembers groupMembers = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
        String ownerAccid = groupMembers.getAccId();
        //查询当前用户的accid
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectByTidAndUserId(tid, userId);
        String accId = wximGroupMembers.getAccId();
        UpdateGroupUserNickNameRequestDto requestDto = new UpdateGroupUserNickNameRequestDto();
        requestDto.setOwner(ownerAccid);
        requestDto.setTid(tid);
        requestDto.setAccid(accId);
        requestDto.setNick(nickName);
        //调用第三方服务
        WangYiCommonResponseDto responseDto = feignThirdInterfaceService.updateTeamNick(requestDto);
        if (responseDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATETEAMNICK_FAILED, "修改昵称.....");
        }
        //入库
        wximGroupMembers.setMemo(nickName);
        return wximGroupMembersMapper.updateByPrimaryKey(wximGroupMembers);
    }

    @Override
    public WangYiCommonResponseDto muteTeam(MuteTeamRequestParam param) {
        //获取用户信息
        AuthUserInfo authUserInfo = param.getAuthUserInfo();
        Integer userId = authUserInfo.getUserId();
        String tid = param.getTid();
        Integer ope = param.getOpe();
        //查询当前用户的accid
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectByTidAndUserId(tid, userId);
        String accId = wximGroupMembers.getAccId();

        MuteTeamRequestDto muteTeamRequestDto = new MuteTeamRequestDto();
        muteTeamRequestDto.setAccid(accId);
        muteTeamRequestDto.setTid(tid);
        muteTeamRequestDto.setOpe(ope + "");
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.muteTeam(muteTeamRequestDto);
        if (muteTeamRequestDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATETEAMNICK_FAILED, "修改昵称.....");
        }
        return wangYiCommonResponseDto;
    }

    @Override
    public int doMuteTlist(MuteUserRequestParam param) {
        //获取用户信息
        AuthUserInfo authUserInfo = param.getAuthUserInfo();
        Integer userId = authUserInfo.getUserId();
        String tid = param.getTid();
        Integer mute = param.getMute();

        String muteMemberUserId = param.getUserId();

        //查询当前用户的权限
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectByTidAndUserId(tid, userId);
        Byte groupIdentity = wximGroupMembers.getGroupIdentity();
        if (groupIdentity.equals(WangYiEnum.WangYiGroupEnum.MANAGER.getCode())) {
            throw new BusinessException(RetCodeConstants.WANGYI_MUTEUSER_FAILED, "吃瓜群众没有权限禁言用户.....");
        }
        //查询当地前群的群主的accid
        WximGroupMembers groupMembers = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
        String ownerAccid = groupMembers.getAccId();
        //查询要禁言用户的accid
        WximGroupMembers muteUser = wximGroupMembersMapper.selectByTidAndUserId(tid, Integer.parseInt(muteMemberUserId));
        String muteUserAccId = muteUser.getAccId();

        MuteUserRequestDto requestDto = new MuteUserRequestDto();
        requestDto.setTid(tid);
        requestDto.setAccid(muteUserAccId);
        requestDto.setMute(mute + "");
        requestDto.setOwner(ownerAccid);
        //调第三方服务
        WangYiCommonResponseDto responseDto = feignThirdInterfaceService.muteTlist(requestDto);
        if (responseDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_MUTEUSER_FAILED, "禁言用户失败.....");
        }
        //修改群组成员的禁言状态
        if (mute == 0) {
            muteUser.setAnExcuse(false);
        } else {
            muteUser.setAnExcuse(true);
        }
        return wximGroupMembersMapper.updateByPrimaryKey(muteUser);
    }

    @Override
    public int doMuteTlistAll(MuteGroupRequestParam param) {
        //获取用户信息
        AuthUserInfo authUserInfo = param.getAuthUserInfo();
        Integer userId = authUserInfo.getUserId();
        //业务参数
        Integer tid = param.getTid();
        //查询该群组在不在
        WximGroup wximGroup1 = wximGroupMapper.selectByTid(tid);
        if (wximGroup1 == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_NOGROUP_FAILED, "没有该群组.....");
        }
        Integer muteType = param.getMuteType();
        //获取当前用户是不是群主
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectByTidAndUserId(tid + "", userId);
        if (wximGroupMembers == null) {
            throw new BusinessException(RetCodeConstants.GROUP_NO_USER, "该用户不在此群组中.....");
        }
        Byte groupIdentity = wximGroupMembers.getGroupIdentity();
        if (groupIdentity != WangYiEnum.WangYiGroupEnum.OWNER.getCode()) {
            throw new BusinessException(RetCodeConstants.WANGYI_MUTEGROUP_FAILED, "不是群主,没有权限禁言群组.....");
        }
        //群主的accid
        String ownerAccid = wximGroupMembers.getAccId();
        MuteGroupRequestDto requestDto = new MuteGroupRequestDto();
        requestDto.setOwner(ownerAccid);
        requestDto.setTid(tid + "");
        requestDto.setMuteType(muteType + "");
        //调第三方服务接口
        WangYiCommonResponseDto responseDto = feignThirdInterfaceService.muteTlistAll(requestDto);
        if (responseDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_MUTEGROUP_FAILED, "群组禁言失败.....");
        }
        //入库
        WximGroup wximGroup = wximGroupMapper.selectByTid(tid);
        wximGroup.setMuteType(muteType.byteValue());
        return wximGroupMapper.updateByPrimaryKey(wximGroup);
    }

    @Override
    public int kickOutUserFromGroup(KickOutUserFromGroupRequestParam param) {
        //获取用户信息
        AuthUserInfo authUserInfo = param.getAuthUserInfo();
        Integer userId = authUserInfo.getUserId();
        String tid = param.getTid();
        String kickUserId = param.getUserId();

        //查询当前操作的用户有没有权限踢出用户
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectByTidAndUserId(tid, userId);
        Byte groupIdentity = wximGroupMembers.getGroupIdentity();
        if (groupIdentity.equals(WangYiEnum.WangYiGroupEnum.MEMBER.getCode())) {
            throw new BusinessException(RetCodeConstants.WANGYI_KICKOUTUSERFROMGROUP_FAILED, "吃瓜群组没有权限踢出用户.....");
        }
        //获取当前群组的accid
        WximGroupMembers groupMembers = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
        String ownerAccid = groupMembers.getAccId();
        //获取要删除用户的accid
        WximGroupMembers kickGroupMember = wximGroupMembersMapper.selectByTidAndUserId(tid, Integer.parseInt(kickUserId));
        String kickAccid = kickGroupMember.getAccId();

        KickOutUserFromGroupRequestDto requestDto = new KickOutUserFromGroupRequestDto();
        requestDto.setTid(tid);
        requestDto.setOwner(ownerAccid);
        requestDto.setMember(kickAccid);
        //调用第三方接口
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.kickOutUserFromGroup(requestDto);
        if (wangYiCommonResponseDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_KICKOUTUSERFROMGROUP_FAILED, "踢出用户失败.....");
        }
        //将用户从群组用户表中删除
        return wximGroupMembersMapper.deleteByAccidAndTid(kickAccid, tid);
    }

    @Override
    public WangYiCommonResponseDto deleteGroup(DeleteGroupRequestParam param) {
        //获取用户信息
        String tid = param.getTid();
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
        if (wximGroupMembers == null) {
            logger.info("删除群组,该群组不存在.....................tid....."+tid);
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "该群组不存在");
        }
        String accId = wximGroupMembers.getAccId();
        DeleteGroupRequestDto requestDto = new DeleteGroupRequestDto();
        requestDto.setTid(tid);
        requestDto.setOwner(accId);
        WangYiCommonResponseDto responseDto = feignThirdInterfaceService.deleteGroup(requestDto);
        if (requestDto == null) {
            logger.info("解散群组失败......调用第三方接口..............................");
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "解散群组失败");
        }
        //删除群组信息
        wximGroupMapper.deleteGroupByGroupId(tid);
        //删除群内成员
        wximGroupMembersMapper.deleteByTid(tid);
        return responseDto;
    }

    @Override
    public ActivityGroupInfoResponseDto activityGroupInfo(ActivityGroupInfoRequestParam param) {
        return wximGroupMapper.selectActivityGroupInfo(param.getTid());
    }

    @Override
    public List<GroupMuteListResponseDto> groupMuteList(Integer tid) {
        return wximGroupMapper.selectgroupMuteList(tid);
    }

    @Override
    public List<JoinGroupListResposeDto> queryJoinGroupList(Integer userId) {
        return wximGroupMapper.joinGroupList(userId);
    }

    @Override
    public QueryGroupByActivityId queryGroupByActivityId(Integer activityId) {
        WximGroup wximGroup = wximGroupMapper.selectByActivityId(activityId);
        if (wximGroup == null) {
            logger.error("该活动没有对应的群组..............." + activityId);
            throw new BusinessException(RetCodeConstants.WANGYI_NOGROUP_FAILED, "该活动没有对应的群组");
        }
        String tid = wximGroup.getTid();
        QueryGroupByActivityId queryGroupByActivityId = new QueryGroupByActivityId();
        queryGroupByActivityId.setTid(tid);
        return queryGroupByActivityId;
    }

    @Override
    public QueryActivityIdByTidResponseDto queryActivityIdByTid(String tid) {
        WximGroup wximGroup = wximGroupMapper.selectByTid(Integer.parseInt(tid));
        if (wximGroup == null) {
            logger.error("没有该群组........." + tid);
            throw new BusinessException(RetCodeConstants.WANGYI_NOGROUP_FAILED, "没有该群组");
        }
        return new QueryActivityIdByTidResponseDto(wximGroup.getBusinessId() + "");
    }

    /**
     * 创建群组--> 如果该群组已经存在修改title,image
     *
     * @param accid
     * @param tid
     * @param title
     * @param image
     */
    public void updateGroupInfomation(String accid, String tid, String title, String image) {
        //调用第三方同步网易资料
        UpdateGroupRequestDto requestDto = new UpdateGroupRequestDto();
        requestDto.setTid(tid);
        requestDto.setOwner(accid);
        requestDto.setTname(title);
        requestDto.setIcon(image);
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.updateGroupInformation(requestDto);
        if (wangYiCommonResponseDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEGROUP_FAILED, "修改群信息同步网易失败...");
        }
        if (!wangYiCommonResponseDto.isFlag()) {
            logger.error("修改网易群信息失败,data = " + JSON.toJSONString(wangYiCommonResponseDto));
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEGROUP_FAILED, "修改群信息同步网易失败...");
        }
        //同步信息到本地
        int updateResult = wximGroupMapper.updateGroupNameAndImageByTid(title, image, tid);
        if (updateResult == 0) {
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEGROUP_FAILED, "修改群信息失败...");
        }
    }

    @Override
    public CreateGroupResponseDto createGroupDemo(CreateGroupRequestParam param) {


        Integer activityId = param.getActivityId();
        //查询活动信息
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        if (activityInfo == null) {
            logger.error("活动信息不存在, activityId=" + activityId);
            throw new BusinessException(RetCodeConstants.SELECT_FAILED, "活动信息不存在");
        }
        Integer userId = activityInfo.getUserId();


        QueryAccidByUserIdResponseDto queryAccidByUserIdResponseDto = userIMService.queryAccidByUserId(userId);
        String accid = queryAccidByUserIdResponseDto.getAccid();

        //封装参数
        CreateGroupRequestDto dto = new CreateGroupRequestDto();
        dto.setOwner(accid);
        dto.setIcon("icon路径....测试");
        dto.setAnnouncement("Announcement....测试");
        dto.setIntro("Intro....测试");
        dto.setInvitemode(WangYiEnum.InviteRole.MANAGER.getCode().intValue());//谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
        dto.setJoinmode(WangYiEnum.JoinRole.NO_VERIFY.getCode().intValue());//群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
        dto.setMagree(WangYiEnum.MagreeRole.NO_NEED.getCode().intValue());//管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
        dto.setMembers(accid); //全当创建的时候,把群主当做成员
        dto.setTname("群名称...");
        dto.setMsg("进入活动群...");
        dto.setUptinfomode(WangYiEnum.UpdateGroupinfoRole.MANAGER.getCode().intValue());//谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
        dto.setBeinvitemode(WangYiEnum.MagreeRole.NO_NEED.getCode().intValue());
        // dto.setCustom(wximGroup.getId() + "");
        //调用第三方接口创建群组
        CreateGroupResponseDto group = feignThirdInterfaceService.createGroup(dto);

        if (group == null || StringUtils.isEmpty(group.getTid())) {
            logger.error("创建网易群组失败, params=" + JSON.toJSONString(dto));
            throw new BusinessException(RetCodeConstants.WANGYI_REFRESHTOKEN_FAILED, "创建群组失败...");
        }

        return group;
    }

    @Override
    public int updateGroupInfo(UpdateGroupInfoRequestParam param) {

        Integer activityId = param.getActivityId();

        //查询当前活动对应的群组
        WximGroup wximGroup = wximGroupMapper.selectByActivityId(activityId);
        if (wximGroup == null) {
            logger.info("该活动没有对应的群组.....activityId: " + activityId);
            throw new BusinessException(RetCodeConstants.WANGYI_NOGROUP_FAILED, "该活动没有对应的群组");
        }
        //获取该群组的群组的tid
        String tid = wximGroup.getTid();
        //查询当前群组的群主的accid
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
        String ownerAccid = wximGroupMembers.getAccId();

        //调用第三方同步网易资料
        UpdateGroupRequestDto requestDto = new UpdateGroupRequestDto();
        requestDto.setTid(tid);
        requestDto.setTname(param.getGroupName());
        requestDto.setIcon(param.getIcon());
        requestDto.setAnnouncement(param.getAnnouncement());
        requestDto.setIntro(param.getIntro());
        requestDto.setOwner(ownerAccid);
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.updateGroupInformation(requestDto);
        if (wangYiCommonResponseDto == null) {
            logger.error("修改群信息同步网易失败............." + JSON.toJSONString(requestDto));
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEGROUP_FAILED, "修改群信息同步网易失败");
        }
        UpdateGroupInfo updateGroupInfo = new UpdateGroupInfo();
        updateGroupInfo.setId(wximGroup.getId());
        updateGroupInfo.setIcon(param.getIcon());
        updateGroupInfo.setIntro(param.getIntro());
        updateGroupInfo.setGroupName(param.getGroupName());
        updateGroupInfo.setAnnouncement(param.getAnnouncement());

        //同步信息到本地
        int updateResult = wximGroupMapper.updateGroupInfo(updateGroupInfo);
        if (updateResult == 0) {
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEGROUP_FAILED, "修改群信息失败...");
        }
        return updateResult;
    }

    @Override
    public int doJoinGroupByTid(JoinGroupByTidRequestParam param) {
        //用户id
        Integer userId = param.getAuthUserInfo().getUserId();
        //群id
        String tid = param.getTid();
        WximGroupMembers wximGroupMembers1 = wximGroupMembersMapper.selectByTidAndUserId(tid, userId);
        if (wximGroupMembers1 != null) {
            logger.error("您已参加了该群组....userId :" + userId + "  tid:" + tid);
            throw new BusinessException(RetCodeConstants.WANGYI_ADDUSERSTOGROUP_FAILED, "您已参加了该群组....");
        }
        //查询当前用户信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        String nickName = userInfo.getNickName();
        //根据用户id查询用户的accid
        UserWyimAccount userWyimAccount = userWyimAccountMapper.selectByUserId(userId);
        if (userWyimAccount == null) {
            logger.error("这里是加入群组的时候创建的网易账户................" + userId);
            userIMService.doGetIMUser(userId);
            userWyimAccount = userWyimAccountMapper.selectByUserId(userId);
        }
        String accId = userWyimAccount.getAccId();
        //根据群组id查询群主的accid
        WximGroupMembers wximGroupMembers = wximGroupMembersMapper.selectOwnerAccidByTid(tid);
        //群主accid
        String ownerAccid = wximGroupMembers.getAccId();

        //封装加入群组的参数
        AddUsersToGroupRequestDto requestDto = new AddUsersToGroupRequestDto();
        requestDto.setTid(tid);
        requestDto.setMembers(accId);
        requestDto.setMsg("参加活动加群啦....");
        requestDto.setMagree(WangYiEnum.MagreeRole.NO_NEED.getCode().intValue());
        requestDto.setOwner(ownerAccid);
        //调用第三方服务
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.addUsersToGroup(requestDto);
        if (wangYiCommonResponseDto == null || !wangYiCommonResponseDto.isFlag()) {
            throw new BusinessException(RetCodeConstants.WANGYI_ADDUSERSTOGROUP_FAILED, "往群组中添加成员失败...");
        }
        //入库
        WximGroupMembers groupMembers = new WximGroupMembers();
        groupMembers.setUserId(userId);
        groupMembers.setAccId(accId);
        groupMembers.setGroupId(Integer.parseInt(tid));
        groupMembers.setMemo(userInfo.getNickName());
        groupMembers.setGroupIdentity(WangYiEnum.WangYiGroupEnum.MEMBER.getCode());
        groupMembers.setAnExcuse(false);
        Date date = new Date();
        groupMembers.setCreateTime(date);
        groupMembers.setUpdateTime(date);
        //异步发送群组通知
        new Thread(() -> {
            try {
                //发送群组通知.  XXX进入了群组
                GroupAdviceRequestDto dto = new GroupAdviceRequestDto();
                dto.setFrom(ownerAccid);
                dto.setTo(tid);
                dto.setBody("{\"msg\":\"" + nickName + "加入了群聊\"}");
                dto.setOpe(WangYiEnum.Ope.GROUPMESSAGE.getCode());
                dto.setType(WangYiEnum.Ope.CUSTOMER_TYPE.getCode());
                // String ext = "{\"nikeName\":"+userInfo.getNickName()+",\"msg\":\"加入了群聊\"}";
                String ext = "{\"nikeName\":\"" + nickName + "\",\"msg\":\"加入了群聊\"}";
                dto.setExt(ext);
                WangYiCommonResponseDto groupAdvice = feignThirdInterfaceService.groupAdvice(dto);
                if (groupAdvice == null) {
                    logger.error(RetCodeConstants.WANGYI_GROUPADVICE_FAILED, "群组发送通知失败");
                }
            } catch (Exception e) {
                logger.error("发送消息异常....", e);
            }
        }).start();
        return wximGroupMembersMapper.insert(groupMembers);
    }

    /**
     * 创建网易账户
     *
     * @param userId
     */
    private CreateUserResponseDto createWangYiImUser(Integer userId) {
        //根据当前登录用户查询用户基本信息
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        //生成accid
        String accid = UUIDUtils.getUUID();
        //生成token
        String token = UUIDUtils.getUUID();
        requestDto.setAccid(accid);
        requestDto.setToken(token);
        logger.info("给管理员创建新的网易用户............" + JSON.toJSONString(requestDto));
        CreateUserResponseDto user = feignThirdInterfaceService.createUser(requestDto);
        if (user == null || StringUtils.isEmpty(user.getAccid())) {
            logger.error("创建网易账户失败(调用第三方接口)....................");
            throw new BusinessException(RetCodeConstants.WANGYI_REGISTRY_FAILED, "网易账户注册失败");
        }
        //更新admin的accid和token
        int result = userWyimAccountMapper.updateAdminAccidAndToken(accid, token, userId);
        if (result == 0) {
            throw new BusinessException(RetCodeConstants.WANGYI_REGISTRY_FAILED, "更新管理员网易信息失败");
        }
        return user;
    }


}

