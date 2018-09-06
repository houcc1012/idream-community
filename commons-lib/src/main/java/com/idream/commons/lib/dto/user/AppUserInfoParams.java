package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.model.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@ApiModel("用户信息修改")
public class AppUserInfoParams {
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像地址")
    private String image;
    @ApiModelProperty("性别,男1,女2")
    private Integer gender;
    @ApiModelProperty("生日")
    private Date birthday;
    @ApiModelProperty("职业id")
    private Integer professionId;
    @ApiModelProperty("职业名")
    private String professionName;
    @ApiModelProperty("个性签名")
    private String signature;

    public UserInfo convertUserInfo(Integer userId) {
        UserInfo info = new UserInfo();
        info.setId(userId);
        if (this.gender != null) {
            info.setGender(this.gender.byteValue());
        }
        info.setNickName(StringUtils.isNotBlank(this.nickName) ? this.nickName : null);
        info.setImage(StringUtils.isNotBlank(this.image) ? this.image : null);
        info.setBirthday(this.birthday != null ? this.birthday : null);
        info.setProfessionId(this.professionId != null ? this.professionId : null);
        info.setProfessionName(StringUtils.isNotBlank(this.professionName) ? this.professionName : null);
        info.setUserInfoUpdate(new Byte("1"));
        info.setUpdateTime(new Date());
        return info;

    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNickName() {
        return nickName;
    }

    public String getImage() {
        return image;
    }

    public Integer getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public String getProfessionName() {
        return professionName;
    }

    public String getSignature() {
        return signature;
    }
}
