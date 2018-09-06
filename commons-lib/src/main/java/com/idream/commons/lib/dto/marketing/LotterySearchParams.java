package com.idream.commons.lib.dto.marketing;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/13.
 */
@ApiModel(value = "开奖搜索条件")
public class LotterySearchParams extends PagesParam {

    @ApiModelProperty(value = "是否绑定活动/1是0否")
    private Boolean banded;

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "社区名")
    private String communityName;

    @ApiModelProperty(value = "区ID")
    private String adCode;

    @ApiModelProperty(value = "城市ID")
    private String cityId;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "状态/（4.所有，1.进行中，2.未开始，3.已结束）")
    @NotNull(message = "状态不能为空")
    private Integer status;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Boolean getBanded() {
        return banded;
    }

    public void setBanded(Boolean banded) {
        this.banded = banded;
    }


    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
