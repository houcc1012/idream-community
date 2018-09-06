package com.idream.commons.lib.dto.amap;

import java.math.BigDecimal;

/**
 * 经纬度实体
 *
 * @author charles
 */
public class AmapLocationDto {
    private String province;
    private String city;
//    private String district;
    private String adcode;
    /**
     * 经纬度信息,例如120.102928,30.284822
     */
    private String location;

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
}
