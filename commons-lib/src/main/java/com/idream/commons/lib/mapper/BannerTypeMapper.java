package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.BannerType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BannerTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BannerType record);

    int insertSelective(BannerType record);

    BannerType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BannerType record);

    int updateByPrimaryKey(BannerType record);

    @Select("select * from banner_type")
    List<BannerType> selectAll();
}