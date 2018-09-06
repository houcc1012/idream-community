package com.idream.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.idream.model.getui.AppPushMessage;
import com.idream.service.PushMessageToAndroidService;

@Service
public class PushMessageToAndroidServiceImpl implements PushMessageToAndroidService {

    public static Logger logger = LoggerFactory.getLogger(PushMessageToAndroidServiceImpl.class);

    @Value("${appId}")
    private String appId;
    @Value("${appKey}")
    private String appKey;
    @Value("${masterSecret}")
    private String masterSecret;
    @Value("${host}")
    private String host;

    @Override
    public IPushResult pushMessageToAllAndroid(String title, String text, String url) {
        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(appKey, masterSecret, true);
        AppMessage toAppmessage = AppPushMessage.getAppMessageAllAndroid(appId, appKey, title, text, url);
        IPushResult ret = push.pushMessageToApp(toAppmessage);
        if (ret != null) {
            logger.info(ret.getResponse().toString());
        } else {
            logger.error("消息服务器异常  pushMessageToAllAndroid messge fail");
        }
        return ret;
    }

    @Override
    public IPushResult pushMessageToListAndroid(List<String> cids, String title, String text,
                                                Map<String, Object> customContent) {
        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(appKey, masterSecret, true);
        ListMessage message = AppPushMessage.getAppMessageListAndroid(appId, appKey, title, text, customContent);
        // 配置推送目标
        List<Target> targets = new ArrayList<Target>();
        for (String cid : cids) {
            Target target = new Target();
            target.setAppId(appId);
            target.setClientId(cid);
            targets.add(target);
        }
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        if (ret != null) {
            logger.info(ret.getResponse().toString());
        } else {
            logger.error("消息服务器异常  pushMessageToListAndroid messge fail");
        }
        return ret;
    }

    @Override
    public IPushResult pushMessageToSingleAliasAndroid(String alias, String title, String text,
                                                       Map<String, Object> customContent) {
        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(appKey, masterSecret, true);
        SingleMessage message = AppPushMessage.getAppMessageSingleAndroid(appId, appKey, title, text, customContent);

        IPushResult ret = null;
        Target target = new Target();
        target.setAppId(appId);
        target.setAlias(alias);
        try {
            ret = push.pushMessageToSingle(message, target);
            logger.info(ret.getResponse().toString());
        } catch (RequestException e) {
            e.printStackTrace();
            logger.error("pushMessageToSingleAndroid messge fail", e);
            ret = push.pushMessageToSingle(message, target, e.getRequestId());

        }
        return ret;
    }

    @Override
    public IPushResult pushMessageToSingleAndroid(String cid, String title, String text,
                                                  Map<String, Object> customContent) {
        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(appKey, masterSecret, true);
        SingleMessage message = AppPushMessage.getAppMessageSingleAndroid(appId, appKey, title, text, customContent);

        IPushResult ret = null;
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(cid);
        try {
            ret = push.pushMessageToSingle(message, target);
            logger.info(ret.getResponse().toString());
        } catch (RequestException e) {
            e.printStackTrace();
            logger.error("pushMessageToSingleAndroid messge fail", e);
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        return ret;
    }

}
