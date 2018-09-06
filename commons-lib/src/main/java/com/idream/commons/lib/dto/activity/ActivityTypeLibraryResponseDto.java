package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.model.ActivityTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/6/11 10:11
 * @Description:
 */
@ApiModel(value = "活动类型库查询返回参数")
public class ActivityTypeLibraryResponseDto {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("活动类型名")
    private String typeName;
    @ApiModelProperty("类型属性 1主类型，2辅类型")
    private Integer kind;
    @ApiModelProperty("icon置灰")
    private String icon;
    @ApiModelProperty("icon高亮")
    private String iconLight;
    @ApiModelProperty("背景")
    private String background;
    @ApiModelProperty("关联标签")
    private List<ActivityTag> tagList;
    @ApiModelProperty("创建人")
    private String creater;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty(value = "描述")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
        this.typeName = typeName;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconLight() {
        return iconLight;
    }

    public void setIconLight(String iconLight) {
        this.iconLight = iconLight;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<ActivityTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<ActivityTag> tagList) {
        this.tagList = tagList;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

