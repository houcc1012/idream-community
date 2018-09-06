package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/15 13:05
 * @Description:
 */
@ApiModel("黑名单列表")
public class QueryBlackListResponseParams {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户的accid")
    private String accid;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("头像")
    private String icon;

    @ApiModelProperty("性别")
    private String gender;

    public QueryBlackListResponseParams() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
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
}

