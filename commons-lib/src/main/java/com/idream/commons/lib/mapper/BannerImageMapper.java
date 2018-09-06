package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.user.AdminBannerUrlParams;
import com.idream.commons.lib.model.BannerImage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BannerImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BannerImage record);

    int insertSelective(BannerImage record);

    BannerImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BannerImage record);

    int updateByPrimaryKey(BannerImage record);

    //通过bannerId查询banner_image的image_id
    @Select("select id from banner_image where banner_id = #{bannerId}")
    List<Integer> selectImageIdByBannerId(@Param("bannerId") Integer bannerId);

    @Delete("delete from banner_image where banner_id=#{bannerId}")
    void deleteBatchByBannerId(@Param("bannerId") Integer bannerId);

    @Select("select * from banner_image where banner_id = #{bannerId}")
    List<BannerImage> selectByBannerId(@Param("bannerId") Integer bannerId);

    @Select("select image_url from banner_image where banner_id = #{bannerId}")
    List<String> selectStrImageByBannerId(@Param("bannerId") Integer bannerId);

    List<AdminBannerUrlParams> selectDisplayByTypeId(Integer typeId);

    @Select("select image_url from banner_info where type_id = 2 and display_enable =1 ")
    List<String> selectIntegrationImage();
}