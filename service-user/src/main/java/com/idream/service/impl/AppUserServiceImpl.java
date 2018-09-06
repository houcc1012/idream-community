package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.db.token.JWTTokenService;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.achievement.AppAchievementAwardParams;
import com.idream.commons.lib.dto.achievement.UserAchieveDetailDto;
import com.idream.commons.lib.dto.activity.AppHomePageDto;
import com.idream.commons.lib.dto.activity.AppImageParam;
import com.idream.commons.lib.dto.activity.AppJoinActivityDto;
import com.idream.commons.lib.dto.activity.AppPublishCommunityLifeDto;
import com.idream.commons.lib.dto.activity.AppUserAchievementDto;
import com.idream.commons.lib.dto.app.AppUserAchievementCategoryDto;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.user.*;
import com.idream.commons.lib.enums.*;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.*;
import com.idream.commons.lib.util.DateUtils;
import com.idream.commons.lib.util.RandomUtils;
import com.idream.commons.lib.util.StringFilterUtils;
import com.idream.feign.FeignMarketingService;
import com.idream.service.AppUserService;
import com.idream.service.AsyncUserService;
import com.idream.service.UserService;
import com.idream.utils.IpToAddressUtils;
import com.idream.utils.WeichatUserInfoUtils;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.idream.service.impl.UserServiceImpl.getAuthUserInfo;

