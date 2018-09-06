package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.EgisAttentionRecord;

public interface EgisAttentionRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EgisAttentionRecord record);

    int insertSelective(EgisAttentionRecord record);

    EgisAttentionRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EgisAttentionRecord record);

    int updateByPrimaryKey(EgisAttentionRecord record);

    EgisAttentionRecord selectByUserId(Integer userId);

    int selectUnAttentionCount(Integer userId);
}