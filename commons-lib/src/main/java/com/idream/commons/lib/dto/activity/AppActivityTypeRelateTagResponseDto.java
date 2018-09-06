package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.model.ActivityTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/7/31 15:11
 * @Description:
 */
@ApiModel("活动类型关联的标签返回参数")
public class AppActivityTypeRelateTagResponseDto {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "标签名")
    private String label;

    @ApiModelProperty(value = "父级默认0")
    private Integer pid;

    @ApiModelProperty(value = "级别")
    private Integer level;

    @ApiModelProperty(value = "子标签")
    private List<ActivityTag> childTagList;

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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<ActivityTag> getChildTagList() {
        return childTagList;
    }

    public void setChildTagList(List<ActivityTag> childTagList) {
        this.childTagList = childTagList;
    }
}

