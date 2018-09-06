package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.dto.activity.AppUserAchievementDto;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.model.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author charles
 */
@ApiModel("用户详情实体类")
public class AppUserInfoDto {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像地址")
    private String image;
    @ApiModelProperty("背景图地址")
    private String backgroundImage;
    @ApiModelProperty("性别,男1,女2")
    private Integer gender;
    @ApiModelProperty("生日")
    private Date birthday;
    @ApiModelProperty("职业id")
    private Integer professionId;
    @ApiModelProperty("职业名")
    private String professionName;
    @ApiModelProperty("个性签名")
    private String signature;
    @ApiModelProperty("认证社区")
    private List<CommunityDisplayInfo> communities;
    @ApiModelProperty("活动数量")
    private Integer activityCount;
    @ApiModelProperty("粉丝数")
    private Integer fansCount;
    @ApiModelProperty("关注数")
    private Integer attentionCount;
    @ApiModelProperty("赞数")
    private Integer approvalCount;
    @ApiModelProperty("签到数")
    private Integer signCount;
    @ApiModelProperty("是否游客")
    private Boolean visitor;
    @ApiModelProperty("成就称号")
    private List<AppUserAchievementDto> achievements;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("微信状态,true绑定")
    private Boolean wechatStatus = false;

    public void setWechatStatus(Boolean wechatStatus) {
        this.wechatStatus = wechatStatus;
    }

    public void setAchievements(List<AppUserAchievementDto> achievements) {
        this.achievements = achievements;
    }

    public List<AppUserAchievementDto> getAchievements() {
        return achievements;
    }

    public Boolean getVisitor() {
        return visitor;
    }

    public String getNickName() {
        return nickName;
    }

    public String getImage() {
        return image;
    }

    public Integer getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public String getProfessionName() {
        return professionName;
    }

    public String getSignature() {
        return signature;
    }


    public Integer getActivityCount() {
        return activityCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public Integer getApprovalCount() {
        return approvalCount;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public List<CommunityDisplayInfo> getCommunities() {
        return communities;
    }

    public void setCommunities(List<CommunityDisplayInfo> communities) {
        this.communities = communities;
    }

    public void setActivityCount(Integer activityCount) {
        this.activityCount = activityCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public void setApprovalCount(Integer approvalCount) {
        this.approvalCount = approvalCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public AppUserInfoDto(UserInfo user) {
        this.userId = user.getId();
        this.nickName = user.getNickName();
        this.professionId = user.getProfessionId();
        this.professionName = user.getProfessionName();
        this.gender = user.getGender().intValue();
        this.birthday = user.getBirthday();
        this.image = user.getImage();
        this.visitor = user.getUserRole().equals(UserEnum.UserRoleEnum.VISITOR.getCode());
        this.phone = user.getPhone();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getWechatStatus() {
        return wechatStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @ApiModel("认证社区信息")
    static public class CommunityDisplayInfo {
        @ApiModelProperty("社区名")
        String communityName;
        @ApiModelProperty("社区排名")
        String numberOfCommunity;

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public void setNumberOfCommunity(String numberOfCommunity) {
            this.numberOfCommunity = numberOfCommunity;
        }

        public String getCommunityName() {
            return communityName;
        }

        public String getNumberOfCommunity() {
            return String.format("第%s位居民", numberOfCommunity);
        }
    }

}
