package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.setting.SearchSensitiveWordsDto;
import com.idream.commons.lib.dto.setting.SearchSensitiveWordsParams;
import com.idream.commons.lib.model.SensitiveWord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SensitiveWordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SensitiveWord record);

    int insertSelective(SensitiveWord record);

    SensitiveWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SensitiveWord record);

    int updateByPrimaryKey(SensitiveWord record);

    List<SearchSensitiveWordsDto> selectSensitiveWordList(SearchSensitiveWordsParams param);

    //查找不重复的所有敏感字
    @Select("select word from sensitive_word group by word")
    List<String> findWordAllNotRepeat();

    @Select("select count(*) from sensitive_word where word = #{word}")
    int countByWord(@Param("word") String word);

    @Select("select count(*) from sensitive_word where word = #{word} and id = #{id}")
    int countByWordAndId(@Param("word") String word, @Param("id") Integer id);
}