package com.idream.commons.lib.dto.excel;

public class ExcelUserDto {
    private String nickName;
    private String image;
    private String strGender;
    private String city;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStrGender() {
        return strGender;
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
    }

    public String getCity() {
        return city.endsWith("市") ? city : city + "市";
    }

    public void setCity(String city) {
        this.city = city;
    }
}
