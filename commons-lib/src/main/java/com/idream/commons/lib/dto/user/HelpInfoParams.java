package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 帮助表
 */
@ApiModel("帮助中心参数表")
public class HelpInfoParams {

    @ApiModelProperty(value = "主键,新增时id不传")
    private Integer id;

    @ApiModelProperty(value = "类型id")
    @NotNull(message = "类型不能为空")
    private Integer typeId;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "标题不能为空")
    @Size(max = 30, message = "标题长度不能大于30字")
    private String title;

    @ApiModelProperty(value = "内容")
    @NotBlank(message = "内容不能为空")
    @Size(max = 1000, message = "内容长度不能大于1000字")
    private String content;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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