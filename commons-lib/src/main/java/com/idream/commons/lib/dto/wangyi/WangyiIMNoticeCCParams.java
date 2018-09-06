package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;

/**
 * @author hejiang
 */
@ApiModel("网易IM 消息抄送请求参数")
public class WangyiIMNoticeCCParams {
    private String eventType; //	String	值为1，表示是会话类型的消息
    //	String	会话具体类型：
    //  PERSON（二人会话数据）、TEAM（群聊数据）、 CUSTOM_PERSON（个人自定义系统通知）、CUSTOM_TEAM（群组自定义系统通知），字符串类型
    private String convType;
    //	String	若convType为PERSON或CUSTOM_PERSON，则to为消息接收者的用户账号，字符串类型；
    //  若convType为TEAM或CUSTOM_TEAM，则to为tid，即群id，可转为Long型数据
    private String to;
    private String fromAccount; //	String	消息发送者的用户账号，字符串类型
    private String fromClientType; //	String	发送客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST，字符串类型
    private String fromDeviceId; //	String	发送设备id，字符串类型
    private String fromNick; //	String	发送方昵称，字符串类型
    private String msgTimestamp; //	String	消息发送时间，字符串类型
    /**
     * String	会话具体类型PERSON、TEAM对应的通知消息类型：
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
     * <p>
     * 会话具体类型CUSTOM_PERSON对应的通知消息类型：
     * FRIEND_ADD、 //加好友
     * FRIEND_DELETE、 //删除好友
     * CUSTOM_P2P_MSG、 //个人自定义系统通知
     * <p>
     * 会话具体类型CUSTOM_TEAM对应的通知消息类型：
     * TEAM_APPLY、 //申请入群
     * TEAM_APPLY_REJECT、 //拒绝入群申请
     * TEAM_INVITE、 //邀请进群
     * TEAM_INVITE_REJECT、 //拒绝邀请
     * TLIST_UPDATE、 //群信息更新
     * CUSTOM_TEAM_MSG、 //群组自定义系统通知
     */
    private String msgType;
    private String body; //	String	消息内容，字符串类型
    private String attach; //	String	附加消息，字符串类型
    private String msgidClient; //	String	客户端生成的消息id，仅在convType为PERSON或TEAM含此字段，字符串类型
    private String msgidServer; //	String	服务端生成的消息id，可转为Long型数据
    private String resendFlag; //	String	重发标记：0不是重发, 1是重发。仅在convType为PERSON或TEAM时含此字段，可转为Integer类型数据
    private String customSafeFlag; //	String	自定义系统通知消息是否存离线:0：不存，1：存。 仅在convType为CUSTOM_PERSON或CUSTOM_TEAM时含此字段，可转为Integer类型数据
    private String customApnsText; //	String	自定义系统通知消息推送文本。仅在convType为CUSTOM_PERSON或CUSTOM_TEAM时含此字段，字符串类型
    /**
     * 跟本次群操作有关的用户accid，仅在convType为TEAM或CUSTOM_TEAM时含此字段，字符串类型。
     * tMembers格式举例：
     * {
     * ... // 其他字段
     * "tMembers":"[123, 456]" //相关的accid为 123 和 456
     * }
     */
    private String tMembers;
    private String ext; //	String	消息扩展字段
    private String antispam; //	String	标识是否被反垃圾，仅在被反垃圾时才有此字段，可转为Boolean类型数据
    //	String	易盾反垃圾的原始处理细节，只有接入了相关功能易盾反垃圾的应用才会有这个字段。
    //  详见以下1.4.5.1、P2P：文本消息 和 1.4.5.2、P2P：图片消息的举例说明。
    private String yidunRes;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getConvType() {
        return convType;
    }

    public void setConvType(String convType) {
        this.convType = convType;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getFromClientType() {
        return fromClientType;
    }

    public void setFromClientType(String fromClientType) {
        this.fromClientType = fromClientType;
    }

    public String getFromDeviceId() {
        return fromDeviceId;
    }

    public void setFromDeviceId(String fromDeviceId) {
        this.fromDeviceId = fromDeviceId;
    }

    public String getFromNick() {
        return fromNick;
    }

    public void setFromNick(String fromNick) {
        this.fromNick = fromNick;
    }

    public String getMsgTimestamp() {
        return msgTimestamp;
    }

    public void setMsgTimestamp(String msgTimestamp) {
        this.msgTimestamp = msgTimestamp;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getMsgidClient() {
        return msgidClient;
    }

    public void setMsgidClient(String msgidClient) {
        this.msgidClient = msgidClient;
    }

    public String getMsgidServer() {
        return msgidServer;
    }

    public void setMsgidServer(String msgidServer) {
        this.msgidServer = msgidServer;
    }

    public String getResendFlag() {
        return resendFlag;
    }

    public void setResendFlag(String resendFlag) {
        this.resendFlag = resendFlag;
    }

    public String getCustomSafeFlag() {
        return customSafeFlag;
    }

    public void setCustomSafeFlag(String customSafeFlag) {
        this.customSafeFlag = customSafeFlag;
    }

    public String getCustomApnsText() {
        return customApnsText;
    }

    public void setCustomApnsText(String customApnsText) {
        this.customApnsText = customApnsText;
    }

    public String gettMembers() {
        return tMembers;
    }

    public void settMembers(String tMembers) {
        this.tMembers = tMembers;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getAntispam() {
        return antispam;
    }

    public void setAntispam(String antispam) {
        this.antispam = antispam;
    }

    public String getYidunRes() {
        return yidunRes;
    }

    public void setYidunRes(String yidunRes) {
        this.yidunRes = yidunRes;
    }
}
