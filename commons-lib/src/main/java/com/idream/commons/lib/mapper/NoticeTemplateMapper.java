package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.NoticeTemplate;

public interface NoticeTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoticeTemplate record);

    int insertSelective(NoticeTemplate record);

    NoticeTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoticeTemplate record);

    int updateByPrimaryKey(NoticeTemplate record);
}