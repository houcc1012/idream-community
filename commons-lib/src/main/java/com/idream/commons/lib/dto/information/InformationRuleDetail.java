package com.idream.commons.lib.dto.information;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("信息收集信息相关")
public class InformationRuleDetail {
    @ApiModelProperty("用户年龄列表")
    private List<InformationUserAgeDto> age;
    @ApiModelProperty("用户标签")
    private List<InformationUserTagDto> tag;

    public List<InformationUserAgeDto> getAge() {
        return age;
    }

    public void setAge(List<InformationUserAgeDto> age) {
        this.age = age;
    }

    public List<InformationUserTagDto> getTag() {
        return tag;
    }

    public void setTag(List<InformationUserTagDto> tag) {
        this.tag = tag;
    }
}
