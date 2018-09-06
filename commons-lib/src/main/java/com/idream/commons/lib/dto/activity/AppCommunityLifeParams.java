package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.annotation.SensitiveWordVaild;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "发布我的动态参数")
public class AppCommunityLifeParams {

    @ApiModelProperty(value = "内容")
    @Length(max = 1500, message = "内容长度最大1500字")
    @SensitiveWordVaild
    private String content;

    @ApiModelProperty(value = "类型")
    @NotNull(message = "类型不能为空")
    private Integer typeId;

    @ApiModelProperty(value = "图片")
    @Size(max = 9, min = 1, message = "图片张数必须为1-9张")
    private List<AppImageParam> imageList;

    @ApiModelProperty(value = "隐私级别 1-同社区;2-关注我的;3-仅自己可见;4-仅活动可见；5-选择社区可见", hidden = true)
    private Byte privacyLevel;

    @ApiModelProperty(value = "1动态圈,2活动圈")
    @NotNull(message = "动态来源不能为空")
    private Byte fromType;

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AppImageParam> getImageList() {
        return imageList;
    }

    public void setImageList(List<AppImageParam> imageList) {
        this.imageList = imageList;
    }

    public Byte getPrivacyLevel() {
        return privacyLevel;
    }

    public void setPrivacyLevel(Byte privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Byte getFromType() {
        return fromType;
    }

    public void setFromType(Byte fromType) {
        this.fromType = fromType;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

}
