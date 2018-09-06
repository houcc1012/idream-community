package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserGetuiAliasInfo;

public interface UserGetuiAliasInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGetuiAliasInfo record);

    int insertSelective(UserGetuiAliasInfo record);

    UserGetuiAliasInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGetuiAliasInfo record);

    int updateByPrimaryKey(UserGetuiAliasInfo record);
}