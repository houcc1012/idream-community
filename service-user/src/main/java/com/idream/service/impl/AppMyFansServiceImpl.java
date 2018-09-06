package com.idream.service.impl;

import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.wangyi.AddFriendRequestParams;
import com.idream.commons.lib.dto.wangyi.DeleteFriendRequestParam;
import com.idream.commons.lib.enums.CommunityEnum;
import com.idream.commons.lib.enums.EventEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.*;
import com.idream.commons.lib.util.PageRowsUtils;
import com.idream.commons.mvc.annotation.Achievement;
import com.idream.service.AppMyFansService;
import com.idream.service.UserIMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/2.
 */
@Service
public class AppMyFansServiceImpl implements AppMyFansService {


    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private CommunityLifeMapper communityLifeMapper;

    @Autowired
    private UserAttentionMapper userAttentionMapper;

    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;

    @Autowired
    private UserIMService userIMService;

    @Autowired
    private EgisAttentionRecordMapper egisAttentionRecordMapper;

    @Autowired
    private CommunityLifeTimeLineMapper communityLifeTimeLineMapper;

    /**
     * @param : authUserId
     */
    @Override
    public boolean getWetherAuthenticated(int authUserId) {

        int count = communityLifeMapper.getWetherAuthenticated(authUserId);
        boolean res = false;
        if (count > 0) {
            res = true;
        }
        return res;
    }

    /**
     * @param : authUserId
     * @param : userId
     */
    @Override
    @Achievement(eventType = EventEnum.EventType.ATTENTION)
    public Integer addMyNotice(JSONPublicParam<AppUserIdParam> params) {

        Integer userId = params.getRequestParam().getUserId();
        Integer authUserId = params.getAuthUserInfo().getUserId();
        if(userId.equals(authUserId)){
            throw new BusinessException("自己不能关注自己！");
        }
        Integer id = userAttentionMapper.selectIdByTwoUserId(authUserId, userId);
        if (id == null) {
            UserAttention userAttention = new UserAttention();
            Date date = new Date();
            userAttention.setUserId(authUserId);
            userAttention.setTargetUserId(userId);
            userAttention.setCreateTime(date);
            userAttention.setUpdateTime(date);

            userAttentionMapper.insert(userAttention);
            //IM添加好友
            AddFriendRequestParams param = new AddFriendRequestParams();
            param.setUserId(authUserId);
            param.setFriendUserId(userId);
            int i = userIMService.addIMFriend(param);
            if (i != 1) {
                throw new BusinessException(RetCodeConstants.WANGYI_ADDFRIEND_FAILED, "添加好友失败");
            }

            return userId;

        } else {
            throw new BusinessException(RetCodeConstants.NOTICED_ERROR, "已关注过，请勿重复操作");
        }
    }

    /**
     * @param : authUserId
     * @param : userId
     */
    @Override
    public void deleteMyNotice(JSONPublicParam<AppUserIdParam> params) {

        Integer userId = params.getRequestParam().getUserId();
        Integer authUserId = params.getAuthUserInfo().getUserId();
        Integer id = userAttentionMapper.selectIdByTwoUserId(authUserId, userId);
        int res = 0;
        if (null != id) {
            res = userAttentionMapper.deleteByPrimaryKey(id);
        }
        if (res == 0) {
            throw new BusinessException(RetCodeConstants.OPER_FAIL, "取消失败(未关注过或者已取消关注)");
        } else {
            //删除动态查看时间线相关数据
            int num = communityLifeTimeLineMapper.deleteByUserIdAndLifeId(authUserId, userId);
            logger.info("删除了" + num + "条与该用户相关的动态数据！");
            //IM删除好友
            DeleteFriendRequestParam param = new DeleteFriendRequestParam();
            param.setUserId(authUserId);
            param.setFriendUserId(userId);
            int i = userIMService.deleteFriend(param);
            if (i != 1) {
                throw new BusinessException(RetCodeConstants.WANGYI_DELETEFRIEND_FAILED, "删除好友失败");
            }
        }
    }

    /**
     * @param : authUserId
     * @param : userId
     */
    @Override
    public Boolean getWhetherAttended(int authUserId, int userId) {

        Integer id = userAttentionMapper.selectIdByTwoUserId(authUserId, userId);
        boolean res = true;
        if (null == id) {
            res = false;
        }
        return res;
    }

