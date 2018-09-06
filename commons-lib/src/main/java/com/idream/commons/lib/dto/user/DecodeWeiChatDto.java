package com.idream.commons.lib.dto.user;

import org.apache.commons.lang3.StringUtils;

/**
 * @author hejiang
 */
public class DecodeWeiChatDto {
    //openId
    private String openId;
    //昵称
    private String nickName;
    //性别
    private String gender;
    //城市
    private String city;
    //省份
    private String province;
    //国家
    private String country;
    //头像url
    private String avatarUrl;
    //unionId
    private String unionId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionId() {
        return StringUtils.isEmpty(unionId) ? "TEST_UNIONID" : unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
