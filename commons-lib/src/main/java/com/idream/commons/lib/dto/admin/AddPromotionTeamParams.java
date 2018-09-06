package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jeffery
 * @Date: 2018/8/6 14:23
 */
@ApiModel(value = "新增推广 请求参数")
public class AddPromotionTeamParams {
    @ApiModelProperty(value = "id(编辑传,新增不传)")
    private Integer id;

    @ApiModelProperty(value = "省(新增传,编辑不传)")
    @NotBlank(message = "省不能为空")
    private String province;

    @ApiModelProperty(value = "省code(新增传,编辑不传)")
    @NotBlank(message = "省不能为空")
    private String provinceCode;

    @ApiModelProperty(value = "市(新增传,编辑不传)")
    @NotBlank(message = "市不能为空")
    private String city;

    @ApiModelProperty(value = "市code(新增传,编辑不传)")
    @NotBlank(message = "市不能为空")
    private String cityCode;

    @ApiModelProperty(value = "推广名称")
    @Length(max = 10, message = "推广名称不能超过10个字符")
    private String teamName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
