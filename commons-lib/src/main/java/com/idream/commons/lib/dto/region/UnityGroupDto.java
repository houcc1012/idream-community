package com.idream.commons.lib.dto.region;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "建筑物信息返回dto")
public class UnityGroupDto {
    @ApiModelProperty(value = "unity 编号")
    private String id;
    @ApiModelProperty(value = "unity名称")
    private String name;
    @ApiModelProperty(value = "unity 类型 1-小区;2-写字楼,3-公告牌;4-体育场....")
    private Byte type;
    @ApiModelProperty(value = "unity 人数")
    private Integer peopleNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }
}
