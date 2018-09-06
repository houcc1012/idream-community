package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.model.ProfessionInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "职业信息")
public class UserProfessionDto {
    @ApiModelProperty("职业id")
    private Integer professionId;
    @ApiModelProperty("职业名称")
    private String professionName;

    public static UserProfessionDto convertDto(ProfessionInfo info) {
        UserProfessionDto dto = new UserProfessionDto();
        dto.professionId = info.getId();
        dto.professionName = info.getName();
        return dto;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public String getProfessionName() {
        return professionName;
    }
}
