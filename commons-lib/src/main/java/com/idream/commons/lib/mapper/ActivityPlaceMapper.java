package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ActivityPlace;

import java.util.List;

public interface ActivityPlaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityPlace record);

    int insertSelective(ActivityPlace record);

    ActivityPlace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityPlace record);

    int updateByPrimaryKey(ActivityPlace record);

    /**
     * @param @return
     *
     * @return List<ActivityPlace>
     */
    List<ActivityPlace> getAllActivityPlaces();
}