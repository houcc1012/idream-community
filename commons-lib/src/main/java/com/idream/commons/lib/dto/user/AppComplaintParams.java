package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel("投诉参数保存")
public class AppComplaintParams {
    @ApiModelProperty("内容")
    @NotBlank
    private String content;
    @ApiModelProperty("被举报id:用户id,群id")
    @NotNull
    private Integer businessId;
    @ApiModelProperty("业务类型,1用户,2群")
    @NotNull
    @Min(value = 1, message = "业务类型大于0,1用户,2群")
    @Max(value = 2, message = "业务类型小于3,1用户,2群")
    private Integer businessType;
    @ApiModelProperty("举报类型")
    @NotNull
    private Integer typeId;
    @ApiModelProperty("图片")
    private List<String> images;

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public Integer getBusinessType() {
        return businessType;
    }
}
