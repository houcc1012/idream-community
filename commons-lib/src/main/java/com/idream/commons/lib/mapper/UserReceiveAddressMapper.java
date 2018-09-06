package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserReceiveAddress;

public interface UserReceiveAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserReceiveAddress record);

    int insertSelective(UserReceiveAddress record);

    UserReceiveAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserReceiveAddress record);

    int updateByPrimaryKey(UserReceiveAddress record);
}