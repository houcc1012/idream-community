package com.idream.service;

import java.util.List;
import java.util.Map;

import com.gexin.rp.sdk.base.IPushResult;

public interface PushMessageToAndroidService {
    /**
     * 推送所有用户Android
     *
     * @param title 标题
     * @param text  内容
     * @param url   链接
     *
     * @return IPushResult
     */
    IPushResult pushMessageToAllAndroid(String title, String text, String url);

    /**
     * 对指定列表用户推送消息Android
     *
     * @param cids
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     *
     * @return IPushResult
     */
    IPushResult pushMessageToListAndroid(List<String> cids, String title, String text, Map<String, Object> customContent);

    /**
     * 推送指定别名用户Android
     *
     * @param cid
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     *
     * @return IPushResult
     */
    IPushResult pushMessageToSingleAliasAndroid(String alias, String title, String text, Map<String, Object> customContent);

    /**
     * 推送指定用户Android
     *
     * @param cid
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     *
     * @return IPushResult
     */
    IPushResult pushMessageToSingleAndroid(String cid, String title, String text, Map<String, Object> customContent);
}
