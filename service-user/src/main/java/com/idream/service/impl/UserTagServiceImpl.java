package com.idream.service.impl;

import com.google.common.collect.Lists;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.user.AdminAddUserTagParams;
import com.idream.commons.lib.dto.user.AdminDeleteUserTagParams;
import com.idream.commons.lib.dto.user.UserTagResponseDto;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.mapper.UserTagMapper;
import com.idream.commons.lib.model.UserInfo;
import com.idream.commons.lib.model.UserTag;
import com.idream.service.UserTagService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserTagServiceImpl implements UserTagService {

    @Autowired
    private UserTagMapper userTagMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserTagResponseDto> selectUserTagListByLabel(String label) {
        List<UserTagResponseDto> dtos = null;
        if (StringUtils.isNotEmpty(label)) {
            List<UserTagResponseDto> dto = new ArrayList<>();
            List<UserTag> userTags = userTagMapper.selectUserTagListByUserTagLabel(label);
            for (UserTag userTag : userTags) {
                UserTagResponseDto userTagResponseDto = new UserTagResponseDto();
                if (userTag.getPid().equals(0)) {
                    userTagResponseDto.setId(userTag.getId());
                    userTagResponseDto.setPid(0);
                    userTagResponseDto.setLabel(userTag.getLabel());
                    dto.add(userTagResponseDto);
                } else {
                    UserTag userTags1 = userTagMapper.selectAllSecondUserTagById(userTag.getPid());
                    //设置二级标签信息
                    UserTagResponseDto userTagResponseDto1 = new UserTagResponseDto();
                    List<UserTagResponseDto> list = new ArrayList<>();
                    userTagResponseDto1.setId(userTag.getId());
                    userTagResponseDto1.setPid(userTag.getPid());
                    userTagResponseDto1.setLabel(userTag.getLabel());
                    list.add(userTagResponseDto1);
                    //设置一级标签信息
                    userTagResponseDto.setId(userTags1.getId());
                    userTagResponseDto.setPid(userTags1.getPid());
                    userTagResponseDto.setLabel(userTags1.getLabel());
                    userTagResponseDto.setSecondUserTagList(list);
                    dto.add(userTagResponseDto);
                }
            }
            return dto;
        } else {
            //label为空
            //通过标签查询标签库
            List<UserTag> userTagList = userTagMapper.selectUserTagListByLabel(label);
            if (CollectionUtils.isNotEmpty(userTagList)) {
                dtos = buildUserTagTree(0, userTagList);
            }
            return dtos;
        }
    }

    /**
     * 处理二级用户标签
     *
     * @param parentPid
     * @param userTagList
     */
    private List<UserTagResponseDto> buildUserTagTree(int parentPid, List<UserTag> userTagList) {
        List<UserTagResponseDto> list = Lists.newArrayList();
        for (UserTag n : userTagList) {
            if (parentPid == n.getPid()) {
                UserTagResponseDto userTagResponseDto = new UserTagResponseDto();
                userTagResponseDto.setId(n.getId());
                userTagResponseDto.setLabel(n.getLabel());
                userTagResponseDto.setPid(n.getPid());
                userTagResponseDto.setSecondUserTagList(buildUserTagTree(n.getId(), userTagList));
                list.add(userTagResponseDto);
            }
        }
        return list;
    }

    /**
     * 新增和编辑用户标签
     */
    @Override
    public int insertUserTag(Integer authUserId, AdminAddUserTagParams adminAddUserTagParams) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authUserId);
        String nickName = userInfo.getNickName();
        int u = 0;
        if (adminAddUserTagParams.getTagId().equals(-1)) {
            if (adminAddUserTagParams.getPid().equals(0)) {
                int i = userTagMapper.selectLabelCount(adminAddUserTagParams.getLabel(), adminAddUserTagParams.getPid());
                if (i == 0) {
                    UserTag userTag = new UserTag();
                    Date date = new Date();
                    userTag.setLabel(adminAddUserTagParams.getLabel());
                    userTag.setPid(0);
                    userTag.setAdminUserId(authUserId);
                    userTag.setAdminNickName(nickName);
                    userTag.setStatus(1);
                    userTag.setCategory((byte) 1);
                    userTag.setCreateTime(date);
                    userTag.setUpdateTime(date);
                    u = userTagMapper.insertSelective(userTag);
                } else {
                    throw new BusinessException(RetCodeConstants.USERTAG_REPEAT, "用户标签重复...");
                }
            } else {
                UserTag ut = userTagMapper.selectByPrimaryKey(adminAddUserTagParams.getPid());
                if (!ut.getLabel().equals(adminAddUserTagParams.getLabel())) {
                    int i = userTagMapper.selectLabelCount(adminAddUserTagParams.getLabel(), adminAddUserTagParams.getPid());
                    if (i == 0) {
                        UserTag userTag = new UserTag();
                        Date date = new Date();
                        userTag.setLabel(adminAddUserTagParams.getLabel());
                        userTag.setPid(adminAddUserTagParams.getPid());
                        userTag.setAdminUserId(authUserId);
                        userTag.setAdminNickName(nickName);
                        userTag.setStatus(1);
                        userTag.setCategory((byte) 1);
                        userTag.setCreateTime(date);
                        userTag.setUpdateTime(date);
                        u = userTagMapper.insertSelective(userTag);
                    } else {
                        throw new BusinessException(RetCodeConstants.USERTAG_REPEAT, "用户标签重复...");
                    }
                } else {
                    throw new BusinessException(RetCodeConstants.USERTAG_REPEAT, "用户标签重复...");
                }
            }
        } else {
            UserTag userTag = new UserTag();
            Date date = new Date();
            userTag.setAdminUserId(authUserId);
            userTag.setAdminNickName(nickName);
            userTag.setLabel(adminAddUserTagParams.getLabel());
            userTag.setId(adminAddUserTagParams.getTagId());
            userTag.setUpdateTime(date);
            u = userTagMapper.updateByPrimaryKeySelective(userTag);
        }
        return u;
    }

    @Override
    public int deleteUserTag(AdminDeleteUserTagParams adminDeleteUserTagParams) {
        int u = 0;
        if (!adminDeleteUserTagParams.getPid().equals(0)) {
            UserTag userTag = new UserTag();
            userTag.setId(adminDeleteUserTagParams.getId());
            userTag.setStatus(2);
            u = userTagMapper.updateByPrimaryKeySelective(userTag);
        } else {
            //查询所有的二级标签
            List<Integer> ids = userTagMapper.selectAllSecondUserTagByPid(adminDeleteUserTagParams.getId());
            //添加父标签(一级标签)
            ids.add(adminDeleteUserTagParams.getId());
            for (Integer id : ids) {
                UserTag userTag = new UserTag();
                userTag.setId(id);
                userTag.setStatus(2);
                //删除一级标签和二级标签
                u = userTagMapper.updateByPrimaryKeySelective(userTag);
            }
        }
        return u;
    }

}
