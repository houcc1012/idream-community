package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 权限表
 */
@ApiModel("权限表")
public class SysPermission {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String name;

    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    private String uri;

    /**
     * 菜单id
     */
    @ApiModelProperty(value = "菜单id")
    private String menuId;

    /**
     * 父级
     */
    @ApiModelProperty(value = "父级")
    private String pid;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String path;

    /**
     * 方法
     */
    @ApiModelProperty(value = "方法")
    private String method;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
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