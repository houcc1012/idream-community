package com.idream.service.impl;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.wangyi.AddFriendRequestDto;
import com.idream.commons.lib.dto.wangyi.AddFriendRequestParams;
import com.idream.commons.lib.dto.wangyi.AddUserToBlackListRequestDto;
import com.idream.commons.lib.dto.wangyi.AddUserToBlackListRequestParam;
import com.idream.commons.lib.dto.wangyi.CreateUserRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateUserResponseDto;
import com.idream.commons.lib.dto.wangyi.DeleteFriendRequestDto;
import com.idream.commons.lib.dto.wangyi.DeleteFriendRequestParam;
import com.idream.commons.lib.dto.wangyi.IMUserInfoResponseDto;
import com.idream.commons.lib.dto.wangyi.QueryAccidByUserIdResponseDto;
import com.idream.commons.lib.dto.wangyi.QueryBlackListResponseParams;
import com.idream.commons.lib.dto.wangyi.RemoveUserFromBlackListRequestParam;
import com.idream.commons.lib.dto.wangyi.UpdateFriendRequestDto;
import com.idream.commons.lib.dto.wangyi.UpdateFriendRequestParams;
import com.idream.commons.lib.dto.wangyi.UpdateIMUserInfoParams;
import com.idream.commons.lib.dto.wangyi.UpdateIMUserInfoRequestDto;
import com.idream.commons.lib.dto.wangyi.WangYiCommonResponseDto;
import com.idream.commons.lib.dto.wangyi.WangYiUserInfo;
import com.idream.commons.lib.enums.WangYiEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.UserComplaintHandleRecordMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.mapper.UserWyimAccountMapper;
import com.idream.commons.lib.mapper.UserWyimBlacklistRelMapper;
import com.idream.commons.lib.mapper.UserWyimFriendRelMapper;
import com.idream.commons.lib.model.UserComplaintHandleRecord;
import com.idream.commons.lib.model.UserInfo;
import com.idream.commons.lib.model.UserWyimAccount;
import com.idream.commons.lib.model.UserWyimBlacklistRel;
import com.idream.commons.lib.model.UserWyimFriendRel;
import com.idream.feign.FeignThirdInterfaceService;
import com.idream.service.UserIMService;
import com.idream.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: ZhengGaosheng
 * @date: 2018/5/14 13:32
 * @description:
 */
@Service
public class UserIMServiceImpl implements UserIMService {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private FeignThirdInterfaceService feignThirdInterfaceService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserWyimAccountMapper userWyimAccountMapper;
    @Autowired
    private UserWyimFriendRelMapper userWyimFriendRelMapper;
    @Autowired
    private UserWyimBlacklistRelMapper userWyimBlacklistRelMapper;
    @Autowired
    private UserComplaintHandleRecordMapper userComplaintHandleRecordMapper;


    @Override
    public WangYiUserInfo doGetIMUser(int authUserId) {

        //获取用户信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authUserId);
        if (userInfo == null) {
            logger.error("userId=" + authUserId);
            throw new BusinessException(RetCodeConstants.SELECT_FAILED, "用户信息不存在");
        }
        //先查询当前用户有没有注册过
        UserWyimAccount userWyimAccount = userWyimAccountMapper.selectByUserId(authUserId);
        String accid; //网易账号ID
        String token; //网易聊天token
        String nickName; //昵称
        String icon; //头像
        String gender; //性别
        //查看用户是不是禁言用户
        boolean banUserStatus = banUserStatus(authUserId);
        //判断是不是已经注册
        if (userWyimAccount == null) {
            //注册网易账号
            CreateUserResponseDto wangYiImUser = createWangYiImUser(userInfo);
            accid = wangYiImUser.getAccid();
            token = wangYiImUser.getToken();
            nickName = wangYiImUser.getNickName();
            icon = wangYiImUser.getIcon();
            gender = wangYiImUser.getGender();
        } else {
            //已经注册过了
            accid = userWyimAccount.getAccId();
            token = userWyimAccount.getToken();
            nickName = userInfo.getNickName();
            gender = userInfo.getGender() + "";
            icon = userInfo.getImage();
        }
        //返回数据
        WangYiUserInfo wangYiUserInfo = new WangYiUserInfo();
        wangYiUserInfo.setAccid(accid);
        wangYiUserInfo.setToken(token);
        wangYiUserInfo.setIcon(icon);
        wangYiUserInfo.setNickName(nickName);
        wangYiUserInfo.setGender(gender);
        wangYiUserInfo.setBanFlag(banUserStatus);
        return wangYiUserInfo;

    }

