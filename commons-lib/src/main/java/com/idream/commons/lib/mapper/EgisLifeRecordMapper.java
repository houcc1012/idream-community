package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.EgisLifeRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EgisLifeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EgisLifeRecord record);

    int insertSelective(EgisLifeRecord record);

    EgisLifeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EgisLifeRecord record);

    int updateByPrimaryKey(EgisLifeRecord record);

    @Select("SELECT * FROM egis_life_record WHERE user_id=#{userId}")
    EgisLifeRecord selectByUserId(@Param("userId") Integer userId);

    int selectUnReadByUserId(@Param("userId") Integer userId);

    Integer selectMaxLineIdByUserId(@Param("userId") Integer userId);
}