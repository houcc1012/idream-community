package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.admin.AddPromotionTeamParams;
import com.idream.commons.lib.dto.admin.OtherPromotionDto;
import com.idream.commons.lib.dto.admin.OtherPromotionParams;
import com.idream.commons.lib.model.PromotionTeam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PromotionTeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PromotionTeam record);

    int insertSelective(PromotionTeam record);

    PromotionTeam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PromotionTeam record);

    int updateByPrimaryKey(PromotionTeam record);

    //其他推荐
    List<OtherPromotionDto> getOtherPromotionList(OtherPromotionParams otherPromotionParams);

    //后台管理 查询数据库中书屋的数量
    @Select("SELECT count(*) AS count FROM promotion_team WHERE province_code = #{provinceCode}")
    Integer selectPromotionTeamCount(@Param("provinceCode") String provinceCode);

    //新增推广 推广名称重复性校验
    Integer selectPromotionTeamCountByExample(AddPromotionTeamParams addPromotionTeamParams);
}