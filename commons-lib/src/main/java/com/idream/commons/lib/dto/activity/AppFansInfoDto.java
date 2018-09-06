package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/8.
 */
@ApiModel(value = "粉丝信息")
public class AppFansInfoDto extends AppNeighborInfoDto {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("关注时间")
    private Date createTime;

    @ApiModelProperty("是否关注彼此（否为对方没有关注本人或者本人没有关注他人，是则已互相关注）")
    private Boolean attendEachOther;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getAttendEachOther() {
        return attendEachOther;
    }

    public void setAttendEachOther(Boolean attendEachOther) {
        this.attendEachOther = attendEachOther;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
