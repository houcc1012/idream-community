package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.user.AppHelpInfoDto;
import com.idream.commons.lib.dto.user.HelpInfoDto;
import com.idream.commons.lib.dto.user.HelpInfoSearchParams;
import com.idream.commons.lib.model.HelpInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.poi.ss.formula.functions.Value;

import java.util.List;

public interface HelpInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HelpInfo record);

    int insertSelective(HelpInfo record);

    HelpInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HelpInfo record);

    int updateByPrimaryKey(HelpInfo record);

    List<HelpInfoDto> selectAllList(@Param(value = "title") String title, @Param(value = "typeId") Integer typeId);

    HelpInfoDto selectHelpInfoById(Integer id);

    @Select("select id,title from help_info where type_id = #{typeId}")
    List<AppHelpInfoDto> selectHelpTitleByTypeId(int typeId);

    @Select("select content from help_info where id = #{id}")
    String selectContentByHelpTitle(int id);
}