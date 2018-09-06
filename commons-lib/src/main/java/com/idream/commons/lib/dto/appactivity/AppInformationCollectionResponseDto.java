package com.idream.commons.lib.dto.appactivity;

import java.util.List;

import com.idream.commons.lib.model.InformationRule;
import com.idream.commons.lib.model.TagInfoTree;
import com.idream.commons.lib.model.UserAgeInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("报名收集信息返货参数")
public class AppInformationCollectionResponseDto {

    @ApiModelProperty("用户收集信息")
    private List<InformationRule> informationrule;
    @ApiModelProperty("用户身份标签")
    private List<TagInfoTree> tagInfoTree;
    @ApiModelProperty("用户年龄标签")
    private List<UserAgeInfoDto> userAgeInfo;

    public List<UserAgeInfoDto> getUserAgeInfo() {
        return userAgeInfo;
    }

    public void setUserAgeInfo(List<UserAgeInfoDto> userAgeInfo) {
        this.userAgeInfo = userAgeInfo;
    }

    public List<InformationRule> getInformationrule() {
        return informationrule;
    }

    public void setInformationrule(List<InformationRule> informationrule) {
        this.informationrule = informationrule;
    }

    public List<TagInfoTree> getTagInfoTree() {
        return tagInfoTree;
    }

    public void setTagInfoTree(List<TagInfoTree> tagInfoTree) {
        this.tagInfoTree = tagInfoTree;
    }
}
