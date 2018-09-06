package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author penghekai
 */
@ApiModel(value = "查看报名明细返回参数")
public class FindRegisterResponseDto {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty("姓名")
    private String userName;
    @ApiModelProperty("年龄")
    private String age;
    @ApiModelProperty("标签")
    private String tag;
    @ApiModelProperty("身份证号")
    private String identity;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "详细地址")
    private String address;
    @ApiModelProperty(value = "小孩数量")
    private Integer childNum;
    @ApiModelProperty(value = "报名时间")
    private Date time;
    @ApiModelProperty(value = "自定义项")
    private List<ActivityAdminCustomerInfoDto> customInfoList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    public List<ActivityAdminCustomerInfoDto> getCustomInfoList() {
        return customInfoList;
    }

    public void setCustomInfoList(List<ActivityAdminCustomerInfoDto> customInfoList) {
        this.customInfoList = customInfoList;
    }
}
