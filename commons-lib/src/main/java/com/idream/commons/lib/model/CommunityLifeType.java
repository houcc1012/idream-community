package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 邻里生活类型
 */
@ApiModel("邻里生活类型")
public class CommunityLifeType {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 类别名
     */
    @ApiModelProperty(value = "类别名")
    private String typeName;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 选中图标
     */
    @ApiModelProperty(value = "选中图标")
    private String iconSel;

    /**
     * 状态1正常2删除
     */
    @ApiModelProperty(value = "状态1正常2删除")
    private Byte status;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getIconSel() {
        return iconSel;
    }

    public void setIconSel(String iconSel) {
        this.iconSel = iconSel == null ? null : iconSel.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}