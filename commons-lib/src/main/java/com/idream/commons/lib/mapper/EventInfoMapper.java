package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.EventInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EventInfo record);

    int insertSelective(EventInfo record);

    EventInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EventInfo record);

    int updateByPrimaryKey(EventInfo record);

    List<EventInfo> selectByTypeIdAndCategory(@Param("typeId") Integer typeId, @Param("category") Byte category);
}