package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/15 15:30
 * @Description:
 */
@ApiModel("将用户添加到黑名单")
public class AddUserToBlackListRequestDto {

    @ApiModelProperty("用户账户")
    @NotBlank
    private String accid;
    @ApiModelProperty("被加黑名单用户的accid")
    @NotBlank
    private String targetAcc;
    @ApiModelProperty("本次操作的关系类型,1:黑名单操作，2:静音列表操作")
    @NotBlank
    private String relationType;
    @ApiModelProperty("操作值，0:取消黑名单或静音，1:加入黑名单或静音")
    @NotBlank
    private String value;

    public AddUserToBlackListRequestDto() {
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getTargetAcc() {
        return targetAcc;
    }

    public void setTargetAcc(String targetAcc) {
        this.targetAcc = targetAcc;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