    @Override
    public List<IMUserInfoResponseDto> getIMUserInfo(int userId) {
        //根据此id获取用户信息
        UserWyimAccount userWyimAccount = userWyimAccountMapper.selectByUserId(userId);
        //获取用户的accid
        String accId = userWyimAccount.getAccId();
        return feignThirdInterfaceService.getIMUserInfo(accId);
    }

    @Override
    public WangYiCommonResponseDto updateIMUserInfo(UpdateIMUserInfoParams dto) {
        //根据用户id获取accid
        WangYiCommonResponseDto wangYiCommonResponseDto = null;
        boolean flag = false;
        try {
            UserWyimAccount userWyimAccount = userWyimAccountMapper.selectByUserId(dto.getUserId());
            //组装修改信息
            UpdateIMUserInfoRequestDto updateDto = new UpdateIMUserInfoRequestDto();
            updateDto.setAccid(userWyimAccount.getAccId());
            updateDto.setIcon(dto.getIcon());
            updateDto.setGender(dto.getGender());
            updateDto.setName(dto.getName());
            wangYiCommonResponseDto = feignThirdInterfaceService.updateIMUserInfo(updateDto);
        } catch (Exception e) {
            logger.error("更新网易用户信息失败, userID:{}", dto.getUserId(), e);
            flag = true;
        }
        if (flag || wangYiCommonResponseDto == null || !wangYiCommonResponseDto.isFlag()) {
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEUSERINFO_FAILED, "修改个人资料失败..");
        }
        return wangYiCommonResponseDto;
    }

    @Override
    public int addIMFriend(AddFriendRequestParams param) {

        Integer userId = param.getUserId();
        Integer friendUserId = param.getFriendUserId();
        //如果userid和好友的userid是相同的. 直接返回成功.
        if (userId.equals(friendUserId)) {
            return 1;
        }
        //查询当前用户网易账号信息
        UserWyimAccount myAccount = userWyimAccountMapper.selectByUserId(userId);
        if (myAccount == null) {
            logger.error("网易账号不存在(删除好友操作)...............userId: " + userId);
            throw new BusinessException(RetCodeConstants.NO_WANGYIACCOUNT, "没有该网易用户");
        }
        //查询好友网易账号信息
        UserWyimAccount friendAccunt = userWyimAccountMapper.selectByUserId(friendUserId);
        if (friendAccunt == null) {
            logger.error("网易账号不存在(删除好友操作)...............friendUserId: " + friendUserId);
            throw new BusinessException(RetCodeConstants.NO_WANGYIACCOUNT, "没有该网易用户");
        }
        //调用第三方接口服务
        AddFriendRequestDto dto = new AddFriendRequestDto();
        dto.setAccid(myAccount.getAccId());
        dto.setFaccid(friendAccunt.getAccId());
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.addFriend(dto);
        if (wangYiCommonResponseDto == null) {
            logger.error("网易账户添加好友,调用第三方接口失败(addIMFriend).........userId: " + userId + " friendUserId: " + friendUserId);
            throw new BusinessException(RetCodeConstants.WANGYI_ADDFRIEND_FAILED, "添加好友失败");
        }
        //好友关系入库
        UserWyimFriendRel wyimFriendRel = new UserWyimFriendRel();
        //当前用户的id
        wyimFriendRel.setUserId(userId);
        //好友的id
        wyimFriendRel.setFriendUserId(friendUserId);
        //备注,第一次加好友,用好友的昵称当备注
        UserInfo friendUserInfo = userInfoMapper.selectByPrimaryKey(friendUserId);
        wyimFriendRel.setFriendMemo(friendUserInfo.getNickName());
        wyimFriendRel.setCreateTime(new Date());
        return userWyimFriendRelMapper.insert(wyimFriendRel);

    }

    @Override
    public int updateFriendAlias(JSONPublicParam<UpdateFriendRequestParams> param) {
        //获取当前用户信息
        AuthUserInfo authUserInfo = param.getAuthUserInfo();
        //当前用户的userId
        Integer userId = authUserInfo.getUserId();
        //查询当前用户的accid
        UserWyimAccount userWyimAccount = userWyimAccountMapper.selectByUserId(userId);
        String accId = userWyimAccount.getAccId();

        //获取业务参数
        UpdateFriendRequestParams requestParam = param.getRequestParam();
        //获取好友的userId
        String friendUserId = requestParam.getFriendUserId();
        //查询好友的accid
        UserWyimAccount friendWyimAccount = userWyimAccountMapper.selectByUserId(Integer.parseInt(friendUserId));
        String friendAccid = friendWyimAccount.getAccId();
        //获取备注
        String alias = requestParam.getAlias();

        UpdateFriendRequestDto requestDto = new UpdateFriendRequestDto();
        requestDto.setAccid(accId);
        requestDto.setFaccid(friendAccid);
        requestDto.setAlias(alias);
        //调用第三方服务
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.updateFriendAlias(requestDto);
        if (wangYiCommonResponseDto == null) {
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEFRIENDALIAS_FAILED, "修改备注失败");
        }
        //修改好友关系表
        UserWyimFriendRel userWyimFriendRel = userWyimFriendRelMapper.selectByUserId(userId, Integer.parseInt(friendUserId));
        userWyimFriendRel.setFriendMemo(alias);
        return userWyimFriendRelMapper.updateByPrimaryKey(userWyimFriendRel);

    }

    @Override
    public int deleteFriend(DeleteFriendRequestParam param) {

        Integer userId = param.getUserId();
        Integer friendUserId = param.getFriendUserId();
        //自己关注自己, 取关的时候直接返回成功
        if (userId.equals(friendUserId)) {
            return 1;
        }
        //查询当前用户的accid
        UserWyimAccount wyimAccount = userWyimAccountMapper.selectByUserId(userId);
        if (wyimAccount == null) {
            logger.error("没有该网易用户(删除好友操作)...............userId: " + userId);
            throw new BusinessException(RetCodeConstants.NO_WANGYIACCOUNT, "没有该网易用户...");
        }
        String accId = wyimAccount.getAccId();
        //查询好友的accid
        UserWyimAccount friendWyimAccount = userWyimAccountMapper.selectByUserId(friendUserId);
        if (friendWyimAccount == null) {
            logger.error("没有该网易用户(删除好友操作)...............friendUserId: " + friendUserId);
            throw new BusinessException(RetCodeConstants.NO_WANGYIACCOUNT, "没有该网易用户...");
        }
        String friendAccId = friendWyimAccount.getAccId();

        //调第三方服务删除好友
        DeleteFriendRequestDto requestDto = new DeleteFriendRequestDto();
        requestDto.setAccid(accId);
        requestDto.setFaccid(friendAccId);
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.deleteFriend(requestDto);
        if (wangYiCommonResponseDto == null) {
            logger.error("删除好友调用第三方接口失败(deleteFriend).........userId: " + userId + " friendUserId: " + friendUserId);
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEFRIEND_FAILED, "删除好友失败");
        }
        //好友关系表中删除好友关系.
        return userWyimFriendRelMapper.deleteByUserIdAndFriendUserId(userId, friendUserId);

    }

    @Override
    public int addUserToBlackList(JSONPublicParam<AddUserToBlackListRequestParam> param) {
        Integer authUserId = param.getAuthUserInfo().getUserId();
        Integer friendUserId = param.getRequestParam().getUserId();

        //先查询是不是已经在黑名单了
        UserWyimBlacklistRel userWyimBlacklistRel1 = userWyimBlacklistRelMapper.selectByUserIdAndFriendUserId(authUserId, friendUserId);
        if (userWyimBlacklistRel1 != null) {
            throw new BusinessException(RetCodeConstants.WANGYI_ADDUSERTOBLACKLIST_FAILED, "该用户已经在您的黑名单了...");
        }
        //查询当前用户的accid
        UserWyimAccount userWyimAccount = userWyimAccountMapper.selectByUserId(authUserId);
        if (userWyimAccount == null) {
            logger.error("没有该网易用户(添加黑名单操作)...............authUserId: " + authUserId);
            throw new BusinessException(RetCodeConstants.NO_WANGYIACCOUNT, "没有该网易用户...");
        }
        String accId = userWyimAccount.getAccId();
        //查询要拉黑用户的accid
        UserWyimAccount friendWyimAccount = userWyimAccountMapper.selectByUserId(friendUserId);
        if (friendWyimAccount == null) {
            logger.error("没有该网易用户(添加黑名单操作)...............friendUserId: " + friendUserId);
            throw new BusinessException(RetCodeConstants.NO_WANGYIACCOUNT, "没有该网易用户...");
        }
        String friendAccid = friendWyimAccount.getAccId();

        AddUserToBlackListRequestDto blackListRequestDto = new AddUserToBlackListRequestDto();
        blackListRequestDto.setAccid(accId);
        blackListRequestDto.setTargetAcc(friendAccid);
        blackListRequestDto.setRelationType(WangYiEnum.RelationType.BLACKLIST.getCode());
        blackListRequestDto.setValue(WangYiEnum.BlackOrMuteType.ADD_BLACKLIST_MUTE.getCode());
        //调用第三方接口
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.addOrRemoveUserToBlackList(blackListRequestDto);
        if (wangYiCommonResponseDto == null) {
            logger.error("网易用户添加黑名单失败(调第三方接口)........authUserId: " + authUserId + " friendUserId: " + friendUserId);
            throw new BusinessException(RetCodeConstants.WANGYI_ADDUSERTOBLACKLIST_FAILED, "将用户添加到黑名单失败");
        }
        //根据好友id查出好友对象
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(friendUserId);
        //获取该好友的昵称
        String friendNickName = userInfo.getNickName();
        //插入黑名单表
        UserWyimBlacklistRel userWyimBlacklistRel = new UserWyimBlacklistRel();
        userWyimBlacklistRel.setUserId(authUserId);
        userWyimBlacklistRel.setBlacklistUserId(friendUserId);
        userWyimBlacklistRel.setMemo(friendNickName);
        userWyimBlacklistRel.setCreateTime(new Date());
        return userWyimBlacklistRelMapper.insert(userWyimBlacklistRel);
    }

    @Override
    public int removeUserFromBlackList(JSONPublicParam<RemoveUserFromBlackListRequestParam> param) {

        Integer authUserId = param.getAuthUserInfo().getUserId();
        Integer friendUserId = param.getRequestParam().getUserId();
        //查询当前用户的accid
        UserWyimAccount userWyimAccount = userWyimAccountMapper.selectByUserId(authUserId);
        if (userWyimAccount == null) {
            logger.error("没有该网易用户(移除黑名单操作)...............authUserId: " + authUserId);
            throw new BusinessException(RetCodeConstants.NO_WANGYIACCOUNT, "没有该网易用户...");
        }
        String accId = userWyimAccount.getAccId();
        //查询要拉黑用户的accid
        UserWyimAccount friendWyimAccount = userWyimAccountMapper.selectByUserId(friendUserId);
        if (friendWyimAccount == null) {
            logger.error("没有该网易用户(移除黑名单操作)...............friendUserId: " + friendUserId);
            throw new BusinessException(RetCodeConstants.NO_WANGYIACCOUNT, "没有该网易用户...");
        }
        String friendAccid = friendWyimAccount.getAccId();

        AddUserToBlackListRequestDto blackListRequestDto = new AddUserToBlackListRequestDto();
        blackListRequestDto.setAccid(accId);
        blackListRequestDto.setTargetAcc(friendAccid);
        blackListRequestDto.setRelationType(WangYiEnum.RelationType.BLACKLIST.getCode());
        blackListRequestDto.setValue(WangYiEnum.BlackOrMuteType.REMOVE_BLACKLIST_MUTE.getCode());
        //调用第三方接口
        WangYiCommonResponseDto wangYiCommonResponseDto = feignThirdInterfaceService.addOrRemoveUserToBlackList(blackListRequestDto);
        if (wangYiCommonResponseDto == null) {
            logger.error("网易用户移除黑名单失败(调第三方接口)........authUserId: " + authUserId + " friendUserId: " + friendUserId);
            throw new BusinessException(RetCodeConstants.WANGYI_REMOVEUSERFROMBLACKLIST_FAILED, "黑名单移除用户失败");
        }
        return userWyimBlacklistRelMapper.deleteByUserIdAndFriendUserId(authUserId, friendUserId);

    }

    @Override
    public List<QueryBlackListResponseParams> queryBlackListByUserId(int authUserId) {
        return userWyimBlacklistRelMapper.queryBlackListByUserId(authUserId);
    }

    @Override
    public QueryAccidByUserIdResponseDto queryAccidByUserId(Integer userId) {
        QueryAccidByUserIdResponseDto responseDto = new QueryAccidByUserIdResponseDto();
        UserWyimAccount userWyimAccount = userWyimAccountMapper.selectByUserId(userId);
        String accid;
        if (userWyimAccount == null) {
            //获取用户信息
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            if (userInfo == null) {
                logger.error("userId=" + userId);
                throw new BusinessException(RetCodeConstants.SELECT_FAILED, "用户信息不存在");
            }
            //注册网易账号
            CreateUserResponseDto wangYiImUser = createWangYiImUser(userInfo);
            accid = wangYiImUser.getAccid();
        } else {
            accid = userWyimAccount.getAccId();
        }
        responseDto.setAccid(accid);
        return responseDto;
    }

    /**
     * 注册网易用户
     *
     * @param userInfo
     */
    private CreateUserResponseDto createWangYiImUser(UserInfo userInfo) {
        //根据当前登录用户查询用户基本信息
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        //生成accid
        String accid = UUIDUtils.getUUID();
        //生成token
        String token = UUIDUtils.getUUID();
        requestDto.setAccid(accid);
        requestDto.setToken(token);
        requestDto.setName(userInfo.getNickName());
        requestDto.setIcon(userInfo.getImage());
        requestDto.setGender(userInfo.getGender() + "");
        requestDto.setExtUserId(userInfo.getId());
        CreateUserResponseDto user = feignThirdInterfaceService.createUser(requestDto);
        if (user == null || StringUtils.isEmpty(user.getAccid())) {
            logger.error("创建网易账户失败(调用第三方接口)....................");
            throw new BusinessException(RetCodeConstants.WANGYI_REGISTRY_FAILED, "网易账户注册失败");
        }
        //网易用户资料本地入库
        UserWyimAccount userWyimAccount = new UserWyimAccount();
        userWyimAccount.setAccId(accid);
        userWyimAccount.setToken(token);
        userWyimAccount.setUserId(userInfo.getId());
        Date date = new Date();
        userWyimAccount.setCreateTime(date);
        userWyimAccount.setUpdateTime(date);
        userWyimAccountMapper.insertSelective(userWyimAccount);
        return user;
    }


    /**
     * 查询当前用户是不是被禁言
     *
     * @param authUserId
     */
    private boolean banUserStatus(Integer authUserId) {
        UserComplaintHandleRecord userComplaintHandleRecord = userComplaintHandleRecordMapper.queryUserBanStatus(authUserId);
        //查询到实体为null说明没有被禁言
        if (userComplaintHandleRecord == null) {
            return false;
        }
        return true;
    }
}

