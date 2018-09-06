package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.WyimUserMsgRecord;

public interface WyimUserMsgRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WyimUserMsgRecord record);

    int insertSelective(WyimUserMsgRecord record);

    WyimUserMsgRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WyimUserMsgRecord record);

    int updateByPrimaryKey(WyimUserMsgRecord record);
}