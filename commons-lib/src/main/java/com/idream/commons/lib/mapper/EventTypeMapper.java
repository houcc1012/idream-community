package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.EventType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EventTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EventType record);

    int insertSelective(EventType record);

    EventType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EventType record);

    int updateByPrimaryKey(EventType record);

    /**
     * 查询子事件,同一时间只有一个
     *
     * @param pid
     * @param valueId
     */
    @Select("select id from event_type where pid=#{pid} and extra_value=#{valueId} and type=2")
    Integer selectChildByPidAndValue(@Param("pid") Integer pid, @Param("valueId") Integer valueId);

    /**
     * 查询子事件通过父级,只查询能同时发生的子事件
     *
     * @param pid
     */
    @Select("select id from event_type where pid=#{pid} and type=1")
    List<Integer> selectChildByPid(@Param("pid") Integer pid);

    List<Integer> selectDistinctChildByActivityId(@Param("activityId") Integer activityId);
}