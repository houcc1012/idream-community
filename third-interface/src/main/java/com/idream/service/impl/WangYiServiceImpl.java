package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.wangyi.*;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.service.WangYiService;
import com.idream.utils.wangyi.NIMPost;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/10 18:52
 * @Description:
 */
@Service
public class WangYiServiceImpl implements WangYiService {

    private final static Logger logger = LoggerFactory.getLogger(WangYiServiceImpl.class);

    @Value("${wangyi_appid}")
    private String wangyi_appid;

    @Value("${wangyi_secret}")
    private String wangyi_secret;

    @Override
    public CreateUserResponseDto createIMUser(CreateUserRequestDto dto) {
        String url = "https://api.netease.im/nimserver/user/create.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //用户唯一标识
        params.add(new BasicNameValuePair("accid", dto.getAccid()));
        //用户昵称
        if (dto.getName() != null) {
            params.add(new BasicNameValuePair("name", dto.getName()));
        }
        //用户icon路径
        if (dto.getIcon() != null) {
            params.add(new BasicNameValuePair("icon", dto.getIcon()));
        }
        //用户token
        if (dto.getToken() != null) {
            params.add(new BasicNameValuePair("token", dto.getToken()));
        }
        //用户性别
        if (dto.getGender() != null) {
            params.add(new BasicNameValuePair("gender", dto.getGender() + ""));
        }
        Integer extUserId = dto.getExtUserId();
        params.add(new BasicNameValuePair("ex", "{\"userId\":" + extUserId + "}"));
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("网易账户注册失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_REGISTRY_FAILED, "网易账户注册失败");
        }
        logger.info("createUser httpRes: {}", res);
        //解析返回的字符串
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是注册失败.
        if (!code.equals("200")) {
            logger.error("创建网易账户失败..............");
            throw new BusinessException(RetCodeConstants.WANGYI_REGISTRY_FAILED, "网易账户注册失败...code:  " + code + ",desc:  " + jsonObject.getString("desc"));
        }
        //注册成功返回token
        String info = jsonObject.getString("info");
        JSONObject jsonObjectInfo = JSONObject.parseObject(info);
        String token = jsonObjectInfo.getString("token");

        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setAccid(dto.getAccid());
        responseDto.setToken(dto.getToken());
        responseDto.setNickName(dto.getName());
        responseDto.setGender(dto.getGender());
        responseDto.setIcon(dto.getIcon());
        return responseDto;
    }

