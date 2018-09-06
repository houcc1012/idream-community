package com.idream.service;

import com.idream.commons.lib.dto.user.AdminAddUserTagParams;
import com.idream.commons.lib.dto.user.AdminDeleteUserTagParams;
import com.idream.commons.lib.dto.user.UserTagResponseDto;

import java.util.List;

/**
 * 用户标签库
 *
 * @param
 *
 * @author zhanfeifei
 */
public interface UserTagService {

    //通过用户标签查询标签库,并且分页
    List<UserTagResponseDto> selectUserTagListByLabel(String label);

    //新增用户标签
    int insertUserTag(Integer authUserId, AdminAddUserTagParams adminAddUserTagParams);

    //删除标签
    int deleteUserTag(AdminDeleteUserTagParams adminDeleteUserTagParams);
}
