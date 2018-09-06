package com.idream.service;

import com.idream.commons.lib.model.UserRequestRecord;

import java.util.List;

/**
 * @author hejiang
 */
public interface UserRequestRecordService {

    /**
     * 批量插入用户记录
     *
     * @param records
     */
    int insertBatchUserRequestRecord(List<UserRequestRecord> records);

    /**
     * 单个插入用户记录
     *
     * @param record
     */
    int insertUserRequestsRecord(UserRequestRecord record);

}
