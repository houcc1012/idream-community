package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.WyimGroupMsgRecord;

public interface WyimGroupMsgRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WyimGroupMsgRecord record);

    int insertSelective(WyimGroupMsgRecord record);

    WyimGroupMsgRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WyimGroupMsgRecord record);

    int updateByPrimaryKey(WyimGroupMsgRecord record);
}