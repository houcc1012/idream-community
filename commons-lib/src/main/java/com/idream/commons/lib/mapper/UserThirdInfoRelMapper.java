package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserThirdInfoRel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserThirdInfoRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserThirdInfoRel record);

    int insertSelective(UserThirdInfoRel record);

    UserThirdInfoRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserThirdInfoRel record);

    int updateByPrimaryKey(UserThirdInfoRel record);

    //查询用户openId
    @Select("SELECT DISTINCT param_value openId  FROM user_third_info_rel WHERE user_id = #{userId} AND param_code = #{paramCode} AND type = 1 ")
    String selectUserOpenIdByUserId(@Param("userId") Integer userId, @Param("paramCode") String paramCode);
}