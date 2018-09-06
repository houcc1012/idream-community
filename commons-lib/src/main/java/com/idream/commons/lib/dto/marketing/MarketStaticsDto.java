package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询营销相关数据返回")
public class MarketStaticsDto {
    @ApiModelProperty(value = "请求类型")
    private int type;
    @ApiModelProperty(value = "人数")
    private int userCount;
    @ApiModelProperty(value = "次数")
    private int marketCount;
    @ApiModelProperty(value = "日期")
    private String date;
    @ApiModelProperty(value = "累计人数")
    private int totalUserCount;

    public int getTotalUserCount() {
        return totalUserCount;
    }

    public void setTotalUserCount(int totalUserCount) {
        this.totalUserCount = totalUserCount;
    }

    public int getTotalMarketCount() {
        return totalMarketCount;
    }

    public void setTotalMarketCount(int totalMarketCount) {
        this.totalMarketCount = totalMarketCount;
    }

    @ApiModelProperty(value = "累计次数")
    private int totalMarketCount;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getMarketcount() {
        return marketCount;
    }

    public void setMarketcount(int marketCount) {
        this.marketCount = marketCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
