package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/11 09:29
 * @Description:
 */
@ApiModel("网易云用户注册参数请求封装")
public class CreateUserRequestDto {

    @ApiModelProperty("网易云用户名(相当于username),必填")
    @NotBlank(message = "accid不能为空")
    @Length(max = 30, message = "用户名超长")
    private String accid;

    @ApiModelProperty("网易云用户昵称")
    private String name;

    @ApiModelProperty("图片路径")
    private String icon;

    @ApiModelProperty("用户指定的token(不指定由网易云生成返回)")
    private String token;

    @ApiModelProperty("性别")
    private String gender;

    private Integer extUserId;

    public Integer getExtUserId() {
        return extUserId;
    }

    public void setExtUserId(Integer extUserId) {
        this.extUserId = extUserId;
    }

    public CreateUserRequestDto() {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

