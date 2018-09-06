package com.idream.service.impl;

import com.idream.commons.lib.mapper.UserActivityRecordMapper;
import com.idream.commons.lib.model.UserActivityRecord;
import com.idream.service.UserActivityRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: UserActivityRecordServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: www.idream.com</p>
 *
 * @author huayuliang
 * @version 1.0
 */
@Service
public class UserActivityRecordServiceImpl implements UserActivityRecordService {

    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;


    @Override
    public void addEntity(UserActivityRecord bean) {
        userActivityRecordMapper.insertSelective(bean);
    }
}
