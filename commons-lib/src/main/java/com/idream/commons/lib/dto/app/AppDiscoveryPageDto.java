package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;
import java.util.List;

public class AppDiscoveryPageDto<T> {
    @ApiModelProperty(value = "活动页数", required = true)
    private int activityPage = 1;
    @ApiModelProperty(value = "活动精彩页数", required = true)
    private int activityLifePage = 1;
    @ApiModelProperty(value = "动态页数", required = true)
    private int lifePage = 1;

    @ApiModelProperty(value = "分页列表", required = true)
    private Collection<T> rows;

    public AppDiscoveryPageDto(Collection<T> rows, int activityPage, int activityLifePage, int lifePage) {
        this.activityPage = activityPage;
        this.activityLifePage = activityLifePage;
        this.lifePage = lifePage;
        this.rows = rows;
    }

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

    public Collection<T> getRows() {
        return rows;
    }

    public void setRows(Collection<T> rows) {
        this.rows = rows;
    }
}
