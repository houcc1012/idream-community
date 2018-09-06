package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.admin.*;
import com.idream.commons.lib.model.PromotionRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PromotionRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PromotionRecord record);

    int insertSelective(PromotionRecord record);

    PromotionRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PromotionRecord record);

    int updateByPrimaryKey(PromotionRecord record);

    //书屋推广
    List<BookHousePromotionDto> getBookPromotionList(BookHousePromotionParams bookHousePromotionParams);

    //书屋推广 详细数据
    List<BookHousePromotionDetailDto> getBookPromotionDetailList(BookHousePromotionDetailParams bookHousePromotionDetailParams);

    //其他推广 详细数据
    List<BookHousePromotionDetailDto> getOtherPromotionDetailList(OtherPromotionDetailParams otherPromotionDetailParams);

    @Select("select count(*) from promotion_record where user_id = #{userId}")
    Integer getPromotionRecord(@Param("userId") Integer userId);
}