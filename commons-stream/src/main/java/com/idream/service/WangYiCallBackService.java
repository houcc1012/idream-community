package com.idream.service;

import com.idream.commons.lib.dto.wangyi.WangyiIMNoticeCCParams;

/**
 * @author hejiang
 */
public interface WangYiCallBackService {

    /**
     * 新增网易IM 用户消息记录
     *
     * @param params
     */
    void insertWangyiImUserRecord(WangyiIMNoticeCCParams params);

    /**
     * 新增网易IM 群组消息记录
     *
     * @param params
     */
    void insertWangyiImGroupRecord(WangyiIMNoticeCCParams params);
}
