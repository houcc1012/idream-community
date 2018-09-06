package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.AdminCommunityAuthDto;
import com.idream.commons.lib.dto.activity.AdminCommunityAuthParams;
import com.idream.commons.lib.dto.app.CommunityInfoResponseDto;
import com.idream.commons.lib.dto.app.CommunityListSearchRequestDto;
import com.idream.commons.lib.enums.CommunityEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.RegionInfo;
import com.idream.commons.lib.model.UserCommunityRelation;
import com.idream.commons.lib.model.UserCommunityRelationRecord;
import com.idream.service.CommunityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jeffery
 */
@Service
public class CommunityInfoServiceImpl implements CommunityInfoService {
    @Autowired
    private CommunityInfoMapper communityInfoMapper;
    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;
    @Autowired
    private HotCityMapper hotCityMapper;
    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;
    @Autowired
    private UserCommunityRelationRecordMapper userCommunityRelationRecordMapper;
    @Autowired
    private ActivityTaskMapper activityTaskMapper;
    @Autowired
    private UserRegionRelationMapper userRegionRelationMapper;
    @Autowired
    private RegionGroupInfoMapper regionGroupInfoMapper;

    @Override
    public PagesDto<CommunityInfoResponseDto> selectCommunityInfoListByCommunityName(Integer userId, CommunityListSearchRequestDto communityListSearchRequestDto) {
        int page = communityListSearchRequestDto.getPage();
        int rows = communityListSearchRequestDto.getRows();
        PageHelper.startPage(page, rows);
        List<CommunityInfoResponseDto> cList = regionGroupInfoMapper.selectCommunityInfoListByCommunityName(communityListSearchRequestDto.getCityCode(), communityListSearchRequestDto.getCommunityName(), communityListSearchRequestDto.getLongitude(), communityListSearchRequestDto.getLatitude());
        PageInfo pageInfo = new PageInfo<>(cList);
        return new PagesDto(cList, pageInfo.getTotal(), page, rows);
    }

    @Override
    public PagesDto<AdminCommunityAuthDto> getAuthUser(AdminCommunityAuthParams query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        List<AdminCommunityAuthDto> list = userCommunityRelationMapper.selectAuthUserByQuery(query);
        PageInfo<AdminCommunityAuthDto> info = new PageInfo<>(list);
        return new PagesDto<>(info.getList(), info.getTotal(), query.getPage(), query.getRows());
    }

    @Override
    public void updateAuthUser(Integer adminUserId, Integer authId, Byte status) {
        updateAuthUserCommunity(adminUserId, authId, status);
    }

    private void updateAuthUserCommunity(Integer adminUserId, Integer authId, Byte status) {
        UserCommunityRelation exist = userCommunityRelationMapper.selectByPrimaryKey(authId);
        if (exist == null) {
            throw new BusinessException(RetCodeConstants.AUTH_USER_INFO_NO_EXIST, "认证的记录信息不存在");
        }
        if (!exist.getStatus().equals(CommunityEnum.AuthCommunityStatus.CHECKING.getCode())) {
            throw new BusinessException(RetCodeConstants.AUTH_USER_OPER_COMPLETED, "请勿重复操作");
        }
        UserCommunityRelation relation = new UserCommunityRelation();
        relation.setId(authId);
        relation.setStatus(status);
        userCommunityRelationMapper.updateByPrimaryKeySelective(relation);
        //保存记录
        UserCommunityRelationRecord record = new UserCommunityRelationRecord();
        record.setAuthId(authId);
        Date date = new Date();
        record.setHandleResult(status);
        record.setHandleTime(date);
        record.setUpdateTime(date);
        record.setAdminUserId(adminUserId);
        userCommunityRelationRecordMapper.updateHandleStatusByAuthId(record);
    }


    @Override
    public void updateAuthUserCommunitySuccess(Integer userId, Integer authId) {
        updateAuthUserCommunity(userId, authId, CommunityEnum.AuthCommunityStatus.PASS.getCode());
    }

    @Override
    public void updateAuthUserCommunityFail(Integer userId, Integer authId) {
        updateAuthUserCommunity(userId, authId, CommunityEnum.AuthCommunityStatus.REJECT.getCode());
        //删除认证的大社区
        deleteUserRegion(userId);
    }

    private void deleteUserRegion(Integer userId) {
        List<RegionInfo> regionInfos = userRegionRelationMapper.selectRegionInfoListByUserId(userId);
        List<RegionInfo> regions = userCommunityRelationMapper.selectAuthRegionByUserId(userId);

        if (regionInfos.size() > regions.size()) {
            List<RegionInfo> collect = regionInfos.stream().filter(i -> !regions.contains(i)).collect(Collectors.toList());

            collect.forEach(i -> {
                userRegionRelationMapper.deleteByUserIdAndRegionId(userId, i.getId());
            });
        }
    }


}




