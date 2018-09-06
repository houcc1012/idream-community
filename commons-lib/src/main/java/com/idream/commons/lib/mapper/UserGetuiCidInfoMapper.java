package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserGetuiCidInfo;

public interface UserGetuiCidInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGetuiCidInfo record);

    int insertSelective(UserGetuiCidInfo record);

    UserGetuiCidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGetuiCidInfo record);

    int updateByPrimaryKey(UserGetuiCidInfo record);
}