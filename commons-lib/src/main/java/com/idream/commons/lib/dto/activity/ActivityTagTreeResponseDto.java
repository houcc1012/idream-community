package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.user.UserTagResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/6/12 09:29
 * @Description:
 */
@ApiModel("活动类型关联活动标签查询返回参数")
public class ActivityTagTreeResponseDto {
    @ApiModelProperty(value = "标签id")
    private Integer id;

    @ApiModelProperty(value = "标签名")
    private String label;

    @ApiModelProperty(value = "父id")
    private Integer pid;

    @ApiModelProperty(value = "标签级别")
    private Integer level;

    @ApiModelProperty(value = "子级标签集合")
    private List<ActivityTagTreeResponseDto> childActivityTagList;

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

    public List<ActivityTagTreeResponseDto> getChildActivityTagList() {
        return childActivityTagList;
    }

    public void setChildActivityTagList(List<ActivityTagTreeResponseDto> childActivityTagList) {
        this.childActivityTagList = childActivityTagList;
    }
}

