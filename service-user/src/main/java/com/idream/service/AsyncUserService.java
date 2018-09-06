package com.idream.service;

/**
 * @author hejiang
 */
public interface AsyncUserService {

    /**
     * 异步执行批量插入用户消息
     *
     * @param userId
     * @param systemNoticeId
     * @param noticeType
     */
    void asyncInsertUserNoticeInfo(int userId, int systemNoticeId, Byte noticeType);
}
