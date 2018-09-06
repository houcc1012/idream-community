package com.idream.commons.lib.dto.app;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

/**
 * @author charles
 */
@ApiModel("发现页参数")
public class AppDiscoveryParams {
    @ApiModelProperty("城市编码")
    @NotBlank(message = "城市编码不能为空")
    private String cityCode;
    @ApiModelProperty("活动类型id")
    private Integer typeId;
    @ApiModelProperty(hidden = true)
    private Integer authUserId;
    @ApiModelProperty(value = "活动页数", required = true)
    private int activityPage = 1;
    @ApiModelProperty(value = "活动精彩页数", required = true)
    private int activityLifePage = 1;
    @ApiModelProperty(value = "动态页数", required = true)
    private int lifePage = 1;
    @ApiModelProperty(value = "活动每页数量", hidden = true)
    private int activitySize = 2;
    @ApiModelProperty(value = "活动精彩每页数量", hidden = true)
    private int activityLifeSize = 4;
    @ApiModelProperty(value = "动态每页数量", hidden = true)
    private int lifeSize = 3;
    @ApiModelProperty(value = "每页数量")
    private int rows=9;

    public int getActivityPage() {
        return activityPage;
    }

    public void setActivityPage(int activityPage) {
        this.activityPage = activityPage;
    }

    public int getActivityLifePage() {
        return activityLifePage;
    }

    public void setActivityLifePage(int activityLifePage) {
        this.activityLifePage = activityLifePage;
    }

    public int getLifePage() {
        return lifePage;
    }

    public void setLifePage(int lifePage) {
        this.lifePage = lifePage;
    }

    public int getActivitySize() {
        return activitySize;
    }

    public void setActivitySize(int activitySize) {
        this.activitySize = activitySize;
    }

    public int getActivityLifeSize() {
        return activityLifeSize;
    }

    public void setActivityLifeSize(int activityLifeSize) {
        this.activityLifeSize = activityLifeSize;
    }

    public int getLifeSize() {
        return lifeSize;
    }

    public void setLifeSize(int lifeSize) {
        this.lifeSize = lifeSize;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Integer authUserId) {
        this.authUserId = authUserId;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
