package com.idream.commons.lib.dto.amap;

import java.math.BigDecimal;
import java.util.List;

/**
 * 地址转换经纬度
 *
 * @author charles
 */
public class AmapAddress2LocationDto {

   public static AmapAddress2LocationDto emptyDto(){
        AmapAddress2LocationDto dto=new AmapAddress2LocationDto();
        dto.setInfo("ok");
        dto.setLocation("");
        dto.setCount(0);
        dto.setStatus(1);
        dto.setInfocode("10000");
        return dto;
    }
    /**
     * 状态,值为0或1,0表示失败；1表示成功
     */
    private int status;
    /**
     * 返回状态说明，status为0时，info返回错误原因，否则返回OK
     */
    private String info;
    /**
     * 返回状态说明,1000代表正确
     */
    private String infocode;
    /**
     * 集合数量
     */
    private int count;
    private String province;
    private String city;
    private String adcode;
    /**
     * 经纬度信息,例如120.102928,30.284822
     */
    private String location;

    private boolean check(){
        return status == 1 && "10000".equals(infocode) && count > 0;
    }
    public BigDecimal getLongitude() {
        String[] split = location.split(",");
        if (split.length > 0) {
            return new BigDecimal(split[0]);
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getLatitude() {
        String[] split = location.split(",");
        if (split.length > 0) {
            return new BigDecimal(split[1]);
        }
        return BigDecimal.ZERO;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvince() {

        return province;
    }

    public String getCity() {
        return city;
    }

    public String getAdcode() {
        return adcode;
    }

    public String getLocation() {
        return location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
