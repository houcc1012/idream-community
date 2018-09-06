package com.idream.service;

import com.gexin.rp.sdk.base.IPushResult;

import java.util.List;
import java.util.Map;

public interface PushMessageToIOSService {
    /**
     * 推送所有用户IOS
     *
     * @param title 标题
     * @param text  内容
     * @param url   链接
     *
     * @return IPushResult
     */
    IPushResult pushMessageToAllIOS(String title, String text, String url);

    /**
     * 对指定列表用户推送消息IOS
     *
     * @param cids
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     *
     * @return IPushResult
     */
    IPushResult pushMessageToListIOS(List<String> cids, String title, String text, Map<String, Object> customContent);

    /**
     * 对指定别名用户推送消息IOS
     *
     * @param alias
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     *
     * @return IPushResult
     */
    IPushResult pushMessageToSingleAliasIOS(String alias, String title, String text, Map<String, Object> customContent);

    /**
     * 对指定用户推送消息IOS
     *
     * @param cid
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     *
     * @return IPushResult
     */
    IPushResult pushMessageToSingleIOS(String cid, String title, String text, Map<String, Object> customContent);
}