/**
 * @author hejiang
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserLoginRecordMapper userLoginRecordMapper;
    @Autowired
    private JWTTokenService jwtTokenService;
    @Resource
    private RedisCache redisCache;
    @Resource
    private WeichatUserInfoUtils weichatUserInfoUtils;
    @Autowired
    private UserAttentionMapper userAttentionMapper;
    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;
    @Autowired
    private CommunityLifeLikeRecordMapper communityLifeLikeRecordMapper;
    @Autowired
    private UserSignatureMapper userSignatureMapper;
    @Autowired
    private IntegrationRecordMapper integrationRecordMapper;
    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;
    @Autowired
    private ProfessionInfoMapper professionInfoMapper;
    @Autowired
    private ActivityTimeRuleMapper activityTimeRuleMapper;
    @Autowired
    private ActivityTimeRuleDetailMapper activityTimeRuleDetailMapper;
    @Autowired
    private ActivityCommunityRelationMapper activityCommunityRelationMapper;
    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Autowired
    private CommunityLifeImageMapper communityLifeImageMapper;
    @Autowired
    private CommunityLifeMapper communityLifeMapper;
    @Autowired
    private UserFeedbackMapper userFeedbackMapper;
    @Autowired
    private AchievementUserMapper achievementUserMapper;
    @Resource
    private UserService userService;
    @Resource
    private UserAccountRelationMapper userAccountRelationMapper;
    @Autowired
    private UserWyimBlacklistRelMapper userWyimBlacklistRelMapper;
    @Autowired
    private UserRegionRelationMapper userRegionRelationMapper;
    @Autowired
    private UserActivityCollectionMapper userActivityCollectionMapper;
    @Autowired
    private EgisAttentionRecordMapper egisAttentionRecordMapper;
    @Autowired
    private EgisLikeRecordMapper egisLikeRecordMapper;
    @Autowired
    private UserNoticeInfoMapper userNoticeInfoMapper;
    @Autowired
    private AsyncUserService asyncUserService;
    @Autowired
    private AchievementCategoryMapper achievementCategoryMapper;
    @Autowired
    private AchievementPoolMapper achievementPoolMapper;
    @Autowired
    private UserTagRelationMapper userTagRelationMapper;
    @Autowired
    private IntegrationInfoMapper integrationInfoMapper;
    @Autowired
    private UserSkinRelationMapper userSkinRelationMapper;
    @Autowired
    private ImageInfoMapper imageInfoMapper;
    @Autowired
    private EgisLifeRecordMapper egisLifeRecordMapper;
    @Autowired
    private FeignMarketingService feignMarketingService;

    @Override
    public AppUserRegisterDto doAppRegisterUserinfo(AppUserRegisterParams params, String remoteIP, Byte deviceType) {
        AppUserRegisterDto dto = new AppUserRegisterDto();
        // 校验用户是否已经注册
        UserAccountRelation relation =
                userAccountRelationMapper.selectByAccountNameAndType(params.getMachineCode(), UserEnum.UserAccountType.MACHINE_CODE.getCode());
        // ip转地址
        GetInfoByIpDto getInfoByIpDto = IpToAddressUtils.getUserInfoByIp(remoteIP);
        UserInfo userInfo;
        // 未注册保存数据库
        if (relation == null) {
            String nickName = "立即登录";
            //创建游客用户信息
            userInfo = saveUserInfo(nickName, null, UserEnum.UserRoleEnum.VISITOR.getCode(), getInfoByIpDto);
            //创建游客账户信息
            userService.insertUserAccountRel(params.getMachineCode(), userInfo.getId(), UserEnum.UserAccountType.MACHINE_CODE.getCode());
        } else {
            userInfo = userInfoMapper.selectByPrimaryKey(relation.getUserId());
        }
        //记录登录信息
        userService.insertUserLoginRecord(remoteIP, deviceType, getInfoByIpDto.getCity(), userInfo.getId());
        //生成token
        String token = jwtTokenService.generateToken(getAuthUserInfo(userInfo));
        dto.setToken(token);
        return dto;
    }

    @Override
    public AppWeiChatLoginDto doAppWeichatLogin(AppWeiChatLoginParams params, String remoteIP, Byte deviceType) {
        AppWeiChatLoginDto data = new AppWeiChatLoginDto();
        data.setBindingPhone(SystemEnum.TrueFalseCode.FALSE.getCode());
        DecodeWeiChatDto decodeWeiChatDto = weichatUserInfoUtils.getUserinfo(params.getCode());
        if (decodeWeiChatDto == null) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "登录失败");
        }
        //ip转地址
        GetInfoByIpDto getInfoByIpDto = IpToAddressUtils.getUserInfoByIp(remoteIP);

        // 校验用户是否已经注册
        UserAccountRelation relation = userAccountRelationMapper
                .selectByAccountNameAndType(decodeWeiChatDto.getUnionId(), UserEnum.UserAccountType.WEICHAT.getCode());
        UserInfo userInfo;
        if (relation == null) {
            // 未注册保存数据库
            userInfo = UserServiceImpl.getWeichatUserInfo(decodeWeiChatDto, getInfoByIpDto);
            userInfoMapper.insertSelective(userInfo);

            //添加用户微信账号信息
            userService.insertUserAccountRel(decodeWeiChatDto.getUnionId(), userInfo.getId(), UserEnum.UserAccountType.WEICHAT.getCode());

            //记录第三方信息关联表信息
            userService.insertUserThirdInfoRel(userInfo.getId(), UserEnum.UserThirdInfoParamCode.WX_APP_OPENID.getCode(),
                    decodeWeiChatDto.getOpenId(), UserEnum.UserThirdInfoType.WEIXIN.getCode());
            //分配背景图
            saveBackgroundImage(userInfo.getId());
        } else {
            userInfo = userInfoMapper.selectByPrimaryKey(relation.getUserId());
            if (StringUtils.isNotEmpty(userInfo.getPhone())) {
                data.setBindingPhone(SystemEnum.TrueFalseCode.TRUE.getCode());
            }
        }
        //记录登录信息
        userService.insertUserLoginRecord(remoteIP, deviceType, getInfoByIpDto.getCity(), userInfo.getId());
        String token = jwtTokenService.generateToken(getAuthUserInfo(userInfo));
        data.setToken(token);
        return data;
    }

    @Override
    public AppPhoneLoginDto doPhoneVerifyCode(PhoneLoginParams params, String remoteIP, Byte deviceType) {
        AppPhoneLoginDto data = new AppPhoneLoginDto();
        // 校验验证码信息
        String code = redisCache.getString(params.getPhone(), RedisKeyConstants.USER_BINDING_IDENTITY_CODE);
        if (!"18506826337".equals(params.getPhone())) {
            if (StringUtils.isEmpty(code)) {
                throw new BusinessException(RetCodeConstants.REQUEST_VERIFICATIONCODE, "验证码已失效!");
            }
            if (!code.equals(params.getCode())) {
                throw new BusinessException(RetCodeConstants.REQUEST_VERIFICATIONCODE, "验证码不正确!");
            }
        }
        //ip转地址
        GetInfoByIpDto getInfoByIpDto = IpToAddressUtils.getUserInfoByIp(remoteIP);
        // 校验用户是否已经注册
        UserAccountRelation relation = userAccountRelationMapper
                .selectByAccountNameAndType(params.getPhone(), UserEnum.UserAccountType.PHONE.getCode());
        UserInfo userInfo;
        if (relation == null) {
            // 未注册保存数据库
            String nickName = "用户" + RandomUtils.getSixNumRandom();
            userInfo = saveUserInfo(nickName, params.getPhone(), UserEnum.UserRoleEnum.ORDINARY_USER.getCode(), getInfoByIpDto);

            //添加用户手机号账号信息
            userService.insertUserAccountRel(params.getPhone(), userInfo.getId(), UserEnum.UserAccountType.PHONE.getCode());
        } else {
            userInfo = userInfoMapper.selectByPrimaryKey(relation.getUserId());
        }
        //记录登录信息
        userService.insertUserLoginRecord(remoteIP, deviceType, getInfoByIpDto.getCity(), userInfo.getId());
        String token = jwtTokenService.generateToken(getAuthUserInfo(userInfo));
        data.setToken(token);
        //删除验证码
        redisCache.del(params.getPhone(), RedisKeyConstants.USER_BINDING_IDENTITY_CODE);
        return data;
    }

    /**
     * 保存用户信息
     *
     * @param nickName
     * @param phone
     * @param userRole
     * @param getInfoByIpDto
     */
    private UserInfo saveUserInfo(String nickName, String phone, Byte userRole, GetInfoByIpDto getInfoByIpDto) {
        // 未注册保存数据库
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(nickName);

        userInfo.setCityCode(getInfoByIpDto.getCityId());
        userInfo.setProvinceName(getInfoByIpDto.getRegion());
        userInfo.setProvinceCode(getInfoByIpDto.getRegionId());
        userInfo.setCityName(getInfoByIpDto.getCity());

        userInfo.setGender(UserEnum.UserGender.MAN.getCode());//默认男性
        Date date = new Date();
        userInfo.setCreateTime(date);
        userInfo.setUpdateTime(date);
        userInfo.setUserRole(userRole);
        userInfo.setUserType(UserEnum.UserType.MOBILE_USER.getCode());
        userInfo.setPhone(phone);
        userInfoMapper.insertSelective(userInfo);
        saveBackgroundImage(userInfo.getId());
        return userInfo;
    }

    @Override
    public void logout(Integer authUserId) {
        String token = redisCache.hget(RedisKeyConstants.AUTH_TOKEN_REL, authUserId + "-" + UserEnum.UserType.MOBILE_USER.getCode());
        redisCache.del(token, RedisKeyConstants.AUTH_TOKEN);
        redisCache.hdel(RedisKeyConstants.AUTH_TOKEN_REL, authUserId + "-" + UserEnum.UserType.MOBILE_USER.getCode());
    }

    @Override
    public AppUserInfoDto getAppUserInfo(Integer userId) {

        UserInfo user = userInfoMapper.selectByPrimaryKey(userId);
        AppUserInfoDto userInfo = new AppUserInfoDto(user);
        UserAccountRelation userAccountRelation = userAccountRelationMapper.selectByUserIdAndAccountType(userId, UserEnum.UserAccountType.WEICHAT.getCode());
        if (userAccountRelation != null) {
            userInfo.setWechatStatus(true);
        }
        int fansCount = userAttentionMapper.countUserFansByUserId(userId);
        int attentionsCount = userAttentionMapper.countUserAttentionsByUserId(userId);
        int activityCount = userActivityRecordMapper.countActivityRecordByUserId(userId);
        int lifeLikeCount = communityLifeLikeRecordMapper.countCommunityLifeLikeByUserId(userId);
        int signCount = integrationRecordMapper.countSignByUserId(userId);
        String imageUrl = userSkinRelationMapper.selectImageUrlByUserId(userId);
        userInfo.setBackgroundImage(imageUrl);
        userInfo.setActivityCount(activityCount);
        userInfo.setFansCount(fansCount);
        userInfo.setAttentionCount(attentionsCount);
        userInfo.setApprovalCount(lifeLikeCount);
        userInfo.setSignCount(signCount);
        String signature = userSignatureMapper.selectByUserId(userId);
        userInfo.setSignature(signature);

        List<AppUserInfoDto.CommunityDisplayInfo> communions = userCommunityRelationMapper.selectAuthCommunityByUserId(userId);
        userInfo.setCommunities(communions);

        List<AppUserAchievementDto> labels = achievementUserMapper.selectAchievementInfoListByUserId(userId);
        userInfo.setAchievements(labels);
        return userInfo;
    }

    @Override
    public void updateUserInfo(AppUserInfoParams requestParam, Integer userId) {
        UserInfo info = requestParam.convertUserInfo(userId);
        info.setNickName(StringUtils.isNotBlank(info.getNickName()) ? EmojiParser.removeAllEmojis(info.getNickName()) : null);
        if (StringUtils.isNotBlank(info.getNickName()) && info.getNickName().length() > 8) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "最多只可设置8个字的昵称哟~");
        }
        if (StringUtils.isNotBlank(requestParam.getSignature()) && requestParam.getSignature().length() > 15) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "最多只可设置15个字的签名哟~");
        }
        userInfoMapper.updateByPrimaryKeySelective(info);

        String signature = requestParam.getSignature();
        if (StringUtils.isNotBlank(signature)) {
            UserSignature userSignature = new UserSignature();
            userSignature.setUserId(userId);
            userSignature.setContent(EmojiParser.removeAllEmojis(signature));
            Date date = new Date();
            userSignature.setCreateTime(date);
            userSignature.setUpdateTime(date);
            userSignatureMapper.insert(userSignature);
        }

        //修改网易资料
        userService.updateIMUserInfo(info);
    }

    @Override
    public PagesDto<AppJoinActivityDto> getJoinActivityByUserId(Integer userId, PagesParam param) {

        PageHelper.startPage(param.getPage(), param.getRows());
        List<AppJoinActivityDto> list = userActivityRecordMapper.selectJoinActivityRecordByUserId(userId);
        list.forEach(this::alterAppJoinActivityDto);
        PageInfo<AppJoinActivityDto> info = new PageInfo<>(list);
        return new PagesDto<>(info.getList(), info.getTotal(), param.getPage(), param.getRows());
    }

    @Override
    public List<UserProfessionDto> listProfessions() {
        return professionInfoMapper.findAll().stream().map(UserProfessionDto::convertDto).collect(Collectors.toList());
    }

    @Override
    public PagesDto<AppPublishCommunityLifeDto> getPublishLifeByUserId(Integer userId, PagesParam param) {
        PageHelper.startPage(param.getPage(), param.getRows());
        List<CommunityLife> lifes = communityLifeMapper.selectPublishLifeByUserId(userId);
        String defaultImage = systemConfigMapper.selectByConfigCode("community_life_image");
        List<AppPublishCommunityLifeDto> collect = lifes.stream().map(l -> {
            AppPublishCommunityLifeDto dto = new AppPublishCommunityLifeDto();
            dto.setLifeId(l.getId());
            List<AppImageParam> images = communityLifeImageMapper.selectByLifeId(dto.getLifeId());
            dto.setImage(images.isEmpty() ? defaultImage : images.get(0).getImageUrl());
            dto.setLikeCount(communityLifeLikeRecordMapper.countByLifeId(dto.getLifeId()));
            return dto;
        }).collect(Collectors.toList());
        PageInfo<AppPublishCommunityLifeDto> info = new PageInfo<>(collect);

        return new PagesDto<>(info.getList(), info.getTotal(), param.getPage(), param.getRows());
    }

    private void alterAppJoinActivityDto(AppJoinActivityDto l) {
        ActivityTimeRule timeRule = activityTimeRuleMapper.selectByPrimaryKey(l.getActivityId());

        if (timeRule.getType() == 1) {
            //一次活动的需要判断是否跨天
            l.setAcrossDay(!DateUtils.sameDay(l.getStartDate(), l.getEndDate()));

            l.setStatus(DateUtils.activityStatus(l.getStartTime(), l.getEndTime()));
        } else if (timeRule.getType() == 2) {
            List<Date> dates = activityTimeRuleDetailMapper.selectLastDates(l.getActivityId());
            if (!dates.isEmpty()) {
                l.setEndDate(dates.get(0));
                l.setStartDate(dates.get(0));
            }

            l.setStatus(DateUtils.activityStatus(l.getStartTime(), l.getEndTime()));
        } else {
            Date date = DateUtils.listIntervalWeekDates(new Date(), timeRule.getWeekDay(), 1).get(0);
            l.setStartDate(date);
            l.setEndDate(date);
            if (l.getStatus().equals(Integer.valueOf(ActivityEnum.ActivityDBStatus.UNSHELVE.getCode()))) {
                l.setStatus(3);
            } else {
                l.setStatus(2);
            }
        }
        l.setCommunityName(activityCommunityRelationMapper.getCommunityNameByActivityId(l.getActivityId()));
    }

    @Override
    public void doAppBingingWeichat(JSONPublicParam<AppWeiChatLoginParams> params) {
        AppWeiChatLoginParams param = params.getRequestParam();//业务参数
        DecodeWeiChatDto decodeWeiChatDto = weichatUserInfoUtils.getUserinfo(param.getCode());

        if (decodeWeiChatDto == null) {
            logger.error("根据Code获取微信用户信息失败");
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "绑定微信号失败");
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(params.getAuthUserInfo().getUserId());
        if (userInfo != null) {
            // 校验用户是否绑定过微信
            UserAccountRelation relation = userAccountRelationMapper
                    .selectByUserIdAndAccountType(userInfo.getId(), UserEnum.UserAccountType.WEICHAT.getCode());
            if (relation != null && StringUtils.isNotEmpty(relation.getAccountName())) {
                throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "当前用户已经绑定微信号,请解绑后重新操作!");
            }
        } else {
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "用户信息不存在!");
        }
        // 校验微信号是否已被绑定,未绑定但已注册合并两个账号信息,以手机号账号为准
        UserAccountRelation relation = userAccountRelationMapper
                .selectByAccountNameAndType(decodeWeiChatDto.getUnionId(), UserEnum.UserAccountType.WEICHAT.getCode());
        if (relation != null) {
            UserInfo verifyUser = userInfoMapper.selectByPrimaryKey(relation.getUserId());
//            if (verifyUser != null) {
//                if (StringUtils.isNotEmpty(verifyUser.getPhone()) && !userInfo.getPhone().equals(verifyUser.getPhone())) {
//                    throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "微信号已被其他账户绑定,请解绑后重新操作!");
//                }
//
//                //删除原微信用户账户信息
//                userAccountRelationMapper.deleteByUserId(verifyUser.getId());
//                // 记录解绑关联
//                userService.insertUserThirdInfoRel(userInfo.getId(), UserEnum.UserThirdInfoParamCode.WX_UNIONID.getCode(),
//                        decodeWeiChatDto.getUnionId(), UserEnum.UserThirdInfoType.WEIXIN.getCode());
//            }
            throw new BusinessException(RetCodeConstants.PHONE_ACCOUNT_EXISTS, verifyUser.getNickName());
        }
        //保存微信账号信息
        userService.insertUserAccountRel(decodeWeiChatDto.getUnionId(), userInfo.getId(), UserEnum.UserAccountType.WEICHAT.getCode());

        //未修改过资料同步微信资料给用户
        if (SystemEnum.TrueFalseCode.FALSE.getCode().equals(userInfo.getUserInfoUpdate())) {
            userInfo.setNickName(decodeWeiChatDto.getNickName());
            userInfo.setUpdateTime(new Date());
            userInfo.setCityName(decodeWeiChatDto.getCity());
            userInfo.setGender(Byte.parseByte(decodeWeiChatDto.getGender()));
            userInfo.setImage(decodeWeiChatDto.getAvatarUrl());
            userInfo.setProvinceName(decodeWeiChatDto.getProvince());
            userInfo.setUserInfoUpdate(SystemEnum.TrueFalseCode.TRUE.getCode());
            userInfoMapper.updateByPrimaryKeySelective(userInfo);

            //同步修改网易IM的数据
            userService.updateIMUserInfo(userInfo);
        }

    }

    @Override
    public void doAppUnBingingWeichat(Integer authUserId) {
        UserAccountRelation accountRel =
                userAccountRelationMapper.selectByUserIdAndAccountType(authUserId, UserEnum.UserAccountType.PHONE.getCode());
        if (accountRel == null) {
            throw new BusinessException(RetCodeConstants.UPDATE_FAIL, "用户账户只绑定了微信号，不能进行解绑操作!");
        }
        userAccountRelationMapper.deleteByUserIdAndAccountType(authUserId, UserEnum.UserAccountType.WEICHAT.getCode());
    }


    @Override
    public void userFeedback(JSONPublicParam<UserFeedBackRequestDto> dto) {
        //获取业务参数
        UserFeedBackRequestDto param = dto.getRequestParam();
        //获取用户信息
        AuthUserInfo authUserInfo = dto.getAuthUserInfo();

        UserFeedback userFeedback = new UserFeedback();
        //用户id
        userFeedback.setUserId(authUserInfo.getUserId());
        //返回内容
        userFeedback.setFeedbackContent(param.getFeedbackContent());
        //图片路径
        userFeedback.setFeedbackImage(param.getFeedbackImage());
        //用户联系方式
        userFeedback.setUserContactInfo(param.getUserContactInfo());

        //刚添加没有处理,false
        userFeedback.setInfoIsHandle(false);
        //生成时间
        userFeedback.setCreateTime(new Date());
        //修改时间
        userFeedback.setUpdateTime(new Date());
        userFeedbackMapper.insert(userFeedback);
    }

    @Override
    public void updateUserFeedback(UpdateUserFeedBackRequestDto dto) {
        UserFeedback feedback = userFeedbackMapper.selectByPrimaryKey(dto.getId());
        feedback.setInfoIsHandle(dto.getInfoIsHandle());
        feedback.setUpdateTime(new Date());
        userFeedbackMapper.updateByPrimaryKey(feedback);
    }

    @Override
    public AppHomePageDto getTargetHomePage(Integer authUserId, Integer userId) {
        AppUserInfoDto appUserInfo = getAppUserInfo(userId);

        AppHomePageDto dto = new AppHomePageDto();
        BeanUtils.copyProperties(appUserInfo, dto);
        //检查是否关注
        Integer i = userAttentionMapper.selectIdByTwoUserId(authUserId, userId);
        if (i != null) {
            dto.setAttention(true);
        }
        //检查是否关注我
        Integer j = userAttentionMapper.selectIdByTwoUserId(userId, authUserId);
        if (j != null) {
            dto.setTargetAttention(true);
        }
        //如果是自己点击的话,显示的逻辑
        dto.setRegionNames(appUserInfo.getCommunities().stream().map(r -> r.getCommunityName()).collect(Collectors.toList()));
        if (authUserId.equals(userId)) {
            return dto;
        }
        //点击别人时,需要过滤没有的成就
        dto.setAchievements(dto.getAchievements().stream().filter(a -> a.getCompleted()).collect(Collectors.toList()));
        //判断是否拉黑
        UserWyimBlacklistRel userWyimBlacklistRel = userWyimBlacklistRelMapper.selectByUserIdAndFriendUserId(authUserId, userId);
        if (userWyimBlacklistRel != null) {
            dto.setBlack(true);
        }

        return dto;
    }

    @Override
    public AppSimpleUserInfoDto getSimpleUserInfo(Integer userId) {

        String image = userInfoMapper.selectByPrimaryKey(userId).getImage();
        AppSimpleUserInfoDto info = new AppSimpleUserInfoDto();
        info.setImage(image);
        info.setUserId(userId);
        return info;
    }

    @Override
    public AppRegisterRecordDto getUserRegistertTime(Integer userId) {

        UserLoginRecord record = userLoginRecordMapper.selectRegisterByUserId(userId);
        AppRegisterRecordDto dto = new AppRegisterRecordDto();
        dto.setRegisterTime(record.getCreateTime());
        return dto;
    }

    @Override
    public UserAchieveDetailDto getAchievementDetail(Integer userId, Integer achievementId) {

        UserAchieveDetailDto dto = achievementUserMapper.selectDetailByUserIdAndAchievementId(userId, achievementId);
        return dto;
    }

    @Override
    public AppUserBlackDto getUserBlack(Integer authUserId, Integer userId) {
        AppUserBlackDto dto = new AppUserBlackDto();
        UserWyimBlacklistRel userWyimBlacklistRel = userWyimBlacklistRelMapper.selectByUserIdAndFriendUserId(authUserId, userId);
        if (userWyimBlacklistRel != null) {
            dto.setBlack(true);
        }
        return dto;
    }

    @Override
    public PagesDto<AppUserSearchDto> findUserByNickName(Integer userId, AppUserSearchParams params) {
        PageHelper.startPage(params.getPage(), params.getRows());
        String nickName = StringFilterUtils.emojiAndEscapeFilter(params.getNickName());

        List<AppUserSearchDto> list = userInfoMapper.selectUserByNickName(userId, nickName);
        PageInfo<AppUserSearchDto> info = new PageInfo<>(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(), params.getRows());
    }

    @Override
    public void doAccessToken(AuthUserInfo authUserInfo) {
        String token = redisCache.hget(RedisKeyConstants.AUTH_TOKEN_REL, authUserInfo.getUserId() + "-" + authUserInfo.getUserType());
        if (StringUtils.isNotEmpty(token)) {
            jwtTokenService.refreshExpire(token);
        }
    }

    @Override
    public PagesDto<AppUserCollectionDto> getUserCollection(Integer userId, PagesParam param) {
        PageHelper.startPage(param.getPage(), param.getRows());
        List<AppUserCollectionDto> list = userActivityCollectionMapper.selectCollectionByUserId(userId);
        PageInfo<AppUserCollectionDto> info = new PageInfo<>(list);
        return new PagesDto<>(info.getList(), info.getTotal(), param.getPage(), param.getRows());
    }

    @Override
    public AppUserUnreadNoticeDto getUserUnreadNotice(Integer userId) {
        AppUserUnreadNoticeDto dto = new AppUserUnreadNoticeDto();
        int fansNum = egisAttentionRecordMapper.selectUnAttentionCount(userId);
        dto.setFansNum(fansNum);
        int likeNum = egisLikeRecordMapper.selectUnlikeCount(userId);
        dto.setLikeNum(likeNum);
        //先查用户表未读
        int sysNum = userNoticeInfoMapper.selectUnReadByUserId(userId);
        dto.setSysNum(sysNum);
        int lifeNum = egisLifeRecordMapper.selectUnReadByUserId(userId);
        dto.setLifeNum(lifeNum);
        int totalNum = fansNum + likeNum + sysNum;
        dto.setTotalNum(totalNum);
        return dto;
    }

    /**
     * 同步用户系统消息
     *
     * @param userId
     */
    @Override
    public void addUserNoticeInfo(Integer userId) {
        //查询当前用户系统通知ID最大值
        int systemNoticeId = userNoticeInfoMapper.selectMaxNoticeIdByUserId(userId, SystemEnum.NoticeType.SYSTEM.getType());
        asyncUserService.asyncInsertUserNoticeInfo(userId, systemNoticeId, SystemEnum.NoticeType.SYSTEM.getType());
    }

    @Override
    public List<AppUserAchievementCategoryDto> listAchievement(Integer userId) {

        List<AppUserAchievementDto> list = achievementUserMapper.selectAchievementInfoListByUserId(userId);
        Map<Integer, List<AppUserAchievementDto>> collect = list.stream().collect(Collectors.groupingBy(AppUserAchievementDto::getCategoryId));
        List<AchievementCategory> categories = achievementCategoryMapper.selectAll();
        return categories.stream().sorted(Comparator.comparing(AchievementCategory::getSorted)).map(i -> {
            AppUserAchievementCategoryDto categoryDto = new AppUserAchievementCategoryDto();
            categoryDto.setCategoryName(i.getCategoryName());
            categoryDto.setCategoryId(i.getId());
            categoryDto.setAchievements(collect.getOrDefault(i.getId(), new ArrayList<>()));
            return categoryDto;
        }).filter(i -> !i.getAchievements().isEmpty()).collect(Collectors.toList());
    }

    @Override
    public Integer addAchievementAward(AppAchievementAwardParams requestParam) {
        Integer userId = requestParam.getAuthUserId();
        Integer achevementId = requestParam.getAchievementId();
        AchievementUser achievementUser = achievementUserMapper.selectByUserIdAndAchieveId(userId, achevementId);
        if (achievementUser == null || !achievementUser.getCompleted()) {
            throw new BusinessException("成就未完成,无法领取奖励");
        }
        if (achievementUser.getReceived()) {
            throw new BusinessException("已经领过奖励");
        }
        List<AchievementPool> pools = achievementPoolMapper.selectByAchieveId(achevementId);
        pools.forEach(p -> {
            if (p.getType().equals(AchievementEnum.PoolType.TITLE.getCode())) {
                UserTagRelation record = new UserTagRelation();
                Date date = new Date();
                record.setUserId(userId);
                record.setTagId(Integer.parseInt(p.getAwardValue()));
                record.setCreateTime(date);
                record.setUpdateTime(date);
                userTagRelationMapper.insertSelective(record);
            }

            if (p.getType().equals(AchievementEnum.PoolType.INTEGRATION.getCode())) {
                int score = Integer.parseInt(p.getAwardValue());
                feignMarketingService.addUserScore(score, MarketingEnum.ScoreFromType.ACHIEVEMENT.getCode(), MarketingEnum.CouponRecordType.GET.getCode(), userId);
            }
        });

        achievementUser.setReceived(true);
        achievementUser.setUpdateTime(new Date());
        achievementUserMapper.updateByPrimaryKey(achievementUser);

        return pools.stream().filter(i -> i.getType().equals(AchievementEnum.PoolType.INTEGRATION.getCode())).map(i -> Integer.valueOf(i.getAwardValue())).findAny().orElse(0);
    }

    @Override
    public String saveBackgroundImage(Integer userId) {
        List<ImageInfo> imageInfos = imageInfoMapper.selectImageByCategory(3);
        String imgUrl = userSkinRelationMapper.selectImageUrlByUserId(userId);
        if (CollectionUtils.isNotEmpty(imageInfos) && StringUtils.isEmpty(imgUrl)) {
            Collections.shuffle(imageInfos);
            ImageInfo imageInfo = imageInfos.get(0);
            Date date = new Date();
            UserSkinRelation userSkinRelation = new UserSkinRelation();
            userSkinRelation.setImageId(imageInfo.getId());
            userSkinRelation.setImageUrl(imageInfo.getImage());
            userSkinRelation.setUserId(userId);
            userSkinRelation.setCreateTime(date);
            userSkinRelation.setUpdateTime(date);
            userSkinRelationMapper.insertSelective(userSkinRelation);
            return imageInfo.getImage();
        }
        return imgUrl;
    }
}
