package com.idream.commons.lib.base;

public class RetCodeConstants {


    public final static String RET_CODE = "retCode";
    public final static String RET_MSG = "retMsg";
    public final static String RESPONSE_DATA = "responseData";

    public final static String SUCESS = "0000";

    //通用的错误以9开头
    public final static String UNKOWN_ERROR = "9999";//未知错误
    public final static String PARAMS_ERROR = "9001"; //参数错误
    public final static String DATA_PARSE_ERROR = "9002";//数据解析错误
    public final static String REQUEST_VALIDATE_ERROR = "9003";//api请求鉴权失败
    public final static String REQUEST_VERIFICATIONCODE = "9004";//短信验证码失败
    public final static String ERROR_PHONEEXISTENCE = "9005";//手机已经存在
    public final static String ERROR_ACTIVITY_TASK_RECORD = "9006";//打卡失败
    public final static String ERROR_NO_USER = "9008";//用户未注册
    public final static String VERIFY_ERROR = "9009";//校验失败
    public static final String ACCOUNT_NAME_NOT_EXIST = "9010"; //账号不存在
    public static final String ACCOUNT_STATUS_EXCEPTION = "9011";//账号状态异常
    public static final String ACCOUNTNAME_OR_PASSEORD_ERROR = "9012";//账号或密码错误
    public static final String LOGIN_FAIL_ACCOUNT_LOCK = "9013";
    public static final String DELETE_FAIL = "9014";//删除失败
    public static final String ACCEPT_USER_JOINED_ACTIVITY = "9015";//被邀请人已加入活动
    public static final String ACCEPTID_HAS_INVITED = "9016";//被邀请人已加入活动
    public static final String UPDATE_FAIL = "9017";//修改失败
    public static final String ADD_FAIL = "9018";//新增失败
    public final static String TOO_MANY_REQUEST = "9019"; //请求频繁
    public final static String RE_SUBMIT = "9020"; //重复提交
    public final static String SERVICE_UOGRADED = "9021"; //服务升级中
    public final static String PHONE_ACCOUNT_EXISTS = "9022";//手机账号已存在
    public final static String SENSITIVE_WORD_VERIFY_NOT_PASS = "9023";//敏感字校验不通过


    //数据库发生发生的错误 以81开头
    public final static String DATABASE_ERROR = "8101";
    public final static String DB_UPDATE_ERROR = "8102"; //数据库更新异常

    //通用业务失败错误
    public static final String UPLOAD_IMAGE_ERROR = "7001"; //上传图片失败
    public static final String REEOR_FINDACTIVITTASKTECORDUSERINFO = "7003";  //用户打过卡
    public static final String SELECT_FAILED = "7004"; //查询失败

    public static final String NOTICED_ERROR = "7005"; //已关注过
    public static final String CANCEL_NOTICED_ERROR = "7006"; //未关注过该用户或者已取消关注

    public static final String THUMBUP_ERROR = "7007"; //已赞过
    public static final String CANCEL_HUMBUP_ERROR = "7008"; //未赞过该动态或者已取消赞
    public static final String OPER_FAIL = "7009"; //操作失败
    public static final String LIFE_NOT_EXIST = "7010"; //活动圈不存在
    public static final String QUIT_ACTIVITY_ERROR = "7011"; //退出活动失败
    public static final String UPDATE_ACTIVITY_STATUS_ERROR = "7012"; //发布活动失败
    public static final String ALDREAY_JOIN_ACTIVITY = "7013"; //参加活动失败，已加入活动
    public static final String USER_LIKE_TYPE_ERROR = "7014"; //用户感兴趣的活动类型不存在
    public static final String ACTIVITY_NO_PUTWAY = "7015"; //该活动临时下架
    public static final String USER_NOT_ATTEND_ACTIVITY = "7016"; //该活动临时下架

    //用户活动参加活动校验
    public static final String CREATOR_ATTENDED = "2001";//创建者已参加
    public static final String NOT_CREATOR_ATTENDED = "2002";//不是创建者未参加
    public static final String CREATOR_NOT_ATTENDED = "2003";//创建者未参加
    public static final String NOT_CREATOR_NOT_ATTENDED = "2004";//不是创建者也未参加活动
    public static final String EXSIST_USER = "2005";//存在用户
    public static final String ERROR_ACTIVITEND = "2005";  //活动结束
    public static final String CHECKPHONE = "2006";  //手机号已经绑定
    public static final String BINDINGPHONEERROR = "2007"; //手机号绑定失败,请重新绑定
    public static final String NOT_IN_ACTIVITY_CITY = "2008";

