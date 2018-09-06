package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.information.InformationUserAgeDto;
import com.idream.commons.lib.dto.appactivity.UserAgeInfoDto;
import com.idream.commons.lib.model.UserAgeInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserAgeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAgeInfo record);

    int insertSelective(UserAgeInfo record);

    UserAgeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAgeInfo record);

    int updateByPrimaryKey(UserAgeInfo record);

    List<UserAgeInfoDto> getAgeInfo();

    @Select("SELECT id,`name` as value,`code` FROM user_age_info ORDER BY sorted")
    List<InformationUserAgeDto> selectAll();
}