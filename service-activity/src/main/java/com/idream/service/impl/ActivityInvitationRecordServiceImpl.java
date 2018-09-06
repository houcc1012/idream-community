/**
 *
 */
package com.idream.service.impl;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.activity.ShareInfoDto;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.enums.MarketingEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.ActivityInvitationRecordMapper;
import com.idream.commons.lib.mapper.IntegrationConfigMapper;
import com.idream.commons.lib.mapper.IntegrationRecordMapper;
import com.idream.commons.lib.model.ActivityInvitationRecord;
import com.idream.feign.FeginScoreService;
import com.idream.service.ActivityInvitationRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author xiaogang
 */
@Service
public class ActivityInvitationRecordServiceImpl implements ActivityInvitationRecordService {

    @Autowired
    private ActivityInvitationRecordMapper activityInvitationRecordMapper;

    private static final Logger logger = LoggerFactory.getLogger(ActivityInvitationRecordServiceImpl.class);

    //邀请成功
    private static final int INVITATION_SUCCESS = 1;

    @Autowired
    private IntegrationConfigMapper integrationConfigMapper;

    @Autowired
    private IntegrationRecordMapper integrationRecordMapper;

    @Autowired
    private FeginScoreService feginUserScoreService;

    public static final String firstShareScore = "activity_first_share_score";

    public static final String shareScore = "activity_share_score";

    public static final String maxShareScore = "activity_max_share_score";

    @Override
    public void addInvitationRecord(JSONPublicParam<ShareInfoDto> param) {

        // 业务参数
        ShareInfoDto shareInfoDto = param.getRequestParam();
        // 用户信息
        AuthUserInfo user = param.getAuthUserInfo();

        // 查询用户活动邀请记录
        ActivityInvitationRecord exsist = activityInvitationRecordMapper
                .getIdByOtherId(user.getUserId(), shareInfoDto.getActivityId(), shareInfoDto.getSharedUserId());
        if (exsist == null) {
            Date date = new Date();
            ActivityInvitationRecord air = new ActivityInvitationRecord();
            air.setActivityId(shareInfoDto.getActivityId());
            air.setInvitationId(shareInfoDto.getSharedUserId());
            air.setAcceptId(user.getUserId());
            air.setStatus(INVITATION_SUCCESS);
            air.setCreateTime(date);
            air.setUpdateTime(date);
            activityInvitationRecordMapper.insertSelective(air);
            //邀请人获得邀请积分
            //saveUserScore(air.getActivityId(), shareInfoDto.getSharedUserId());
        } else {
            throw new BusinessException(RetCodeConstants.ALDREAY_JOIN_ACTIVITY, "已经参加过活动");
        }
    }

    @Override
    public int saveUserScore(int activityId, int userId) {

        //获取用户此活动已经获取的积分值
        Integer score = integrationRecordMapper.getScoreByActivityIdAndUserId(userId);
        //获取用户分享活动所能获得的最大积分值
        Integer maxShare = integrationConfigMapper.getIntegerByCode(maxShareScore);
        if (score == 0) {
            int firstShare = integrationConfigMapper.getIntegerByCode(firstShareScore);
            //增加积分
            feginUserScoreService.addUserScore(firstShare, MarketingEnum.ScoreFromType.SHARE.getCode(), MarketingEnum.CouponRecordType.GET.getCode(), 0, activityId, userId);
            return firstShare;
        } else if (score < maxShare) {
            int share = integrationConfigMapper.getIntegerByCode(shareScore);
            //增加积分
            feginUserScoreService.addUserScore(share, MarketingEnum.ScoreFromType.SHARE.getCode(), MarketingEnum.CouponRecordType.GET.getCode(), 0, activityId, userId);
            return share;
        } else {
            logger.info("分享活动获取积分已达上限，不允许添加积分！");
            return 0;
        }
    }

}