    //用户账户
    public static final String USER_PASSWORD_ILLEGAL = "2101";//密码非法

    public static final String NOTALLOW = "0001"; //没有权限参加活动
    public static final String OVERCOUNT = "0002"; //人数达到上限
    public static final String NO_INFORMATIONCOLLECTION = "0003"; //没有搜集报名信息

    public static final String SAVE_FAILED = "3001";//保存失败
    public static final String EXCHANGE_OUTDATE = "3002";//券码过期
    public static final String EXCHANGEED_ERROR = "3003";//已兑换过
    public static final String EXCHANGEED_WAIT = "3006";//未兑换
    public static final String NOTAPPLY_ERROR = "3004"; //不适用于此地区
    public static final String SIGN_ERROR = "3005"; //领奖失败，未填写信息
    //现场开奖是否有返回参数
    public static final String LOTTERY_INFO_BLANK = "3006"; //社区无现场开奖
    public static final String AWARD_ERROR = "3007";//奖品错误
    public static final String INTEGRATION_ERROR = "3008";//积分错误
    public static final String ACCOUNT_USER_NOT_ACTIVITY = "3009";  //用户没有参加活动记录
    public static final String LOTTERY_NO_COUNT = "3010";  //用户开奖次数已用完
    public static final String INTEGRATION_NOT_ENOUGH = "3011";//积分不足
    public static final String LOTTERY_AWARD_ERROR = "3012";//未设置奖品信息
    public static final String LOTTERY_DISTANCE_EXCEED = "3013";//距离开奖社区过远
    //活动列表中该城市是否有活动
    public static final String HAVE_ACTIVITY = "3012";//有活动
    public static final String NO_HAVE_ACTIVITY = "3013";//无活动
    public static final String UNAUTHRIZED = "3014";//当前手机未授权定位权限
    public static final String AUTHTHRIZED_HAVE_ACTIVITY = "3105"; //当前手机有定位权限,且当前城市有活动,10km无活动
    public static final String AUTHTHRIZED_HAVE_ACTIVITY_TEN = "3106";//当前手机有定位权限,且当前城市有活动,10km有活动
    public static final String AUTHTHRIZED_NO_HAVE_ACTIVITY = "3107";//当前手机有定位权限,且当前城市无活动


    //我的社区icon是否展示
    public static final String SHOW_MY_COMMUNITY_BUTTON = "3108";//展示"我的社区"button
    public static final String NO_SHOW_MY_COMMUNITY_BUTTON = "3109";//不展示"我的社区"button
    public static final String NOT_NULL_REGIONID_COMMUNITYID = "3112";//不展示"我的社区"button
    public static final String NOT_BLANK_REGIONID_COMMUNITYID = "3113";//大社区id和小区id不能同时为空

    //app系统通知
    public static final String ERROR_EMPTY_LIEK = "3110";//清空点赞失败
    public static final String SUCCESS_EMPTY_LIEK = "3111";//清空点赞失败

    //admin用户标签
    public static final String USERTAG_REPEAT = "3114";//用户标签重复
    public static final String ACTIVITY_TAG_REPEAT = "3115";//活动标签重复

    //管理员异常 32
    public static final String USER_MANAGER_NO_EXIST = "3201";//管理员不存在
    public static final String USER_MANAGER_NO_AUTH = "3202";//管理员没有权限

    //社区认知异常 33
    public static final String AUTH_COMMUNITY_IS_EXIST = "3301";//已认证过该社区
    public static final String AUTH_COMMUNITY_NO_EXIST = "3302";//认证的信息不存在
    public static final String AUTH_COMMUNITY_BOOKHOUSE_NO_EXIST = "3303";//书屋不存在

    public static final String AUTH_USER_INFO_NO_EXIST = "3401";//认证的用户信息不存在
    public static final String AUTH_USER_OPER_COMPLETED = "3402";//用户认证操作已完成

    public static final String NO_OTHER_REGION = "3403";//该小区或书屋已无其他社区可以关联
    public static final String DATA_BASE_NO_REGION = "3404";//数据库没有社区数据

    //小程序推广
    public static final String WX_PROMOTION_ERROR = "3501";//该用户已经通过该方式注册小程序成功,重复操作错误
    public static final String WX_PROMOTION_NAME_REPEAT = "3502";//该省市下的该推广名称已经存在

