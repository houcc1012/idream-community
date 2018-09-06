package com.idream.commons.lib.dto.activity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "活动标签库")
public class ActivityTagResponseDto {

    @ApiModelProperty(value = "活动标签id")
    private Integer id;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "标签属性")
    private Integer type;

    @ApiModelProperty(value = "创建人")
    private String adminNickName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAdminNickName() {
        return adminNickName;
    }

    public void setAdminNickName(String adminNickName) {
        this.adminNickName = adminNickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