    /**
     * @param : authUserId
     */
    @Override
    public PagesDto<AppFansInfoDto> getMyFansList(AppSearchFansInfoParam params) {

        int authUserId = params.getAuthUserId();
        int page = params.getPage();
        int rows = params.getRows();

        //查询分界的关注ID
        EgisAttentionRecord param = egisAttentionRecordMapper.selectByUserId(authUserId);
        if (null != param) {
            params.setAttendId(param.getAttentionId());
        }
        List<AppFansInfoDto> list = userAttentionMapper.getMyFansList(params);
        int count = userAttentionMapper.getMyFansListCount(params);
        return new PagesDto(PageRowsUtils.getPageObj(list, page, rows), count, page, rows);
    }

    /**
     * @param : params
     */
    @Override
    public PagesDto<AppFansInfoDto> getMyNewFansList(AppSearchFansInfoParam params) {

        int page = params.getPage();
        int rows = params.getRows();
        int userId = params.getAuthUserId();
        EgisAttentionRecord param = egisAttentionRecordMapper.selectByUserId(userId);
        if (null != param) {
            params.setAttendId(param.getAttentionId());
        }
        List<AppFansInfoDto> list = userAttentionMapper.getMyNewFansList(params);
        int count = userAttentionMapper.getMyNewFansListCount(params);
        return new PagesDto(PageRowsUtils.getPageObj(list, page, rows), count, page, rows);
    }

    @Override
    public void updateMyNewFansList(JSONPublicParam<UpdateMyNewFansFlagParams> params) {
        int userId = params.getAuthUserInfo().getUserId();
        EgisAttentionRecord param = egisAttentionRecordMapper.selectByUserId(userId);
        Date date = new Date();
        if(param == null){
            param = new EgisAttentionRecord();
            param.setAttentionId(params.getRequestParam().getId());
            param.setUserId(userId);
            param.setCreateTime(date);
            param.setUpdateTime(date);
            //新增分界记录
            egisAttentionRecordMapper.insert(param);
        }else{
            if(param.getId() < params.getRequestParam().getId()){
                param.setAttentionId(params.getRequestParam().getId());
                param.setUserId(userId);
                param.setUpdateTime(date);
                param.setId(param.getId());
                //修改分界记录
                egisAttentionRecordMapper.updateByPrimaryKeySelective(param);
            }
        }
    }

    /**
     * @param : authUserId
     */
    @Override
    public List<AppCommunityInfoDto> getMyCommunity(int authUserId) {

        return userCommunityRelationMapper.getMyCommunity(authUserId);
    }

    /**
     * @param : adminSearchFansInfoParam
     */
    @Override
    public PagesDto<AdminFansInfoDto> getFansList(AdminSearchFansInfoParam adminSearchFansInfoParam) {

        List<AdminFansInfoDto> fansList = new ArrayList<AdminFansInfoDto>();
        int type = adminSearchFansInfoParam.getType();
        int userId = adminSearchFansInfoParam.getUserId();
        int page = adminSearchFansInfoParam.getPage();
        int rows = adminSearchFansInfoParam.getRows();
        // PageHelper.startPage(page,rows);
        adminSearchFansInfoParam.setPage(PageRowsUtils.getPage(page, rows));
        int count = 0;
        if (type == CommunityEnum.SearchTypeEnum.Myfans.getCode().intValue()) {
            fansList = userAttentionMapper.getFansListByAttendMe(adminSearchFansInfoParam);
            count = userAttentionMapper.getAttendMeCount(adminSearchFansInfoParam);
        } else if (type == CommunityEnum.SearchTypeEnum.MyAttended.getCode().intValue()) {
            fansList = userAttentionMapper.getFansListByAttendOther(adminSearchFansInfoParam);
            count = userAttentionMapper.getAttendOtherCount(adminSearchFansInfoParam);
        } else {
            fansList = userAttentionMapper.getFansListByAttendEachOther(adminSearchFansInfoParam);
            count = userAttentionMapper.getAttendEachOtherCount(adminSearchFansInfoParam);
        }

        //PageInfo<AdminFansInfoDto> pageInfo = new PageInfo<>(fansList);
        return new PagesDto(fansList, count, page, rows);
    }

    /**
     * @param : authUserId
     */
    @Override
    public PagesDto<AppFansInfoDto> getMyAttendList(AppMyAttendParam params) {

        int authUserId = params.getAuthUserId();
        int page = params.getPage();
        int rows = params.getRows();
        AdminSearchFansInfoParam param = new AdminSearchFansInfoParam();
        param.setUserId(authUserId);
        param.setPage(PageRowsUtils.getPage(page, rows));

        List<AppFansInfoDto> list = userAttentionMapper.getMyAttendList(param);
        PageInfo<AppFansInfoDto> pageInfo = new PageInfo<>(list);
        int count = userAttentionMapper.getMyAttendListCount(param);
        return new PagesDto(list, count, page, rows);
    }

}
