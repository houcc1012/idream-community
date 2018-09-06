package com.idream.service.impl;

import com.idream.commons.lib.mapper.UserRequestRecordMapper;
import com.idream.commons.lib.model.UserRequestRecord;
import com.idream.service.UserRequestRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hejiang
 */
@Service
public class UserRequestRecordServiceImpl implements UserRequestRecordService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    private UserRequestRecordMapper userRequestRecordMapper;

    /**
     * 插入用户记录
     *
     * @param records
     */
    @Override
    public int insertBatchUserRequestRecord(List<UserRequestRecord> records) {
        return userRequestRecordMapper.insertBatchRecord(records);
    }

    @Override
    public int insertUserRequestsRecord(UserRequestRecord record) {
        return userRequestRecordMapper.insertSelective(record);
    }
}
