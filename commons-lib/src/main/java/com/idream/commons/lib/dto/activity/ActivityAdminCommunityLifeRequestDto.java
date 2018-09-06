package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/8/30 14:41
 * @Description:
 */
@ApiModel("活动精彩查询请求参数")
public class ActivityAdminCommunityLifeRequestDto extends PagesParam{

    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("状态 2正常 3已屏蔽")
    private Byte status;
    @ApiModelProperty("精彩内容")
    private String content;
    @ApiModelProperty("昵称")
    private String nickName;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

