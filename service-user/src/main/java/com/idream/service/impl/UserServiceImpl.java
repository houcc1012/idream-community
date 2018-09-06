package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.db.token.JWTTokenService;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.AdminUserAchievementDto;
import com.idream.commons.lib.dto.region.RegionCommunityDto;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.user.AdminClientManageUserListDto;
import com.idream.commons.lib.dto.user.AdminClientManageUserListParams;
import com.idream.commons.lib.dto.user.AdminClientUserInfoParams;
import com.idream.commons.lib.dto.user.AdminClientUserListDto;
import com.idream.commons.lib.dto.user.AdminClientUserListParams;
import com.idream.commons.lib.dto.user.AmapIPLocationDto;
import com.idream.commons.lib.dto.user.DecodeWeiChatDto;
import com.idream.commons.lib.dto.user.DefaultUserLocationDto;
import com.idream.commons.lib.dto.user.GetInfoByIpDto;
import com.idream.commons.lib.dto.user.ManagerInfoDto;
import com.idream.commons.lib.dto.user.MiniProgramSendTemplateParams;
import com.idream.commons.lib.dto.user.MiniProgramUserFormIdParams;
import com.idream.commons.lib.dto.user.MiniUserInfoDto;
import com.idream.commons.lib.dto.user.OperateManageUserParams;
import com.idream.commons.lib.dto.user.ProfessionInfoDto;
import com.idream.commons.lib.dto.user.UpdateUserInfoParams;
import com.idream.commons.lib.dto.user.UseIntergralDto;
import com.idream.commons.lib.dto.user.UserAuthorizeParams;
import com.idream.commons.lib.dto.user.UserCommunityRelationDto;
import com.idream.commons.lib.dto.user.UserDetailDto;
import com.idream.commons.lib.dto.user.UserManagerDto;
import com.idream.commons.lib.dto.user.UserRegisterDto;
import com.idream.commons.lib.dto.user.UserRegisterParams;
import com.idream.commons.lib.dto.user.UserTagDto;
import com.idream.commons.lib.dto.wangyi.UpdateIMUserInfoParams;
import com.idream.commons.lib.dto.wangyi.WangYiCommonResponseDto;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.enums.UserEnum.UserRoleEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.AchievementUserMapper;
import com.idream.commons.lib.mapper.ActivityInfoMapper;
import com.idream.commons.lib.mapper.ImageInfoMapper;
import com.idream.commons.lib.mapper.IntegrationRecordMapper;
import com.idream.commons.lib.mapper.ProfessionInfoMapper;
import com.idream.commons.lib.mapper.RegionGroupInfoMapper;
import com.idream.commons.lib.mapper.RegionGroupRelationMapper;
import com.idream.commons.lib.mapper.RegionInfoMapper;
import com.idream.commons.lib.mapper.UserAccountMapper;
import com.idream.commons.lib.mapper.UserAccountRelationMapper;
import com.idream.commons.lib.mapper.UserCommunityRelationMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.mapper.UserLoginRecordMapper;
import com.idream.commons.lib.mapper.UserManagerMapper;
import com.idream.commons.lib.mapper.UserMiniprogramFormidMapper;
import com.idream.commons.lib.mapper.UserSkinRelationMapper;
import com.idream.commons.lib.mapper.UserStatisticsMapper;
import com.idream.commons.lib.mapper.UserTagMapper;
import com.idream.commons.lib.mapper.UserThirdInfoRelMapper;
import com.idream.commons.lib.model.ActivityInfo;
import com.idream.commons.lib.model.ImageInfo;
import com.idream.commons.lib.model.ProfessionInfo;
import com.idream.commons.lib.model.RegionGroupInfo;
import com.idream.commons.lib.model.RegionInfo;
import com.idream.commons.lib.model.UserAccount;
import com.idream.commons.lib.model.UserAccountRelation;
import com.idream.commons.lib.model.UserInfo;
import com.idream.commons.lib.model.UserLoginRecord;
import com.idream.commons.lib.model.UserManager;
import com.idream.commons.lib.model.UserMiniprogramFormid;
import com.idream.commons.lib.model.UserSkinRelation;
import com.idream.commons.lib.model.UserStatistics;
import com.idream.commons.lib.model.UserThirdInfoRel;
import com.idream.commons.lib.util.AESClientUtils;
import com.idream.commons.lib.util.AmapUtils;
import com.idream.commons.lib.util.DateUtils;
import com.idream.commons.lib.util.IdentityExtractorUtils;
import com.idream.commons.lib.util.IdentityValidatorUtils;
import com.idream.commons.lib.util.PageRowsUtils;
import com.idream.commons.lib.util.RandomUtils;
import com.idream.rabbit.SmsSendService;
import com.idream.service.UserIMService;
import com.idream.service.UserLoginService;
import com.idream.service.UserService;
import com.idream.utils.IpToAddressUtils;
import com.idream.utils.WeichatMiniProgramUtils;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.bouncycastle.cms.RecipientId.password;

@Service
public class UserServiceImpl implements UserService {

    @Value("${PhoneTime}")
    public Integer phoneTime;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ActivityInfoMapper activityInfoMapper;

    @Autowired
    private RedisCache redisCache;

    @Resource
    private ProfessionInfoMapper professionInfoMapper;

    @Resource
    private JWTTokenService jwtTokenService;

    @Resource
    private SmsSendService smsSendService;

    @Resource
    private UserTagMapper userTagMapper;

    @Resource
    private IntegrationRecordMapper integrationRecordMapper;

    @Resource
    private UserLoginRecordMapper userLoginRecordMapper;

    @Resource
    private UserManagerMapper userManagerMapper;

    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;

    @Resource
    private WeichatMiniProgramUtils weichatMiniProgramUtils;

    @Resource
    private UserMiniprogramFormidMapper userMiniprogramFormidMapper;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private UserThirdInfoRelMapper userThirdInfoRelMapper;

