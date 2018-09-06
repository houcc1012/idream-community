package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 14:51
 * @Description:
 */
@ApiModel("更新用户信息")
public class UpdateIMUserInfoRequestDto {

    @ApiModelProperty("用户的accid")
    private String accid;

    @ApiModelProperty("昵称")
    private String name;

    @ApiModelProperty("图片路径")
    private String icon;

    @ApiModelProperty("性别")
    private String gender;


    public UpdateIMUserInfoRequestDto() {
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UpdateIMUserInfoRequestDto{" +
                "accid='" + accid + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

