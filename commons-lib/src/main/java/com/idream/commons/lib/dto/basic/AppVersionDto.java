package com.idream.commons.lib.dto.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("APP版本信息返回参数")
public class AppVersionDto {

    @ApiModelProperty(value = "最新版本", required = true)
    private String version;

    @ApiModelProperty(value = "是否必须更新", required = true, allowableValues = "0-非必需更新；1-强制更新")
    private Byte force;

    @ApiModelProperty(value = "安卓版本下载地址", required = true)
    private String downUrl;

    @ApiModelProperty(value = "比较app版本与比生产版本高低  0-低；1-相同；2-高", required = true)
    private String isHighVersion;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Byte getForce() {
        return force;
    }

    public void setForce(Byte force) {
        this.force = force;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getIsHighVersion() {
        return isHighVersion;
    }

    public void setIsHighVersion(String isHighVersion) {
        this.isHighVersion = isHighVersion;
    }
}
