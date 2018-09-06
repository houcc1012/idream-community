package com.idream.service.impl;

import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.mapper.UserNoticeInfoMapper;
import com.idream.service.AsyncUserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hejiang
 */
@Service
public class AsyncUserServiceImpl implements AsyncUserService {

    @Resource
    private UserNoticeInfoMapper userNoticeInfoMapper;

    @Async
    @Override
    public void asyncInsertUserNoticeInfo(int userId, int systemNoticeId, Byte noticeType) {
        if (SystemEnum.NoticeType.SYSTEM.getType().equals(noticeType)) {
            userNoticeInfoMapper.insertBatchByNoticeIdAndUserId(systemNoticeId, userId);
        }
    }
}
