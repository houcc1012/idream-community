package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ProfessionInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProfessionInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProfessionInfo record);

    int insertSelective(ProfessionInfo record);

    ProfessionInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProfessionInfo record);

    int updateByPrimaryKey(ProfessionInfo record);

    //查询所有职业信息
    @Select("select * from profession_info order by pid")
    List<ProfessionInfo> findAll();

}