    @Resource
    private UserLoginService userLoginService;
    @Resource
    private UserService userService;
    @Resource
    private UserStatisticsMapper userStatisticsMapper;
    @Resource
    private AchievementUserMapper achievementUserMapper;
    @Autowired
    private RegionGroupRelationMapper regionGroupRelationMapper;
    @Autowired
    private RegionInfoMapper regionInfoMapper;
    @Autowired
    private RegionGroupInfoMapper regionGroupInfoMapper;
    @Autowired
    private ImageInfoMapper imageInfoMapper;
    @Autowired
    private UserSkinRelationMapper userSkinRelationMapper;
    @Autowired
    private UserIMService userIMService;

    @Value("${spring.cloud.config.profile}")
    private String environment;

    @Resource
    private UserAccountRelationMapper userAccountRelationMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public MiniUserInfoDto getUserInfo(int userId) {
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(userId);
        MiniUserInfoDto dto = new MiniUserInfoDto();
        BeanUtils.copyProperties(userInfo, dto);
        dto.setUserId(userId);
        return dto;
    }

    @Override
    public JSONPublicDto<UserRegisterDto> doRegisterUserinfo(UserRegisterParams userRegisterParams, String remoteIP) {

        // 解密获取微信用户信息
        DecodeWeiChatDto decodeWeiChatDto = weichatMiniProgramUtils
                .getMiniProgramUserInfo(userRegisterParams.getEncryptedData(), userRegisterParams.getIv(), userRegisterParams.getCode());
        if (decodeWeiChatDto == null) {
            throw new BusinessException("获取微信用户信息失败, params:{}", JSON.toJSONString(userRegisterParams));
        }
        UserRegisterDto userRegisterDto = null;
        // 校验用户是否已经注册
        UserAccountRelation relation = userAccountRelationMapper
                .selectByAccountNameAndType(decodeWeiChatDto.getUnionId(), UserEnum.UserAccountType.WEICHAT.getCode());
        //ip转地址
        GetInfoByIpDto getInfoByIpDto = IpToAddressUtils.getUserInfoByIp(remoteIP);
        UserInfo userInfo;
        if (relation == null) {
            // 组装微信用户信息
            userInfo = getWeichatUserInfo(decodeWeiChatDto, getInfoByIpDto);
            userInfoMapper.insertSelective(userInfo);

            //添加用户微信账号信息
            insertUserAccountRel(decodeWeiChatDto.getUnionId(), userInfo.getId(), UserEnum.UserAccountType.WEICHAT.getCode());

            //记录第三方信息关联表信息
            insertUserThirdInfoRel(userInfo.getId(), UserEnum.UserThirdInfoParamCode.WX_MINI_PROGRAM_OPENID.getCode(),
                    decodeWeiChatDto.getOpenId(), UserEnum.UserThirdInfoType.WEIXIN.getCode());
            //设置背景图
            saveBackgroudImage(userInfo.getId());

        } else {
            userInfo = userInfoMapper.selectByPrimaryKey(relation.getUserId());
        }
        // 返回token和用户信息
        userRegisterDto = getUserLoginDto(decodeWeiChatDto.getOpenId(), decodeWeiChatDto.getUnionId(), userInfo);
        //记录登录信息
        insertUserLoginRecord(remoteIP, SystemEnum.ClientChannelEnum.WECHAT.getCode(), getInfoByIpDto.getCity(), userInfo.getId());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "新用户登陆", userRegisterDto);
    }

    @Override
    public void insertUserThirdInfoRel(Integer userId, String paramCode, String paramValue, Byte type) {
        Date date = new Date();
        UserThirdInfoRel rel = new UserThirdInfoRel();
        rel.setUserId(userId);
        rel.setParamCode(paramCode);
        rel.setParamValue(paramValue);
        rel.setUpdateTime(date);
        rel.setType(type);
        rel.setCreateTime(date);
        userThirdInfoRelMapper.insertSelective(rel);
    }

    public static UserInfo getWeichatUserInfo(DecodeWeiChatDto decodeWeiChatDto, GetInfoByIpDto getInfoByIpDto) {
        Date date = new Date();
        // 保存数据库
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(decodeWeiChatDto.getNickName());

        String city = getInfoByIpDto.getCity();
        userInfo.setCityName(city);
        userInfo.setCityCode(getInfoByIpDto.getCityId());
        userInfo.setProvinceName(getInfoByIpDto.getRegion());
        userInfo.setProvinceCode(getInfoByIpDto.getRegionId());

        userInfo.setGender(Byte.parseByte(decodeWeiChatDto.getGender()));
        userInfo.setImage(decodeWeiChatDto.getAvatarUrl());
        userInfo.setCreateTime(date);
        userInfo.setUpdateTime(date);
        userInfo.setUserRole(UserEnum.UserRoleEnum.ORDINARY_USER.getCode());
        userInfo.setUserType(UserEnum.UserType.MOBILE_USER.getCode());
        return userInfo;
    }

    /**
     * 新增用户账户信息
     *
     * @param accountName
     * @param userId
     * @param accountType
     */
    @Override
    public boolean insertUserAccountRel(String accountName, Integer userId, Byte accountType) {
        UserAccountRelation relation = userAccountRelationMapper.selectByUserIdAndAccountType(userId, accountType);
        if (relation == null) {
            Date date = new Date();
            //保存用户关联账号信息
            relation = new UserAccountRelation();
            relation.setCreateTime(date);
            relation.setAccountName(accountName);
            relation.setUpdateTime(date);
            relation.setUserId(userId);
            relation.setAccountType(accountType);
            int result = userAccountRelationMapper.insertSelective(relation);
            if (result > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 组装小程序用户注册登录新消息
     *
     * @param openId
     * @param unionId
     * @param userInfo @return
     */
    private UserRegisterDto getUserLoginDto(String openId, String unionId, UserInfo userInfo) {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setOpenId(openId);
        userRegisterDto.setToken(jwtTokenService.generateToken(getAuthUserInfo(userInfo)));
        userRegisterDto.setUnionId(unionId);
        return userRegisterDto;
    }

    /**
     * 组装生成token用的用户信息
     *
     * @param userInfo
     */
    public static AuthUserInfo getAuthUserInfo(UserInfo userInfo) {
        AuthUserInfo info = new AuthUserInfo();
        info.setNickName(userInfo.getNickName());
        info.setCity(userInfo.getCityName());
        info.setGender(userInfo.getGender() + "");
        info.setUserId(userInfo.getId());
        info.setUserType(UserEnum.UserType.MOBILE_USER.getCode());
        info.setUserRole(userInfo.getUserRole());
        return info;
    }

    /**
     * 获取手机验证码
     */
    @Override
    public JSONPublicDto getIdentifyCode(int authUserId, String phone) {
        // 校验用户手机号是否注册
        UserInfo info = userInfoMapper.selectByPrimaryKey(authUserId);
        if (info == null) {
            return JSONPublicDto.returnErrorData(RetCodeConstants.ERROR_PHONEEXISTENCE, "用户信息不存在!");
        }
        if (StringUtils.isNotEmpty(info.getPhone()) && !phone.equals(info.getPhone())) {
            String str = info.getPhone().substring(0, 3) + "****" + info.getPhone().substring(7);
            return JSONPublicDto.returnErrorData(RetCodeConstants.ERROR_PHONEEXISTENCE, "该用户已绑定手机号码(" + str + ")!");
        }
        //获取手机验证码
        getPhoneVerifyCode(phone);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "发送成功", null);
    }

    /**
     * 获取手机验证码
     *
     * @param phone
     */
    @Override
    public void getPhoneVerifyCode(String phone) {
        // 获取验证码
        String identityCode = RandomUtils.getRandom();
        if ("test".equals(environment) || "dev".equals(environment)) {
            identityCode = "1111";
        }
        redisCache.setex(phone, RedisKeyConstants.USER_BINDING_IDENTITY_CODE, phoneTime, identityCode);
        //发送验证码
        smsSendService.sendSms(phone, SystemEnum.SmsSendType.VERIFY_CODE.getCode(), identityCode);
    }

    /**
     * 用户绑定手机号,返回新的token
     */
    @Override
    public String doBindingPhone(int authUserId, String phone, String receiveCode) {

        // 校验验证码信息
        String code = redisCache.getString(phone, RedisKeyConstants.USER_BINDING_IDENTITY_CODE);
        if (StringUtils.isEmpty(code)) {
            throw new BusinessException(RetCodeConstants.REQUEST_VERIFICATIONCODE, "验证码已失效!");
        }
        if (!code.equals(receiveCode)) {
            throw new BusinessException(RetCodeConstants.REQUEST_VERIFICATIONCODE, "验证码不正确!");
        }
        //查询用户信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authUserId);
        if (userInfo == null) {
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "用户账号不存在!");
        }
        if (StringUtils.isNotEmpty(userInfo.getPhone())) {
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "当前用户已经绑定过手机号!");
        }
        //查询用户微信unionID
        UserAccountRelation userRelation =
                userAccountRelationMapper.selectByUserIdAndAccountType(userInfo.getId(), UserEnum.UserAccountType.WEICHAT.getCode());
        if (userRelation == null) {
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "该用户不是微信登录用户!");
        }
        String token;
        //判断手机号是否已经是注册用户
        UserInfo info = userInfoMapper.selectByPhone(phone);
        if (info != null) {
//            //校验是否已经关联微信账号
//            UserAccountRelation relation =
//                    userAccountRelationMapper.selectByUserIdAndAccountType(info.getId(), UserEnum.UserAccountType.WEICHAT.getCode());
//            if (relation != null) {
//                throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "该手机号已经绑定过微信号!");
//            }
//            //未修改过资料同步微信用户信息到本地资料
//            if (SystemEnum.TrueFalseCode.FALSE.getCode().equals(info.getUserInfoUpdate())) {
//                info.setNickName(userInfo.getNickName());
//                info.setUpdateTime(new Date());
//                info.setBirthday(userInfo.getBirthday());
//                info.setCityCode(userInfo.getCityCode());
//                info.setCityName(userInfo.getCityName());
//                info.setDistrictCode(userInfo.getDistrictCode());
//                info.setDistrictName(userInfo.getDistrictName());
//                info.setGender(userInfo.getGender());
//                info.setImage(userInfo.getImage());
//                info.setProvinceCode(userInfo.getProvinceCode());
//                info.setProvinceName(userInfo.getProvinceName());
//                info.setUserRole(UserRoleEnum.ORDINARY_USER.getCode());
//                userInfoMapper.updateByPrimaryKeySelective(info);
//
//                //同步修改网易IM的数据
//                updateIMUserInfo(info);
//            } else {
//                //修改用户角色名
//                userInfoMapper.updateUserRoleByUserId(UserRoleEnum.ORDINARY_USER.getCode(), info.getId());
//            }
//            //生成用户微信账号信息
//            insertUserAccountRel(userRelation.getAccountName(), info.getId(), UserEnum.UserAccountType.WEICHAT.getCode());
////            //删除小程序用户信息
////            userInfoMapper.deleteByPrimaryKey(userInfo.getId());
//            //删除小程序用户账号信息
//            userAccountRelationMapper.deleteByUserId(userInfo.getId());
//            // 记录解绑关联
//            userService.insertUserThirdInfoRel(userInfo.getId(), UserEnum.UserThirdInfoParamCode.WX_UNIONID.getCode(),
//                    userRelation.getAccountName(), UserEnum.UserThirdInfoType.WEIXIN.getCode());
//
//            //重新生成新的token
//            token = jwtTokenService.generateToken(getAuthUserInfo(info));
            throw new BusinessException(RetCodeConstants.PHONE_ACCOUNT_EXISTS, info.getNickName());
        } else {
            //生成用户手机号账号信息
            insertUserAccountRel(phone, userInfo.getId(), UserEnum.UserAccountType.PHONE.getCode());

            //手机号不存在,手机号存入用户信息
            userInfo.setPhone(phone);
//            userInfo.setUserRole(UserRoleEnum.ORDINARY_USER.getCode());
            userInfo.setUpdateTime(new Date());
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
            //重新生成新的token
            token = jwtTokenService.generateToken(getAuthUserInfo(userInfo));
        }
        //删除验证码
        redisCache.del(phone, RedisKeyConstants.USER_BINDING_IDENTITY_CODE);
        return token;
    }

    /**
     * 记录登录记录
     *
     * @param remoteIP
     * @param deviceType
     * @param cityName
     * @param userId
     */
    @Override
    public void insertUserLoginRecord(String remoteIP, Byte deviceType, String cityName, Integer userId) {
        //记录登录信息
        UserLoginRecord record = new UserLoginRecord();
        if (StringUtils.isEmpty(cityName)) {
            //ip转地址
            GetInfoByIpDto getInfoByIpDto = IpToAddressUtils.getUserInfoByIp(remoteIP);
            record.setCity(getInfoByIpDto.getCity());
        } else {
            record.setCity(cityName);
        }
        record.setCreateTime(new Date());
        record.setUserId(userId);
        record.setDevice(deviceType);
        record.setIp(remoteIP);
        userLoginRecordMapper.insertSelective(record);
    }

    /**
     * 编辑资料
     */
    @Override
    public void updateUserInfo(JSONPublicParam<UpdateUserInfoParams> params) {
        // 用户信息
        AuthUserInfo userInfo = params.getAuthUserInfo();
        // 业务参数
        UpdateUserInfoParams updateUserInfoParams = params.getRequestParam();
        UserInfo info = new UserInfo();
        info.setNickName(StringUtils.isNotBlank(updateUserInfoParams.getNickName()) ? EmojiParser.removeAllEmojis(updateUserInfoParams.getNickName()) : null);
        info.setGender(updateUserInfoParams.getGender());
        info.setProfessionId(updateUserInfoParams.getProfessionId());

        ProfessionInfo professionInfo = professionInfoMapper.selectByPrimaryKey(updateUserInfoParams.getProfessionId());
        if (professionInfo != null) {
            info.setProfessionName(professionInfo.getName());
        }

        info.setBirthday(DateUtils.getDate(updateUserInfoParams.getBirthday(), DateUtils.YYYY_MM_DD));
        info.setId(userInfo.getUserId());
        info.setUserInfoUpdate(SystemEnum.TrueFalseCode.TRUE.getCode());
        userInfoMapper.updateByPrimaryKeySelective(info);

        //同步修改网易IM的数据
        updateIMUserInfo(info);
    }

    @Override
    public void updateAdminClientUserInfo(JSONPublicParam<AdminClientUserInfoParams> params) {
        AuthUserInfo authUserInfo = params.getAuthUserInfo();
        AdminClientUserInfoParams param = params.getRequestParam();
        //查询用户账号信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authUserInfo.getUserId());
        if (userInfo == null) {
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "用户不存在！");
        }
        //查询用户账号信息
        UserAccount userAccount = userAccountMapper.selectByUserId(userInfo.getId());
        if (userAccount == null) {
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "用户账号不存在！");
        }
        //校验账号是否被占用
        UserAccountRelation relation = userAccountRelationMapper.selectByAccountName(param.getAccountName());
        if (relation != null && !relation.getUserId().equals(userInfo.getId())) {
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "该账号已被占用！");
        }
        //查询用户关联表信息
        List<UserAccountRelation> relations = userAccountRelationMapper.selectByUserId(userInfo.getId());
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(relations)) {
            logger.error("账号关联表不存在, params:{}", JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "用户账号不存在！");
        }
        //校验账号是否被修改
        boolean accountFlag = relations.stream().anyMatch(r -> r.getAccountName().equals(param.getAccountName()));
        //未修改
        if (!accountFlag) {
            //有修改账号情况
            int result = userAccountRelationMapper
                    .updateAccountNameByUserIdAndAccountType(param.getAccountName(), userInfo.getId(), UserEnum.UserAccountType.ORDINARY.getCode());
            if (result == 0) {
                userService.insertUserAccountRel(param.getAccountName(), userInfo.getId(), UserEnum.UserAccountType.ORDINARY.getCode());
            }
        }
        //修改密码
        String passwordStr;
        //检验密码
        checkPassword(param.getPassword());
        try {
            passwordStr = AESClientUtils.aesEncrypt(param.getPassword());
        } catch (Exception e) {
            logger.error("密码加密失败！password=" + param.getPassword());
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "修改账号密码失败！");
        }
        //修改密码
        userAccountMapper.updatePasswordByUserId(passwordStr, userInfo.getId());
        //退出登录
        userLoginService.userLogout(userInfo.getId());
    }

    private void checkPassword(String password) {
        String regex = "^[a-zA-Z0-9_]{6,20}$";
        boolean matches = password.matches(regex);
        if (!matches) {
            throw new BusinessException(RetCodeConstants.USER_PASSWORD_ILLEGAL, "密码非法.密码长度6-20位,数字,字母下划线");
        }
    }

    /**
     * 查询职业列表
     */
    @Override
    public List<ProfessionInfoDto> findProfessionList() {
        List<ProfessionInfo> professionInfos = professionInfoMapper.findAll();
        List<ProfessionInfoDto> dtos = null;
        if (CollectionUtils.isNotEmpty(professionInfos)) {
            dtos = getChildProfessionInfo(0, professionInfos);
        }
        return dtos;
    }

    /**
     * @Author: hejiang
     * @Description: 处理职业数据
     * @Date: 19:31 2018/3/30
     */
    private List<ProfessionInfoDto> getChildProfessionInfo(int parentPid, List<ProfessionInfo> professionInfos) {
        List<ProfessionInfoDto> childDtos = Lists.newArrayList();
        for (ProfessionInfo pi : professionInfos) {
            if (parentPid == pi.getPid()) {
                ProfessionInfoDto pd = new ProfessionInfoDto();
                pd.setId(pi.getId());
                pd.setName(pi.getName());
                pd.setPid(pi.getPid());
                pd.setChildProfessionInfos(getChildProfessionInfo(pi.getId(), professionInfos));
                childDtos.add(pd);
            }
        }
        return childDtos;
    }

    /**
     * 查询客户端用户列表
     *
     * @param params
     */
    @Override
    public PagesDto<AdminClientUserListDto> getClientUserList(AdminClientUserListParams params) {
        //分页
        //  PageHelper.startPage(params.getPage(), params.getRows());
        List<AdminClientUserListDto> list = userInfoMapper.selectClientUserList(params);
        StringBuilder userIds = new StringBuilder();
        Map<Integer, List<UserCommunityRelationDto>> map = new HashMap<Integer, List<UserCommunityRelationDto>>();
        list.forEach(dto -> {
            List<UserCommunityRelationDto> communityRelations = new ArrayList<>();
            map.put(dto.getId(), communityRelations);
            dto.setCommunities(communityRelations);
            userIds.append(dto.getId()).append(",");
        });
        if (userIds.toString().endsWith(",")) {
            userIds.deleteCharAt(userIds.length() - 1);
        }
        if (userIds.length() > 0) {
            List<UserCommunityRelationDto> communityList = userCommunityRelationMapper.selectBatchCommunityList(userIds.toString(), params.getStatus());
            communityList.forEach(item -> {
                if (map.get(item.getUserId()) != null) {
                    map.get(item.getUserId()).add(item);
                }
            });
        }
        if (params.getIsComplainted() != null && params.getIsComplainted() == 1) {
            //查看非禁言 过滤掉禁言
            list.removeIf(item -> item.getComplainted() == 1);
        } else if (params.getIsComplainted() != null && params.getIsComplainted() == 2) {
            //查看禁言  过滤掉非禁言
            list.removeIf(item -> item.getComplainted() == 0);
        }
        if (params.getIsHasCommunity() != null && params.getIsHasCommunity() == 1) {
            //查看有认证社区 过滤掉没有认证社区
            list.removeIf(item -> CollectionUtils.isEmpty(item.getCommunities()));
        } else if (params.getIsHasCommunity() != null && params.getIsHasCommunity() == 2) {
            //查看无认证社区 过滤掉有认证认证
            list.removeIf(item -> CollectionUtils.isNotEmpty(item.getCommunities()));
        }
        // PageInfo<AdminClientUserListDto> info = new PageInfo<>(list);
        List<AdminClientUserListDto> listDtos = PageRowsUtils.getPageObj(list, params.getPage(), params.getRows());
        return new PagesDto<>(listDtos, list.size(), params.getPage(), params.getRows());
    }

    /**
     * 查询客户端用户详情
     *
     * @param userId
     */
    @Override
    public UserDetailDto getUserDetail(Integer userId) {
        UserInfo user = userInfoMapper.selectByPrimaryKey(userId);
        if (user == null) {
            logger.info("用户信息未找到!userID=" + userId);
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "用户信息未找到!");
        }
        //查询用户登录信息
        UserDetailDto userDetailDto = userInfoMapper.selectUserDetailsById(userId);
        return userDetailDto;
    }

    /**
     * @Author: hejiang
     * @Description: 查询用户积分
     * @Date: 18:40 2018/4/15
     */
    @Override
    public PagesDto<UseIntergralDto> getUserIntergrals(Integer userId, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<UseIntergralDto> list = integrationRecordMapper.selectIntegrationByUserId(userId);
        PageInfo<UseIntergralDto> info = new PageInfo<>(list);
        return new PagesDto<>(list, info.getTotal(), page, rows);
    }

    /**
     * @Author: hejiang
     * @Description: 查询用户标签信息
     * @Date: 18:41 2018/4/15
     */
    @Override
    public List<UserTagDto> getUserTagList(Integer userId) {
        List<UserTagDto> userTages = userTagMapper.selectUserTagsByUserId(userId);
        return userTages;
    }

    /**
     * @Author: hejiang
     * @Description: 查询管理者列表
     * @Date: 18:41 2018/4/15
     */
    @Override
    public PagesDto<AdminClientManageUserListDto> getClientManageUserList(AdminClientManageUserListParams params) {
        //分页
        PageHelper.startPage(params.getPage(), params.getRows());
        List<AdminClientManageUserListDto> list = userInfoMapper.selectClientManageUserList(params);
        PageInfo<AdminClientManageUserListDto> info = new PageInfo<>(list);
        return new PagesDto<>(list, info.getTotal(), params.getPage(), params.getRows());
    }

    /**
     * @Author: hejiang
     * @Description: 用户授权
     * @Date: 18:40 2018/4/15
     */
    @Override
    public int doUserAuthorize(JSONPublicParam<UserAuthorizeParams> params) {
        UserAuthorizeParams param = params.getRequestParam();
        UserInfo user = userInfoMapper.selectByPrimaryKey(param.getUserId());
        if (user == null) {
            logger.info("用户信息未找到! params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "用户信息未找到!");
        }
        //修改证件号和真实姓名
        userInfoMapper.updateIdentityAndRealNameByUserId(param.getIdentity(), param.getRealName(), param.getUserId());

        //根据姓名和证件号检查管理者是否重复
        UserManager userManager = userManagerMapper.selectByUserId(user.getId());
        int result = 0;
        if (userManager != null) {
            logger.info("管理者已存在! params=" + JSON.toJSONString(params));
            if (SystemEnum.TrueFalseCode.TRUE.getCode().equals(userManager.getStatus())) {
                throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "管理员已授权,无需重复授权!");
            }
            result = userManagerMapper.updateStatusById(SystemEnum.TrueFalseCode.TRUE.getCode(), userManager.getId());
            //修改管理员后台账号状态
            int count = userAccountMapper.updateAccountStatusByUserId(UserEnum.AdminAccountStatus.NORMAL.getCode(), userManager.getUserId());
            if (count == 0) {
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "管理员授权失败!");
            }
        } else {
            UserManager manager = new UserManager();
            manager.setUserId(param.getUserId());
            Date date = new Date();
            manager.setUpdateTime(date);
            manager.setCreateTime(date);
            manager.setStatus(SystemEnum.TrueFalseCode.TRUE.getCode());
            result = userManagerMapper.insertSelective(manager);

            //创建管理员账号，并发送短信
            createUserAccount(user);
        }
        userInfoMapper.updateUserRoleByUserId(UserEnum.UserRoleEnum.MANAGEER.getCode(), user.getId());
        if (result == 0) {
            throw new BusinessException(RetCodeConstants.SAVE_FAILED, "管理员授权失败");
        }
        return result;
    }

    /**
     * @Author: hejiang
     * @Description: 新增管理员
     * @Date: 22:11 2018/4/15
     */
    @Override
    public void addUserManage(JSONPublicParam<OperateManageUserParams> params) {
        OperateManageUserParams param = params.getRequestParam();
        //新增用户
        UserInfo userInfo = userInfoMapper.selectByPhone(param.getPhone());
        if (userInfo != null) {
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "手机号码（" + param.getPhone() + ")已存在！");
        }
        //校验身份证号码
        boolean isVali = IdentityValidatorUtils.isValidatedAllIdcard(param.getIdentity());
        if (!isVali) {
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "无效身份证号码!");
        }
        //组装用户信息
        userInfo = createManagerUserInfo(param);
        userInfoMapper.insertSelective(userInfo);

        //组装管理员信息
        UserManager manager = getUserManager(userInfo.getId(), param.getBookHouseId(), SystemEnum.TrueFalseCode.TRUE.getCode());
        userManagerMapper.insertSelective(manager);

        //创建管理员账号，并发送短信
        createUserAccount(userInfo);
        //新增网易IM账号
        userIMService.doGetIMUser(userInfo.getId());
    }

    /**
     * 创建管理员信息
     *
     * @param userId
     * @param bookId
     * @param status
     */
    private UserManager getUserManager(Integer userId, Integer bookId, Byte status) {
        Date date = new Date();
        UserManager manager = new UserManager();
        manager.setUpdateTime(date);
        manager.setUserId(userId);
        manager.setBookId(bookId);
        manager.setCreateTime(date);
        manager.setStatus(status);
        return manager;
    }

    /**
     * 组装管理者用户信息
     *
     * @param param
     */
    private UserInfo createManagerUserInfo(OperateManageUserParams param) {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(param.getNickName());
        userInfo.setCityName(param.getCity());
        userInfo.setPhone(param.getPhone());
        //解析身份证号码获得性别和生日
        userInfo.setGender(IdentityExtractorUtils.getGender(param.getIdentity()));
        userInfo.setProfessionId(null);
        userInfo.setImage(null);
        Date date = new Date();
        userInfo.setCreateTime(date);
        userInfo.setUpdateTime(date);
        userInfo.setRealName(param.getRealName());
        userInfo.setCityCode(param.getCityCode());
        userInfo.setDistrictCode(param.getDistrictCode());
        userInfo.setDistrictName(param.getDistrict());
        userInfo.setProvinceCode(param.getProvinceCode());
        userInfo.setProvinceName(param.getProvince());
        userInfo.setIdentity(param.getIdentity());
        userInfo.setBirthday(IdentityExtractorUtils.getBirthday(param.getIdentity()));
        userInfo.setUserRole(UserEnum.UserRoleEnum.MANAGEER.getCode());
        userInfo.setUserType(UserEnum.UserType.MOBILE_USER.getCode());
        return userInfo;
    }


    /**
     * @Author: hejiang
     * @Description: 创建管理员账号
     * @Date: 14:29 2018/4/16
     */
    private void createUserAccount(UserInfo userInfo) {
        //组装管理员账号信息
        UserAccount account = new UserAccount();
        Date date = new Date();
        account.setUpdateTime(date);
        account.setCreateTime(date);
        account.setStatus(UserEnum.AdminAccountStatus.NORMAL.getCode());
        account.setUserId(userInfo.getId());
        String password = RandomUtils.getSixNumRandom();
        if ("test".equals(environment) || "dev".equals(environment)) {
            password = "123456";
        }
        String passwordStr;
        try {
            passwordStr = AESClientUtils.aesEncrypt(password);
        } catch (Exception e) {
            logger.error("密码加密失败！password=" + password);
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "新增管理者失败，重试！");
        }
        account.setPassword(passwordStr);
        UserAccount userAccount = userAccountMapper.selectByUserId(userInfo.getId());
        if (userAccount == null) {
            userAccountMapper.insertSelective(account);
        } else {
            account.setId(userAccount.getId());
            userAccountMapper.updateByPrimaryKeySelective(account);
        }
        // 账号关联信息
        UserAccountRelation relation =
                userAccountRelationMapper.selectByUserIdAndAccountType(userInfo.getId(), UserEnum.UserAccountType.PHONE.getCode());
        if (relation == null) {
            //创建账号关联信息
            userService.insertUserAccountRel(userInfo.getPhone(), userInfo.getId(), UserEnum.UserAccountType.PHONE.getCode());
        }
        //发送短信
        try {
            smsSendService.sendSms(userInfo.getPhone(), SystemEnum.SmsSendType.USER_ACCOUNT_PASSWORD.getCode(), password);
        } catch (Exception e) {
            logger.error("发送短信失败！password=" + password, e);
        }
    }

    /**
     * @Author: hejiang
     * @Description: 编辑管理员
     * @Date: 22:11 2018/4/15
     */
    @Override
    public void updateUserManage(JSONPublicParam<OperateManageUserParams> params) {
        //业务参数
        OperateManageUserParams param = params.getRequestParam();
        //查询
        UserManager manager = userManagerMapper.selectByPrimaryKey(param.getId());
        if (manager == null) {
            logger.info("管理者不存在! params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "管理员不存在!");
        }
        //校验身份证号码
        boolean isVali = IdentityValidatorUtils.isValidatedAllIdcard(param.getIdentity());
        if (!isVali) {
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "无效身份证号码!");
        }
        //修改管理员信息
        Date date = new Date();
        manager.setUpdateTime(date);
        manager.setBookId(param.getBookHouseId());
        manager.setId(param.getId());
        userManagerMapper.updateByPrimaryKeySelective(manager);

        //修改管理者用户信息
        UserInfo userInfo = createManagerUserInfo(param);
        userInfo.setId(manager.getUserId());
        userInfo.setUserInfoUpdate(SystemEnum.TrueFalseCode.TRUE.getCode());
        userInfoMapper.updateByPrimaryKeySelective(userInfo);

        //修改网易IM账号信息
        updateIMUserInfo(userInfo);
    }

    /**
     * @Author: hejiang
     * @Description: 取消授权
     * @Date: 22:11 2018/4/15
     */
    @Override
    public void doCancelAuthorize(Integer authUserId, Integer id) {
        //查询
        UserManager manager = userManagerMapper.selectByPrimaryKey(id);
        if (manager == null) {
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "管理员不存在!");
        } else {
            if (SystemEnum.TrueFalseCode.FALSE.getCode().equals(manager.getStatus())) {
                throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "管理员未授权,无需取消!");
            }
            int result = userManagerMapper.updateStatusById(SystemEnum.TrueFalseCode.FALSE.getCode(), manager.getId());
            if (result == 0) {
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "取消授权失败!");
            }
            //修改管理员后台账号状态
            int count = userAccountMapper.updateAccountStatusByUserId(UserEnum.AdminAccountStatus.NOT_OPEN.getCode(), manager.getUserId());
            if (count == 0) {
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "取消授权失败!");
            }
        }
        userInfoMapper.updateUserRoleByUserId(UserRoleEnum.ORDINARY_USER.getCode(), manager.getUserId());
    }

    /**
     * @Author: hejiang
     * @Description: 查询管理员
     * @Date: 23:15 2018/4/15
     */
    @Override
    public UserManagerDto getUserManage(Integer authUserId, Integer id) {
        return userManagerMapper.selectUserManagerDtoByUserIdAndId(id);
    }

    @Override
    public void doAgainAuthorize(Integer authUserId, Integer id) {
        //查询用户信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authUserId);

        //查询
        UserManager manager = userManagerMapper.selectByPrimaryKey(id);
        if (manager == null) {
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "管理员不存在!");
        } else {
            if (SystemEnum.TrueFalseCode.TRUE.getCode().equals(manager.getStatus())) {
                throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "管理员已授权,无需重复授权!");
            }
            int result = userManagerMapper.updateStatusById(SystemEnum.TrueFalseCode.TRUE.getCode(), manager.getId());
            if (result == 0) {
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "授权失败!");
            }
            //修改管理员后台账号状态
            int count = userAccountMapper.updateAccountStatusByUserId(UserEnum.AdminAccountStatus.NORMAL.getCode(), manager.getUserId());
            if (count == 0) {
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "授权失败!");
            }
        }
        userInfoMapper.updateUserRoleByUserId(UserEnum.UserRoleEnum.MANAGEER.getCode(), manager.getUserId());
        //发送短信
        try {
            UserAccount account = userAccountMapper.selectByUserId(manager.getUserId());
            String password = AESClientUtils.aesDecrypt(account.getPassword());
            smsSendService.sendSms(userInfo.getPhone(), SystemEnum.SmsSendType.USER_ACCOUNT_PASSWORD.getCode(), password);
        } catch (Exception e) {
            logger.error("发送短信失败！password=" + password, e);
        }
    }

    @Override
    public ManagerInfoDto getManagerInfo(Integer authUserId) {
        // 返回管理员的用户信息
        UserInfo info = userInfoMapper.selectByPrimaryKey(authUserId);

        if (!info.getUserRole().equals(UserRoleEnum.MANAGEER.getCode())) {
            throw new BusinessException(RetCodeConstants.USER_MANAGER_NO_EXIST, "当前用户不是管理员");
        }
        ManagerInfoDto dto = new ManagerInfoDto();
        dto.setUserId(authUserId);
        dto.setNickName(info.getNickName());
        dto.setRealName(info.getRealName());
        dto.setPhone(info.getPhone());
        //查询账号信息
        UserAccountRelation relation = userAccountRelationMapper.selectByUserIdAndAccountType(authUserId, UserEnum.UserAccountType.ORDINARY.getCode());
        if (relation != null) {
            dto.setAccountName(relation.getAccountName());
        } else {
            dto.setAccountName(info.getPhone());
        }

        UserManager manager = userManagerMapper.selectByUserId(authUserId);
        if (manager == null || manager.getStatus().equals(SystemEnum.TrueFalseCode.FALSE.getCode()) || manager.getBookId() == null || manager.getBookId() == 0) {
            return dto;
        }
        dto.setBookId(manager.getBookId());

        if (dto.getBookId() != null) {
            List<Integer> regionIds = regionGroupRelationMapper.selectRegionIdsByGroupId(dto.getBookId());
            List<RegionInfo> regionInfos = regionIds.stream().map(i -> regionInfoMapper.selectByPrimaryKey(i)).collect(Collectors.toList());
            List<RegionCommunityDto> communitys = regionIds.stream().flatMap(i -> regionGroupRelationMapper.selectCommunityByRegionId(i).stream()).collect(Collectors.toList());
            RegionGroupInfo regionGroupInfo = regionGroupInfoMapper.selectByPrimaryKey(dto.getBookId());
            dto.setBookHouse(regionGroupInfo.getName());
            dto.setAdCode(regionGroupInfo.getDistrictCode());
            dto.setCommunities(communitys);
            dto.setRegions(regionInfos);
        }

        return dto;
    }

    /**
     * 保存小程序用户FormId
     *
     * @param params
     */
    @Override
    public void addMiniProgramFormId(JSONPublicParam<List<MiniProgramUserFormIdParams>> params) {
        //保存数据
        userMiniprogramFormidMapper.addBatchFormId(params.getAuthUserInfo().getUserId(), params.getRequestParam());
    }

    /**
     * @param
     */

    @Override
    public void sendMiniProgramTemplateMessage(JSONPublicParam<MiniProgramSendTemplateParams> param) {
        int userId = param.getAuthUserInfo().getUserId();
        MiniProgramSendTemplateParams templateParams = param.getRequestParam();
        //查询
        String openId = userThirdInfoRelMapper.selectUserOpenIdByUserId(userId, UserEnum.UserThirdInfoParamCode.WX_MINI_PROGRAM_OPENID.getCode());
        if (openId == null || openId.isEmpty()) {
            throw new BusinessException(RetCodeConstants.MINI_PROGRAM_OPEN_ID_NOT_EXIST, "openid不存在!");
        }
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(templateParams.getActivityId());
        if (activityInfo == null) {
            throw new BusinessException(RetCodeConstants.ACTIVITY_NOT_EXIST, "活动不存在!");
        }
        List<UserMiniprogramFormid> formidList = userMiniprogramFormidMapper.selectByUserId(userId);
        if (formidList == null || formidList.isEmpty()) {
            throw new BusinessException(RetCodeConstants.FORM_ID_NOT_EXIST, "表单不存在!");
        }
        String formId = formidList.get(0).getFormId();
        //发送消息
        weichatMiniProgramUtils.sendTemplateMessage(openId, templateParams.getTemplateId(), templateParams.getPage(), formId, activityInfo.getContent());
        //发送成功删除
        userMiniprogramFormidMapper.deleteByPrimaryKey(formidList.get(0).getId());
    }

    @Override
    public DefaultUserLocationDto getUserLocation(Integer userId, String remoteIP) {
        DefaultUserLocationDto result = new DefaultUserLocationDto();
        List<RegionGroupInfo> list = userCommunityRelationMapper.selectCommunityByUserId(userId);
        if (CollectionUtils.isNotEmpty(list)) {
            RegionGroupInfo info = list.get(0);
            result.setCityCode(info.getCityCode());
            result.setLongitude(info.getLongitude());
            result.setLatitude(info.getLatitude());
            result.setCityName(info.getCity());
            return result;
        }
        AmapIPLocationDto ipLocation = AmapUtils.getIpLocation(remoteIP);
        if (ipLocation.success()) {
            result.setCityCode(ipLocation.getCityCode());
            result.setLongitude(ipLocation.getLongitude());
            result.setLatitude(ipLocation.getLatitude());
            result.setCityName(ipLocation.getCity());
            return result;
        }
        result.setCityCode("330100");
        result.setCityName("杭州市");
        result.setLongitude(new BigDecimal("120.129179"));
        result.setLatitude(new BigDecimal("30.2655"));
        return result;
    }

    @Override
    public void updateUserStatistics() {
        List<UserDetailDto> userListDtos = userInfoMapper.selectUserList();
        for (UserDetailDto dto : userListDtos) {
            UserStatistics data = userStatisticsMapper.selectByUserId(dto.getUserId());
            UserStatistics userStatistics = new UserStatistics();
            userStatistics.setUserId(dto.getUserId());
            if (dto.getAchievementCount() != null) {
                userStatistics.setAchievementCount(Short.parseShort(dto.getAchievementCount().toString()));
            }
            if (dto.getCouponCount() != null) {
                userStatistics.setCouponCount(Short.parseShort(dto.getCouponCount().toString()));
            }
            if (dto.getFansCount() != null) {
                userStatistics.setFansCount(Short.parseShort(dto.getFansCount().toString()));
            }
            if (dto.getJoinActivityCount() != null) {
                userStatistics.setJoinActivityCount(Short.parseShort(dto.getJoinActivityCount().toString()));
            }
            if (dto.getCommunityLifeCount() != null) {
                userStatistics.setLifeCount(Short.parseShort(dto.getCommunityLifeCount().toString()));
            }
            if (dto.getScoreBalance() != null) {
                userStatistics.setScoreCount(Short.parseShort(dto.getScoreBalance().toString()));
            }
            if (dto.getSignCount() != null) {
                userStatistics.setSignCount(Short.parseShort(dto.getSignCount().toString()));
            }
            Date date = new Date();
            date.setTime(System.currentTimeMillis());
            userStatistics.setUpdateTime(date);
            if (data == null) {
                userStatistics.setCreateTime(date);
                userStatisticsMapper.insertSelective(userStatistics);
            } else {
                userStatistics.setId(data.getId());
                userStatisticsMapper.updateByPrimaryKeySelective(userStatistics);
            }
        }
    }

    @Override
    public PagesDto<AdminUserAchievementDto> getUserAchievements(Integer userId, Integer page, Integer rows) {
        //分页
        PageHelper.startPage(page, rows);
        List<AdminUserAchievementDto> list = achievementUserMapper.selectCompletedAchievement(userId);
        PageInfo<AdminUserAchievementDto> info = new PageInfo<>(list);
        return new PagesDto<>(list, info.getTotal(), page, rows);
    }

    @Override
    public void updateIMUserInfo(UserInfo info) {
        if (info.getNickName() != null || info.getImage() != null || info.getGender() != null) {
            UpdateIMUserInfoParams param = new UpdateIMUserInfoParams();
            param.setUserId(info.getId());
            param.setGender(info.getGender() == null ? "" : info.getGender().intValue() + "");
            param.setIcon(info.getImage());
            param.setName(info.getNickName());

            WangYiCommonResponseDto result = null;
            try {
                result = userIMService.updateIMUserInfo(param);
            } catch (Exception e) {
                logger.error("更新网易用户信息失败, userID:{}", info.getId());
                throw e;
            }
        }
    }

    private void saveBackgroudImage(Integer userId) {
        List<ImageInfo> imageInfos = imageInfoMapper.selectImageByCategory(3);
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(imageInfos)) {
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
        }
    }

}
