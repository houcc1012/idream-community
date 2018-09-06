package com.idream.commons.lib.dto.amap;

import java.util.Arrays;

public class AmapLocation2AddressDto extends AmapBaseDto {
   private static String[] MUNICIPALITY = {"上海市", "北京市", "天津市", "重庆市"};

    public static AmapLocation2AddressDto emptyDto() {
        AmapLocation2AddressDto dto=new AmapLocation2AddressDto();
        dto.setCity("");
        dto.setProvince("");
        dto.setCountry("");
        return dto;
    }
    private String country;
    private String province;
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        int i = Arrays.binarySearch(MUNICIPALITY, province);
        if (i>=0) {
            city=province;
        }

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
