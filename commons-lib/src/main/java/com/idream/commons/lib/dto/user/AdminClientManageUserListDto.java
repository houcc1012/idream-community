package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("管理者列表查询")
public class AdminClientManageUserListDto {

    @ApiModelProperty(value = "主键id")
    private int id;

    @ApiModelProperty(value = "userId")
    private int userId;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "区域编码")
    private String adCode;

    @ApiModelProperty(value = "书屋名")
    private String bookHouseName;

    @ApiModelProperty(value = "累计发布活动数量")
    private int totalPublishActivityCount;

    @ApiModelProperty(value = "是否授权 1-是;0-否")
    private int managerStatus;

    public int getManagerStatus() {
        return managerStatus;
    }

    public void setManagerStatus(int managerStatus) {
        this.managerStatus = managerStatus;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getBookHouseName() {
        return bookHouseName;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }

    public int getTotalPublishActivityCount() {
        return totalPublishActivityCount;
    }

    public void setTotalPublishActivityCount(int totalPublishActivityCount) {
        this.totalPublishActivityCount = totalPublishActivityCount;
    }
}
