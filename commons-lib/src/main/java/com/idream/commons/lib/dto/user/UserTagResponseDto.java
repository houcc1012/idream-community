package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "用户标签")
public class UserTagResponseDto {

    @ApiModelProperty(value = "标签id")
    private Integer id;

    @ApiModelProperty(value = "标签名")
    private String label;

    @ApiModelProperty(value = "父id")
    private Integer pid;

    @ApiModelProperty(value = "二级标签")
    private List<UserTagResponseDto> secondUserTagList;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<UserTagResponseDto> getSecondUserTagList() {
        return secondUserTagList;
    }

    public void setSecondUserTagList(List<UserTagResponseDto> secondUserTagList) {
        this.secondUserTagList = secondUserTagList;
    }

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

}
