package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("管理者列表查询请求参数")
public class AdminClientManageUserListParams extends PagesParam {

    @ApiModelProperty(value = "手机号", required = false)
    private String phone;

    @ApiModelProperty(value = "省编码", required = false)
    private String province;

    @ApiModelProperty(value = "城市编码", required = false)
    private String city;

    @ApiModelProperty(value = "地区编码", required = false)
    private String district;

    @ApiModelProperty(value = "书屋名称", required = false)
    private String bookHouseName;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBookHouseName() {
        return bookHouseName;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }
}
