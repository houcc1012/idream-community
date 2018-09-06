package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ExchangeRecord;

public interface ExchangeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExchangeRecord record);

    int insertSelective(ExchangeRecord record);

    ExchangeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExchangeRecord record);

    int updateByPrimaryKey(ExchangeRecord record);
}