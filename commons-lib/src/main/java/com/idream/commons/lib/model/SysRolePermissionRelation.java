package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 */
@ApiModel("")
public class SysRolePermissionRelation {
    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Integer sysRoleId;

    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private Integer sysPermissionId;

    /**
     * 资源类型
     */
    @ApiModelProperty(value = "资源类型")
    private String resourceType;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String path;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     *
     */
    @ApiModelProperty(value = "")
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

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Integer getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(Integer sysPermissionId) {
        this.sysPermissionId = sysPermissionId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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