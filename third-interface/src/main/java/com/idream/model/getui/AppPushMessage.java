package com.idream.model.getui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

/**
 * @author harry.zhang
 * @version 1.0
 */
public class AppPushMessage {

    /**
     * 群发消息体Android
     *
     * @param appId
     * @param appKey
     * @param title
     * @param Text
     * @param url
     */
    public static AppMessage getAppMessageAllAndroid(String appId, String appKey, String title, String Text, String url) {

        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = MessgeTemplate.getlinkTemplateAndroid(appId, appKey, title, Text, url);

        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(24 * 3600 * 1000);

        return message;
    }

    /**
     * 群发消息体IOS
     *
     * @param appId
     * @param appKey
     * @param title
     * @param Text
     * @param url
     */
    public static AppMessage getAppMessageAllIOS(String appId, String appKey, String title, String Text, String url) {

        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = MessgeTemplate.getlinkTemplateIOS(appId, appKey, title, Text, url);

        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(24 * 3600 * 1000);

        return message;
    }

    /**
     * 发单个带透传消息体Android
     *
     * @param appId
     * @param appKey
     * @param title
     * @param text
     * @param customContent
     */
    public static SingleMessage getAppMessageSingleAndroid(String appId, String appKey, String title, String text,
                                                           Map<String, Object> customContent) {

        NotificationTemplate template = MessgeTemplate.getNotificationTemplateAndroid(appId, appKey, title, text,
                customContent);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        return message;
    }

    /**
     * 发单个透传消息体IOS
     *
     * @param appId
     * @param appKey
     * @param customContent
     */
    public static SingleMessage getAppMessageSingleIOS(String appId, String appKey, String title, String text,
                                                       Map<String, Object> customContent) {
        // 通知透传模板
        TransmissionTemplate template = MessgeTemplate.getTransmissionTemplateIOS(appId, appKey, title, text,
                customContent);
        SingleMessage message = new SingleMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        return message;

    }

    /**
     * 指定列表带透传消息体Android
     *
     * @param appId
     * @param appKey
     * @param title
     * @param text
     * @param customContent
     */
    public static ListMessage getAppMessageListAndroid(String appId, String appKey, String title, String text,
                                                       Map<String, Object> customContent) {
        // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin_pushList_needDetails", "true");
        // 配置返回每个别名及其对应cid的用户状态，可选
        // System.setProperty("gexin_pushList_needAliasDetails", "true");
        // 通知透传模板
        NotificationTemplate template = MessgeTemplate.getNotificationTemplateAndroid(appId, appKey, title, text,
                customContent);
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        return message;
    }

    /**
     * 指定列表透传消息体IOS
     *
     * @param appId
     * @param appKey
     * @param customContent
     */
    public static ListMessage getAppMessageListIOS(String appId, String appKey, String title, String text,
                                                   Map<String, Object> customContent) {
        // 通知透传模板
        TransmissionTemplate template = MessgeTemplate.getTransmissionTemplateIOS(appId, appKey, title, text,
                customContent);
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        return message;
    }


}