package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("修改用户信息请求参数")
public class UpdateUserInfoParams {

    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickName;

    @ApiModelProperty(value = "生日 格式 yyyy-MM-dd", required = true)
    private String birthday;

    @ApiModelProperty(value = "性别: 1男2女", required = true)
    private Byte gender;

    @ApiModelProperty(value = "职业Id", required = true)
    private int professionId;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public int getProfessionId() {
        return professionId;
    }

    public void setProfessionId(int professionId) {
        this.professionId = professionId;
    }
}
