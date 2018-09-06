package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.MiniProgramNoticeTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MiniProgramNoticeTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MiniProgramNoticeTemplate record);

    int insertSelective(MiniProgramNoticeTemplate record);

    MiniProgramNoticeTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MiniProgramNoticeTemplate record);

    int updateByPrimaryKey(MiniProgramNoticeTemplate record);

    @Select("SELECT * from mini_program_notice_template WHERE template_id = #{templateId}")
    MiniProgramNoticeTemplate selectByTemplateId(@Param("templateId") String templateId);
}