package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserCommunityRelationRecord;

public interface UserCommunityRelationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCommunityRelationRecord record);

    int insertSelective(UserCommunityRelationRecord record);

    UserCommunityRelationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCommunityRelationRecord record);

    int updateByPrimaryKey(UserCommunityRelationRecord record);

    /**
     * 添加删除记录
     *
     * @param record
     */
    void updateDeletedStatusByAuthId(UserCommunityRelationRecord record);

    /**
     * 添加处理记录
     *
     * @param record
     */
    void updateHandleStatusByAuthId(UserCommunityRelationRecord record);
}