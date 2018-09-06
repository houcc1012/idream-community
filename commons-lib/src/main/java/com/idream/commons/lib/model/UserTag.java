package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户标签表
 */
@ApiModel("用户标签表")
public class UserTag {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String label;

    /**
     * 父级,未使用
     */
    @ApiModelProperty(value = "父级,未使用")
    private Integer pid;

    /**
     * 管理员id
     */
    @ApiModelProperty(value = "管理员id")
    private Integer adminUserId;

    /**
     * 管理员昵称
     */
    @ApiModelProperty(value = "管理员昵称")
    private String adminNickName;

    /**
     * 状态,1正常2删除
     */
    @ApiModelProperty(value = "状态,1正常2删除")
    private Integer status;

    /**
     * 类别,1用户标签,2称号标签
     */
    @ApiModelProperty(value = "类别,1用户标签,2称号标签")
    private Byte category;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 标签图标
     */
    @ApiModelProperty(value = "标签图标")
    private String icon;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getAdminNickName() {
        return adminNickName;
    }

    public void setAdminNickName(String adminNickName) {
        this.adminNickName = adminNickName == null ? null : adminNickName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Byte getCategory() {
        return category;
    }

    public void setCategory(Byte category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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