package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ImageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ImageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImageInfo record);

    int insertSelective(ImageInfo record);

    ImageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImageInfo record);

    int updateByPrimaryKey(ImageInfo record);

    //查询所有图标
    List<String> selectImage(@Param("category") Integer category);

    //查询所有图标
    @Select("select id,image from image_info where category=#{category}")
    List<ImageInfo> selectImageByCategory(@Param("category") Integer category);
}