package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("积分抽奖请求参数")
public class IntegrationDrawParams {

    @ApiModelProperty(value = "抽奖类型 1-积分抽奖；2-免费抽奖")
    private Byte integrationDrawType;

    @ApiModelProperty(value = "抽奖奖池Id,-1代表平台奖池")
    private Integer bookId;

    public Byte getIntegrationDrawType() {
        return integrationDrawType;
    }

    public void setIntegrationDrawType(Byte integrationDrawType) {
        this.integrationDrawType = integrationDrawType;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
