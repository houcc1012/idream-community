package com.idream.commons.lib.dto.adminuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("举报处理参数")
public class AdminComplaintStatusParams {
    @ApiModelProperty("举报id")
    private Integer complaintId;

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }
}
