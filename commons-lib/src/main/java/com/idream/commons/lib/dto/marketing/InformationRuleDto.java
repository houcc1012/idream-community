package com.idream.commons.lib.dto.marketing;

import com.idream.commons.lib.dto.activity.AppUserLabelDto;
import com.idream.commons.lib.dto.appactivity.UserAgeInfoDto;
import com.idream.commons.lib.model.TagInfoTree;
import com.idream.commons.lib.model.UserAgeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ApiModel("选填信息")
public class InformationRuleDto {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "信息ID")
    @NotNull
    private Integer infoId;

    @ApiModelProperty(value = "收集信息的名称")
    @NotBlank
    private String infoName;


    @ApiModelProperty(value = "code")
    @NotBlank
    private String infoCode;


    @ApiModelProperty(value = "sort")
    private Integer infoSort;

    @ApiModelProperty(value = "标签集合")
    private List<TagInfoTree> tagInfoList;

    @ApiModelProperty(value = "年龄集合")
    private List<UserAgeInfoDto> ageList;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }


    public Integer getInfoSort() {
        return infoSort;
    }

    public void setInfoSort(Integer infoSort) {
        this.infoSort = infoSort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    public List<UserAgeInfoDto> getAgeList() {
        return ageList;
    }

    public void setAgeList(List<UserAgeInfoDto> ageList) {
        this.ageList = ageList;
    }

    public List<TagInfoTree> getTagInfoList() {
        return tagInfoList;
    }

    public void setTagInfoList(List<TagInfoTree> tagInfoList) {
        this.tagInfoList = tagInfoList;
    }
}