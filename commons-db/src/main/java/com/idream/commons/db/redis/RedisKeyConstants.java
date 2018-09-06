package com.idream.commons.db.redis;

/**
 * @Author: hejiang
 * @Description: redis key
 * @Date: 18:56 2018/3/22
 */
public class RedisKeyConstants {
    //redisLock
    public static final String REDIS_LOCK = "redisLock";
    //登录token 前缀
    public static final String AUTH_TOKEN = "authToken";

    //token 用户关联 key
    public static final String AUTH_TOKEN_REL = "authTokenRel";
    public static final String USER_BINDING_IDENTITY_CODE = "userBindingIdentityCode";
    //管理端(后台)账户锁 type
    public static final String ADMIN_ACCOUNT_LOCK = "adminAccountLock";
    //用户权限
    public static final String AUTH_PERMISSION = "authPermission";
    //兑奖奖品前缀
    public static final String AWARD_PRE = "awardPre";
    //抽奖奖品key
    public static final String INTEGRATION_AWARD = "integrationAward";
    //抽奖奖品数量
    public static final String INTEGRATION_AWARD_COUNT = "integrationAwardCount";
    //现场开奖奖品
    public static final String SCENE_OPEN_AWARD = "sceneOpenAward";
    //现场开奖奖品
    public static final String SCENE_OPEN_AWARD_COUNT = "sceneOpenAwardCount";
    //现场开奖奖品抽奖次数
    public static final String SCENE_OPEN_DRAW_COUNT = "sceneOpenDrawCount";
    //管理者(中台)账户锁 type
    public static final String USER_ACCOUNT_LOCK = "userAccountLock";
    //微信小程序accesstoken
    public static final String WX_MINIPROGRAM_TOKEN = "weichatMiniprogramToken";
}
