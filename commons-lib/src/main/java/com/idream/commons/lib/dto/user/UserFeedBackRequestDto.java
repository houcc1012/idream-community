package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/8 16:10
 * @Description:
 */
@ApiModel("用户反馈接受参数实体封装")
public class UserFeedBackRequestDto {

    /**
     * 反馈内容
     */
    @ApiModelProperty(value = "反馈内容")
    @NotBlank(message = "反馈内容不能为空")
    private String feedbackContent;

    /**
     * 反馈图片, 多张图以逗号隔开
     */
    @ApiModelProperty(value = "反馈图片, 多张图以逗号隔开")
    private String feedbackImage;

    /**
     * 用户联系方式
     */
    @ApiModelProperty(value = "用户联系方式")
    private String userContactInfo;


    public UserFeedBackRequestDto() {
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getFeedbackImage() {
        return feedbackImage;
    }

    public void setFeedbackImage(String feedbackImage) {
        this.feedbackImage = feedbackImage;
    }

    public String getUserContactInfo() {
        return userContactInfo;
    }

    public void setUserContactInfo(String userContactInfo) {
        this.userContactInfo = userContactInfo;
    }


}

