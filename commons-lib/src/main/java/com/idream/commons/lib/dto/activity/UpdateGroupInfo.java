package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/23 15:49
 * @description:
 */
@ApiModel("更新群组数据库参数封装")
public class UpdateGroupInfo {


    private Integer id;

    @ApiModelProperty("群组名称")
    private String groupName;

    @ApiModelProperty("群组icon")
    private String icon;

    @ApiModelProperty("群公告")
    private String announcement;

    @ApiModelProperty("群描述")
    private String intro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}

