package com.idream.commons.lib.dto.adminuser;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles
 */
@ApiModel("用户投诉查询参数")
public class AdminComplaintParams extends PagesParam {
    @ApiModelProperty("关键字")
    private String keyWord;
    @ApiModelProperty("举报状态,1等待,2成功,3失败,空字符表示全部")
    private Integer complaintStatus;
    @ApiModelProperty("业务类型,1用户,2群")
    private Integer businessType;

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setComplaintStatus(Integer complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getComplaintStatus() {
        return complaintStatus;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