    //网易相关
    public static final String WANGYI_REGISTRY_FAILED = "5000";//网易云用户注册失败
    public static final String WANGYI_REFRESHTOKEN_FAILED = "5001";//网易云获取新TOKEN失败
    public static final String WANGYI_CREATEGROUP_FAILED = "5002";//创建群组失败
    public static final String WANGYI_ADDUSERSTOGROUP_FAILED = "5003";//群组添加用户失败
    public static final String WANGYI_UPDATEGROUP_FAILED = "5004"; //更新群组信息失败
    public static final String WANGYI_QUERYGROUPANDUSERSLIST_FAILED = "5005"; //查询群组信息失败
    public static final String WANGYI_KICKOUTUSERFROMGROUP_FAILED = "5006"; //从群组中踢人失败
    public static final String WANGYI_DELETEGROUP_FAILED = "5007"; //删除群组失败
    public static final String WANGYI_ADDGROUPMANAGER_FAILED = "5008"; //添加群组管理员失败
    public static final String WANGYI_REMOVEMANAGER_FAILED = "5009"; //移除管理员失败
    public static final String WANGYI_QUERYGROUPDETAIL_FAILED = "5010"; //查询群组详情失败
    public static final String WANGYI_GETUSERJOINTEAMS_FAILED = "5011"; //获取用户参与的群组失败
    public static final String WANGYI_CHANGEGROUPOWNER_FAILED = "5012"; //移交群主失败
    public static final String WANGYI_UPDATETEAMNICK_FAILED = "5013"; //修改群内用户昵称失败
    public static final String WANGYI_MUTETEAM_FAILED = "5014"; //修改消息提醒开关失败
    public static final String WANGYI_MUTEUSER_FAILED = "5015"; //群组内禁言成员失败
    public static final String WANGYI_UPDATEUSERINFO_FAILED = "5016"; //更新用户信息失败
    public static final String WANGYI_GETUSERINFO_FAILED = "5017"; //获取用户信息失败
    public static final String WANGYI_ADDFRIEND_FAILED = "5018"; //添加好友失败
    public static final String WANGYI_UPDATEFRIENDALIAS_FAILED = "5019"; //添加备注失败
    public static final String WANGYI_DELETEFRIEND_FAILED = "5020"; //删除好友失败
    public static final String WANGYI_ADDUSERTOBLACKLIST_FAILED = "5021"; //将用户添加到黑名单失败
    public static final String WANGYI_REMOVEUSERFROMBLACKLIST_FAILED = "5022"; //黑名单移除用户失败
    public static final String WANGYI_MUTEGROUP_FAILED = "5023"; //禁言(解禁)群组失败
    public static final String WANGYI_LEAVEGROUP_FAILED = "5024"; //主动退群失败
    public static final String WANGYI_GROUPADVICE_FAILED = "5025"; //群组发送通知
    public static final String WANGYI_NOGROUP_FAILED = "5026"; //没有群组
    public static final String NO_WANGYIACCOUNT = "5027"; //没有该用网易用户
    public static final String GROUPOWNER_NOTALLOW_LEAVEGROUP = "5028";//群组不允许退群
    public static final String GROUP_NO_USER = "5029";//群组内没有改用户
    public static final String ALREADY_HAVE = "5030"; //已经搜集了报名信息
    public static final String ACTIVITY_ALREADY_COLLECTION = "5031"; //该活动已经搜藏
    public static final String ACTIVITY_NO_COLLECTION = "5032"; //该活动没有搜藏
    public static final String ACTIVITY_COLLECTION_FAILED = "5033"; //活动添加搜藏失败
    public static final String ACTIVITY_REMOVE_COLLECTION_FAILED = "5034"; //活动取消搜藏失败
    public static final String DISLIKE_WANGYI_FAILED = "5035"; //重复操作将该趣聊设置为不感兴趣失败
    public static final String IM_GROUP_ALREADY_CREATE = "5036"; //群聊已存在
    public static final String IM_GROUP_NOT_EXIST = "5037"; //群聊不存在
    public static final String IM_GROUP_OVERAGE = "5038"; //群人数达到上限
    //发送微信模板
    public static final String MINI_PROGRAM_OPEN_ID_NOT_EXIST = "6000";//关联小程序openid不存在
    public static final String TEMPLATE_NOT_EXIST = "6001";//模板消息不存在
    public static final String FORM_ID_NOT_EXIST = "6002";//表单不存在
    public static final String ACTIVITY_NOT_EXIST = "6003";//活动不存在
    public static final String ACTIVITY_HAVE_CANCEL = "6004";//活动已取消
    public static final String ACTIVITY_CANCEL_ERROR = "6005";//活动取消失败


}
