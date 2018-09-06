package com.idream.commons.lib.enums;

/**
 * @author zhenggaosheng
 */
public class WangYiEnum {

    /**
     * 群组角色 1:群主  ,  2:管理员  ,  3:吃瓜群众
     */
    public enum WangYiGroupEnum {

        OWNER(1), MANAGER(2), MEMBER(3);

        private Byte code;

        private WangYiGroupEnum(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 网易IM接口操作类型 1-建群;2-退群, 3-给群加人;4-加好友;5-删除好友
     */
    public enum OperateWangyiIMType {

        CTREATE_GROUP(1), QUIT_GROUP(2), ADD_GROUP_USER(3), ADD_FRIEND(4), DELETE_FRIEND(5), DELETE_GROUP(6), UPDATE_GROUP(7),DELETE_IM_GROUP(8);

        private Byte type;

        private OperateWangyiIMType(Integer type) {
            this.type = type.byteValue();
        }

        public Byte getType() {
            return type;
        }
    }


    /**
     * BusinessType:1 群所属业务类型 1-活动
     */
    public enum BusinessType {
        BUSINESSTYPE(1);
        private Byte code;

        private BusinessType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 加群权限
     */
    public enum JoinRole {
        VERIFY(1),//需要验证
        NO_VERIFY(0),//不用验证
        NOTALLOW(2);//不允许任何人加入
        private Byte code;

        private JoinRole(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 邀请入群权限
     */
    public enum InviteRole {
        MANAGER(0), //管理员
        EVERYBODY(1); //所有人
        private Byte code;

        private InviteRole(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 群资料修改权限
     */
    public enum UpdateGroupinfoRole {
        MANAGER(0), //管理员
        EVERYBODY(1); //所有人
        private Byte code;

        private UpdateGroupinfoRole(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 被邀请人入群权限
     */
    public enum MagreeRole {
        NEED(1),//需要被邀请人同意才可以加入群
        NO_NEED(0);//不需要被邀请人同意加入群
        private Byte code;

        private MagreeRole(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 群组禁言类型
     */
    public enum MuteType {
        NO_BAN(0), //无禁言
        BAN_MEMBER(1), //禁言普通成员
        BAN_ALLPEOPLE(3); //全部禁言
        private Byte code;

        private MuteType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 发送消息类型
     */
    public enum Ope {
        //0：点对点个人消息，1：群消息（高级群），其他返回414
        GROUPMESSAGE("1"), //群消息
        PERSON("0"),  //个人消息
        CUSTOMER_TYPE("10"); //与android和ios约定的消息.
        private String code;

        private Ope(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 黑名单静音操作
     */
    public enum RelationType {
        //本次操作的关系类型,1:黑名单操作，2:静音列表操作
        BLACKLIST("1"),
        MUTE("2");
        private String code;

        private RelationType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 黑名单静音操作
     */
    public enum BlackOrMuteType {
        //操作值，0:取消黑名单或静音，1:加入黑名单或静音
        REMOVE_BLACKLIST_MUTE("0"),
        ADD_BLACKLIST_MUTE("1");
        private String code;

        private BlackOrMuteType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 消息抄送会话类型
     * PERSON, 二人会话数据
     * TEAM, 群聊数据
     * CUSTOM_PERSON, 个人自定义系统通知
     * CUSTOM_TEAM 群组自定义系统通知
     */
    public enum ConvType {
        PERSON, TEAM, CUSTOM_PERSON, CUSTOM_TEAM;
    }

    /**
     * 消息抄送发送客户端类型
     * AOS, IOS, PC, WINPHONE, WEB, REST;
     */
    public enum FromClientType {
        AOS, IOS, PC, WINPHONE, WEB, REST;
    }

    /**
     * 消息通知类型
     * 会话具体类型PERSON、TEAM对应的通知消息类型,
     * TEXT、
     * PICTURE、
     * AUDIO、
     * VIDEO、
     * LOCATION 、
     * NOTIFICATION、
     * FILE、 //文件消息
     * NETCALL_AUDIO、 //网络电话音频聊天
     * NETCALL_VEDIO、 //网络电话视频聊天
     * DATATUNNEL_NEW、 //新的数据通道请求通知
     * TIPS、 //提示类型消息
     * CUSTOM //自定义消息
     * 会话具体类型CUSTOM_PERSON对应的通知消息类型：
     * FRIEND_ADD、 //加好友
     * FRIEND_DELETE、 //删除好友
     * CUSTOM_P2P_MSG、 //个人自定义系统通知
     * 会话具体类型CUSTOM_TEAM对应的通知消息类型：
     * TEAM_APPLY、 //申请入群
     * TEAM_APPLY_REJECT、 //拒绝入群申请
     * TEAM_INVITE、 //邀请进群
     * TEAM_INVITE_REJECT、 //拒绝邀请
     * TLIST_UPDATE、 //群信息更新
     * CUSTOM_TEAM_MSG、 //群组自定义系统通知
     */
    public enum MsgType {
        TEXT, PICTURE, AUDIO, VIDEO, LOCATION, NOTIFICATION, FILE, NETCALL_AUDIO, NETCALL_VEDIO, DATATUNNEL_NEW, TIPS,
        CUSTOM, FRIEND_ADD, FRIEND_DELETE, CUSTOM_P2P_MSG, TEAM_APPLY, TEAM_APPLY_REJECT, TEAM_INVITE, TEAM_INVITE_REJECT,
        TLIST_UPDATE, CUSTOM_TEAM_MSG;
    }

    /**
     * 重发标记
     * 0不是重发, 1是重发
     */
    public enum ResendFlag {
        NO_RESEND("0"),
        RESEND("1");
        private String code;

        ResendFlag(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }


}
