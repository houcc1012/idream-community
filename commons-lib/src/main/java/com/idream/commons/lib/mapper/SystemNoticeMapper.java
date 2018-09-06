package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.app.NoticeListResponseDto;
import com.idream.commons.lib.model.SystemNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemNotice record);

    int insertSelective(SystemNotice record);

    SystemNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemNotice record);

    int updateByPrimaryKey(SystemNotice record);

    List<NoticeListResponseDto> selectNoticeList(@Param("content") String content);
}