    @Override
    public WangYiCommonResponseDto updateIMUserInfo(UpdateIMUserInfoRequestDto dto) {
        String url = "https://api.netease.im/nimserver/user/updateUinfo.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("accid", dto.getAccid()));

        if (StringUtils.isNotBlank(dto.getName())) {
            params.add(new BasicNameValuePair("name", dto.getName()));
        }
        if (StringUtils.isNoneBlank(dto.getIcon())) {
            params.add(new BasicNameValuePair("icon", dto.getIcon()));
        }
        if (StringUtils.isNotBlank(dto.getGender())) {
            params.add(new BasicNameValuePair("gender", dto.getGender()));
        }
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("更新用户信息失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEUSERINFO_FAILED, "更新用户信息失败");
        }
        logger.info("updateIMUserInfo httpRes: {}", res);
        //解析返回的字符串
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是更新失败.
        if (!code.equals("200")) {
            logger.error("更新用户信息失败.." + dto);
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEUSERINFO_FAILED, "更新用户信息失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }

        return new WangYiCommonResponseDto(true, "更新成功");
    }

    @Override
    public WangYiCommonResponseDto addFriend(AddFriendRequestDto dto) {
        String url = "https://api.netease.im/nimserver/friend/add.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("accid", dto.getAccid()));
        params.add(new BasicNameValuePair("faccid", dto.getFaccid()));
        params.add(new BasicNameValuePair("type", "1"));
        params.add(new BasicNameValuePair("msg", dto.getMsg()));
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("添加好友失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_ADDFRIEND_FAILED, "添加好友失败");
        }
        logger.info("addFriend httpRes: {}", res);
        //解析返回的字符串
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是添加失败.
        if (!code.equals("200")) {
            logger.error("网易账户添加好友失败....................");
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEUSERINFO_FAILED, "添加好友失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "添加好友成功");
    }

    @Override
    public WangYiCommonResponseDto updateFriendAlias(UpdateFriendRequestDto dto) {
        String url = "https://api.netease.im/nimserver/friend/update.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("accid", dto.getAccid()));
        params.add(new BasicNameValuePair("faccid", dto.getFaccid()));
        params.add(new BasicNameValuePair("alias", dto.getAlias()));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("添加备注失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEFRIENDALIAS_FAILED, "添加备注失败");
        }
        logger.info("updateFriendAlias httpRes: {}", res);
        //解析返回的字符串
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是添加备注失败.
        if (!code.equals("200")) {
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEFRIENDALIAS_FAILED, "添加备注失败...code:" + code + ",  desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "添加备注成功");
    }

    @Override
    public WangYiCommonResponseDto deleteFriend(DeleteFriendRequestDto dto) {
        String url = "https://api.netease.im/nimserver/friend/delete.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("accid", dto.getAccid()));
        params.add(new BasicNameValuePair("faccid", dto.getFaccid()));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("删除好友失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEFRIEND_FAILED, "删除好友失败");
        }
        logger.info("deleteFriend httpRes: {}", res);
        //解析返回的字符串
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //返回描述
        String desc = jsonObject.getString("desc");
        if (code.equals("414") && desc.equals("not friend")) {
            logger.info(RetCodeConstants.WANGYI_DELETEFRIEND_FAILED, "重复删除好友(特殊处理)...code:" + code + ",  desc:  " + jsonObject.getString("desc"));
            return new WangYiCommonResponseDto(true, "删除好友成功");
        }
        return new WangYiCommonResponseDto(true, "删除好友成功");
    }

    @Override
    public List<IMUserInfoResponseDto> getIMUserInfo(String accids) {
        String url = "https://api.netease.im/nimserver/user/getUinfos.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        String[] split = accids.split(",");
        String accidsArray = JSON.toJSONString(split);
        params.add(new BasicNameValuePair("accids", accidsArray));
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("获取用户信息失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_GETUSERINFO_FAILED, "获取用户信息失败");
        }
        logger.info("getIMUserInfo httpRes: {}", res);
        //解析返回的字符串
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是获取用户信息失败.
        if (!code.equals("200")) {
            throw new BusinessException(RetCodeConstants.WANGYI_GETUSERINFO_FAILED, "获取用户信息失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        String uinfos = jsonObject.getString("uinfos");
        return JSONObject.parseArray(uinfos, IMUserInfoResponseDto.class);
    }

    @Override
    public WangYiCommonResponseDto addOrRemoveUserToBlackList(AddUserToBlackListRequestDto dto) {
        String url = "https://api.netease.im/nimserver/user/setSpecialRelation.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("accid", dto.getAccid()));
        params.add(new BasicNameValuePair("targetAcc", dto.getTargetAcc()));
        params.add(new BasicNameValuePair("relationType", dto.getRelationType()));
        params.add(new BasicNameValuePair("value", dto.getValue()));
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("将用户添加到黑名单失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_ADDUSERTOBLACKLIST_FAILED, "将用户添加到黑名单失败");
        }
        logger.info("addOrRemoveUserToBlackList httpRes: {}", res);
        //解析返回的字符串
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是添加到黑名单失败.
        if (!code.equals("200")) {
            logger.error("添加(移除)黑名单失败...............(0取消,1加入).." + dto.getRelationType());
            throw new BusinessException(RetCodeConstants.WANGYI_ADDUSERTOBLACKLIST_FAILED, "将用户添加到黑名单失败...code:" + code + ",  desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "将用户添加到黑名单成功");
    }

    @Override
    public RefreshTokenResponseDto refreshToken(String accid) {
        String url = "https://api.netease.im/nimserver/user/refreshToken.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("accid", accid));
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("网易账户刷新token失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_REFRESHTOKEN_FAILED, "网易账户刷新token失败");
        }
        logger.info("refreshToken httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是刷新失败.
        if (!code.equals("200")) {
            logger.error("网易账户刷新token失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_REFRESHTOKEN_FAILED, "网易账户刷新token失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        //刷新成功返回token
        String info = jsonObject.getString("info");
        JSONObject jsonObjectInfo = JSONObject.parseObject(info);
        String token = jsonObjectInfo.getString("token");
        return new RefreshTokenResponseDto(token);
    }

    @Override
    public CreateGroupResponseDto createGroup(CreateGroupRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/create.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //群名称
        params.add(new BasicNameValuePair("tname", dto.getTname()));
        //群主
        params.add(new BasicNameValuePair("owner", dto.getOwner()));

        //群成员
        String[] split = dto.getMembers().split(",");
        String members = JSON.toJSONString(split);
        params.add(new BasicNameValuePair("members", members));
        //群公告
        if (dto.getAnnouncement() != null) {
            params.add(new BasicNameValuePair("announcement", dto.getAnnouncement()));
        }
        //群描述
        if (dto.getIntro() != null) {
            params.add(new BasicNameValuePair("intro", dto.getIntro()));
        }
        //邀请加入的文字
        params.add(new BasicNameValuePair("msg", dto.getMsg()));
        //管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
        params.add(new BasicNameValuePair("magree", dto.getMagree() + ""));
        //群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
        params.add(new BasicNameValuePair("joinmode", dto.getJoinmode() + ""));
        //谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
        if (dto.getInvitemode() == null) {
            params.add(new BasicNameValuePair("invitemode", dto.getInvitemode() + ""));
        }
        //
        if (dto.getUptinfomode() == null) {
            params.add(new BasicNameValuePair("uptinfomode", dto.getUptinfomode() + ""));
        }
        //群头像
        if (dto.getIcon() != null) {
            params.add(new BasicNameValuePair("icon", dto.getIcon()));
        }
        if (dto.getBeinvitemode() != null) {
            params.add(new BasicNameValuePair("beinvitemode", dto.getBeinvitemode() + ""));
        }
        String custom = dto.getCustom();
        if (custom != null) {
            params.add(new BasicNameValuePair("custom", "{\"custom\":" + custom + "}"));
        }

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("网易云获取新失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_CREATEGROUP_FAILED, "网易云获取新失败");
        }
        logger.info("createGroup httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        if ("806".equals(code)) {
            logger.info("创建群组失败,创建群组数量达到限制(每个网易用户只能创创建群组失败,创建群组数量达到限制建100个群组)..... " + code + ",desc:" + jsonObject.getString("desc"));
            return new CreateGroupResponseDto("806");
        }
        //状态只要不是200就是创建失败.
        if (!code.equals("200")) {
            logger.error("创建群组失败..... " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_REFRESHTOKEN_FAILED, "创建群组失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        String tid = jsonObject.getString("tid");
        return new CreateGroupResponseDto(tid);
    }

    @Override
    public WangYiCommonResponseDto addUsersToGroup(AddUsersToGroupRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/add.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        //群id
        params.add(new BasicNameValuePair("tid", dto.getTid()));
        //群主
        params.add(new BasicNameValuePair("owner", dto.getOwner()));
        //群成员
        String[] split = dto.getMembers().split(",");
        String members = JSON.toJSONString(split);
        params.add(new BasicNameValuePair("members", members));
        //
        params.add(new BasicNameValuePair("magree", dto.getMagree() + ""));
        //邀请信息
        params.add(new BasicNameValuePair("msg", dto.getMsg()));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("往群组中添加成员失败.......", e);
            return new WangYiCommonResponseDto(false, "添加失败");
        }
        logger.info("addUsersToGroup httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //801 群人数达到上限
        if ("801".equals(code)) {
            return new WangYiCommonResponseDto(false, "801");
        }
        //状态只要不是200就是添加成员失败.
        if (!code.equals("200")) {
            logger.error("往群组中添加成员失败. " + code + ",desc:" + jsonObject.getString("desc"));
            return new WangYiCommonResponseDto(false, "添加失败");
        }
        return new WangYiCommonResponseDto(true, "添加成功");
    }

    @Override
    public WangYiCommonResponseDto updateGroupInformation(UpdateGroupRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/update.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //群id
        params.add(new BasicNameValuePair("tid", dto.getTid()));
        //群名称
        if (dto.getTname() != null) {
            params.add(new BasicNameValuePair("tname", dto.getTname()));
        }
        //群主
        params.add(new BasicNameValuePair("owner", dto.getOwner()));
        //群公告
        if (dto.getAnnouncement() != null) {
            params.add(new BasicNameValuePair("announcement", dto.getAnnouncement()));
        }
        //群描述
        if (dto.getIntro() != null) {
            params.add(new BasicNameValuePair("intro", dto.getIcon()));
        }
        //群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
        if (dto.getJoinmode() != null) {
            params.add(new BasicNameValuePair("joinmode", dto.getJoinmode() + ""));
        }
        //头像路径
        if (dto.getIcon() != null) {
            params.add(new BasicNameValuePair("icon", dto.getIcon()));
        }
        //管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
        if (dto.getMagree() != null) {
            params.add(new BasicNameValuePair("magree", dto.getMagree() + ""));
        }
        //谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
        if (dto.getInvitemode() != null) {
            params.add(new BasicNameValuePair("invitemode", dto.getInvitemode() + ""));
        }
        //被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414
        if (dto.getBeinvitemode() != null) {
            params.add(new BasicNameValuePair("beinvitemode", dto.getBeinvitemode() + ""));
        }

        if (dto.getUptinfomode() != null) {
            params.add(new BasicNameValuePair("uptinfomode", dto.getUptinfomode() + ""));
        }

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("更新群组信息失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEGROUP_FAILED, "更新群组信息失败");
        }
        logger.info("updateGroupInformation httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是创建失败.
        if (!code.equals("200")) {
            logger.error("更新群组信息失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_ADDUSERSTOGROUP_FAILED, "更新群组信息失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "更改成功");
    }

    @Override
    public List<GroupAndMemberListResponseDto> queryGroupAndUsersList(QueryGroupAndUsersListRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/query.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //群成员
        String[] split = dto.getTids().split(",");
        String tids = JSON.toJSONString(split);
        params.add(new BasicNameValuePair("tids", tids));
        //查询类型
        params.add(new BasicNameValuePair("ope", dto.getOpe() + ""));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("群信息与成员列表失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATEGROUP_FAILED, "群信息与成员列表失败");
        }
        logger.info("queryGroupAndUsersList httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是查询失败
        if (!code.equals("200")) {
            //   logger.error("群信息与成员列表失败. "+code+",desc:"+jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_QUERYGROUPANDUSERSLIST_FAILED, "群信息与成员列表失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        String tinfos = jsonObject.getString("tinfos");
        List<GroupAndMemberListResponseDto> groupAndMemberListResponseDtos = JSONArray.parseArray(tinfos, GroupAndMemberListResponseDto.class);
        return groupAndMemberListResponseDtos;
    }

    @Override
    public WangYiCommonResponseDto kickOutUserFromGroup(KickOutUserFromGroupRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/kick.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("tid", dto.getTid()));
        params.add(new BasicNameValuePair("owner", dto.getOwner()));
        params.add(new BasicNameValuePair("member", dto.getMember()));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("从群组中踢人失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_KICKOUTUSERFROMGROUP_FAILED, "从群组中踢人失败");
        }
        logger.info("kickOutUserFromGroup httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是查询失败
        if (!code.equals("200")) {
            logger.error("从群组中踢人失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_KICKOUTUSERFROMGROUP_FAILED, "从群组中踢人失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "踢出成功");
    }

    @Override
    public WangYiCommonResponseDto deleteGroup(DeleteGroupRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/remove.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //群组idi
        params.add(new BasicNameValuePair("tid", dto.getTid()));
        params.add(new BasicNameValuePair("owner", dto.getOwner()));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("删除群组失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "删除群组失败");
        }
        logger.info("deleteGroup httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是删除失败
        if (!code.equals("200")) {
            logger.error("删除群组失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "删除群组失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "删除成功");
    }

    @Override
    public WangYiCommonResponseDto addGroupManager(AddGroupManagerRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/addManager.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //群id
        params.add(new BasicNameValuePair("tid", dto.getTid()));
        //群主accid
        params.add(new BasicNameValuePair("owner", dto.getOwner()));

        //管理员
        String[] split = dto.getMembers().split(",");
        String members = JSON.toJSONString(split);
        params.add(new BasicNameValuePair("members", members));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("添加群组管理员失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_ADDGROUPMANAGER_FAILED, "添加群组管理员失败");
        }
        logger.info("addGroupManager httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是添加群组管理员失败
        if (!code.equals("200")) {
            logger.error("添加群组管理员失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "添加群组管理员失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "添加成功");
    }

    @Override
    public WangYiCommonResponseDto removeManagerFromGroup(RemoveManagerFromGroupRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/removeManager.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //群组id
        params.add(new BasicNameValuePair("tid", dto.getTid()));
        //群组accid
        params.add(new BasicNameValuePair("owner", dto.getOwner()));
        //管理员列表
        String[] split = dto.getMembers().split(",");
        String members = JSON.toJSONString(split);
        params.add(new BasicNameValuePair("members", members));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("移除管理员失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_REMOVEMANAGER_FAILED, "移除管理员失败");
        }
        logger.info("removeManagerFromGroup httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是移除失败
        if (!code.equals("200")) {
            logger.error("移除管理员失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "移除管理员失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "移除成功");
    }

    @Override
    public QueryGroupAndUsersDetailResponseDto queryGroupAndUsersDetail(String tid) {
        String url = "https://api.netease.im/nimserver/team/queryDetail.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tid", tid));
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("查询群组详情失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_QUERYGROUPDETAIL_FAILED, "查询群组详情失败");
        }
        logger.info("queryGroupAndUsersDetail httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是查询失败
        if (!code.equals("200")) {
            logger.error("查询群组详情失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "查询群组详情失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        String tinfo = jsonObject.getString("tinfo");
        return JSONObject.parseObject(tinfo, QueryGroupAndUsersDetailResponseDto.class);
    }

    @Override
    public JoinTeamsResponseDto getUserJoinTeams(String accid) {
        String url = "https://api.netease.im/nimserver/team/joinTeams.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("accid", accid));
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("获取用户参与的群组失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_GETUSERJOINTEAMS_FAILED, "获取用户参与的群组失败");
        }
        logger.info("getUserJoinTeams httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是获取失败
        if (!code.equals("200")) {
            logger.error("获取用户参与的群组失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_GETUSERJOINTEAMS_FAILED, "获取用户参与的群组失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return JSONObject.parseObject(res, JoinTeamsResponseDto.class);
    }

    @Override
    public WangYiCommonResponseDto changeGroupOwner(ChangeGroupOwnerRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/changeOwner.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("tid", dto.getTid()));
        params.add(new BasicNameValuePair("owner", dto.getOwner()));
        params.add(new BasicNameValuePair("newowner", dto.getNewowner()));
        params.add(new BasicNameValuePair("leave", dto.getLeave()));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("移交群主失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_CHANGEGROUPOWNER_FAILED, "移交群主失败");
        }
        logger.info("changeGroupOwner httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是移交失败
        if (!code.equals("200")) {
            logger.error("移交群主失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "移交群主失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "移交成功");
    }

    @Override
    public WangYiCommonResponseDto updateTeamNick(UpdateGroupUserNickNameRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/updateTeamNick.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("tid", dto.getTid()));
        params.add(new BasicNameValuePair("owner", dto.getOwner()));
        params.add(new BasicNameValuePair("accid", dto.getAccid()));
        params.add(new BasicNameValuePair("nick", dto.getNick()));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("修改群内用户昵称失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_UPDATETEAMNICK_FAILED, "修改群内用户昵称失败");
        }
        logger.info("updateTeamNick httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是移交失败
        if (!code.equals("200")) {
            logger.error("修改群内用户昵称失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "修改群内用户昵称失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "修改成功");
    }

    @Override
    public WangYiCommonResponseDto muteTeam(MuteTeamRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/muteTeam.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //群组id
        params.add(new BasicNameValuePair("tid", dto.getTid()));
        //要操作的群成员accid
        params.add(new BasicNameValuePair("accid", dto.getAccid()));
        //1：关闭消息提醒，2：打开消息提醒，其他值无效
        params.add(new BasicNameValuePair("ope", dto.getOpe()));
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("修改消息提醒开关.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_MUTETEAM_FAILED, "修改消息提醒开关");
        }
        logger.info("muteTeam httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是移交失败
        if (!code.equals("200")) {
            logger.error("修改消息提醒开关. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "修改消息提醒开关...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "修改成功");
    }

    @Override
    public WangYiCommonResponseDto muteTlist(MuteUserRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/muteTlist.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("tid", dto.getTid()));
        params.add(new BasicNameValuePair("owner", dto.getOwner()));
        params.add(new BasicNameValuePair("accid", dto.getAccid()));
        //1-禁言，0-解禁
        params.add(new BasicNameValuePair("mute", dto.getMute()));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("禁言用户失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_MUTEUSER_FAILED, "禁言用户失败");
        }
        logger.info("muteTlist httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是禁言失败
        if (!code.equals("200")) {
            logger.error("禁言用户失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "禁言用户失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "禁言成功");
    }

    @Override
    public WangYiCommonResponseDto muteTlistAll(MuteGroupRequestDto dto) {
        String url = "https://api.netease.im/nimserver/team/muteTlistAll.action";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tid", dto.getTid()));
        params.add(new BasicNameValuePair("owner", dto.getOwner()));
        params.add(new BasicNameValuePair("muteType", dto.getMuteType()));

        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("禁言群组失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_MUTEGROUP_FAILED, "禁言群组失败");
        }
        logger.info("muteTlistAll httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是禁言失败
        if (!code.equals("200")) {
            logger.error("禁言群组失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_DELETEGROUP_FAILED, "禁言群组失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "禁言群组成功");
    }

    @Override
    public WangYiCommonResponseDto leaveGroup(LeaveGroupRequestDto dto) {

        String url = "https://api.netease.im/nimserver/team/leave.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tid", dto.getTid()));
        params.add(new BasicNameValuePair("accid", dto.getAccid()));
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("主动退群失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_LEAVEGROUP_FAILED, "主动退群失败");
        }
        logger.info("leaveGroup httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        String desc = jsonObject.getString("desc");
        if ("414".equals(code) && "accid not team member".equals(desc)) {
            logger.info(".................退群特殊处理..............");
            return new WangYiCommonResponseDto(true, "退群成功");
        }
        //状态只要不是200就是退群失败.
        if (!code.equals("200")) {
            logger.error("主动退群失败. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_LEAVEGROUP_FAILED, "主动退群失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "退群成功");
    }

    @Override
    public WangYiCommonResponseDto groupAdvice(GroupAdviceRequestDto dto) {
        String url = "https://api.netease.im/nimserver/msg/sendMsg.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("from", dto.getFrom()));
        params.add(new BasicNameValuePair("ope", dto.getOpe()));
        params.add(new BasicNameValuePair("to", dto.getTo()));
        params.add(new BasicNameValuePair("type", dto.getType()));
        params.add(new BasicNameValuePair("body", dto.getBody()));
        params.add(new BasicNameValuePair("ext", dto.getExt()));
        HttpEntity entity;
        String res;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            res = NIMPost.postNIMServer(url, entity, wangyi_appid, wangyi_secret);
        } catch (Exception e) {
            logger.error("群组发送通知失败.......", e);
            throw new BusinessException(RetCodeConstants.WANGYI_GROUPADVICE_FAILED, "群组发送通知失败");
        }
        logger.info("groupAdvice httpRes: {}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        //获取返回的状态码
        String code = jsonObject.getString("code");
        //状态只要不是200就是群组发送通知失败失败.
        if (!code.equals("200")) {
            logger.error("发送群组通知. " + code + ",desc:" + jsonObject.getString("desc"));
            throw new BusinessException(RetCodeConstants.WANGYI_LEAVEGROUP_FAILED, "发送群组通知失败...code:" + code + ",desc:  " + jsonObject.getString("desc"));
        }
        return new WangYiCommonResponseDto(true, "发送群组通知成功");
    }
}

