package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/8/6 13:47
 */
@ApiModel(value = "其他推广 返回参数")
public class OtherPromotionDto {
    @ApiModelProperty(value = "推广id")
    private Integer id;

    @ApiModelProperty(value = "推广名称")
    private String name;

    @ApiModelProperty(value = "推广码")
    private String code;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "今日新增")
    private Integer nowAddCount;

    @ApiModelProperty(value = "累计扫码注册")
    private Integer registerCount;

    @ApiModelProperty(value = "推广二维码")
    private String qrCode;

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNowAddCount() {
        return nowAddCount;
    }

    public void setNowAddCount(Integer nowAddCount) {
        this.nowAddCount = nowAddCount;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }
}
