package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/6/30 14:56
 * @Description:
 */
@ApiModel("活动审核页面请求参数")
public class ActivityListCheckRequestDto extends PagesParam {

    @ApiModelProperty(value = "活动标题")
    private String activityTitle;
    @ApiModelProperty(value = "活动类型id")
    private Integer typeId;
    @ApiModelProperty(value = "省code")
    private String provinceCode;
    @ApiModelProperty(value = "市code")
    private String cityCode;
    @ApiModelProperty(value = "区code")
    private String adCode;
    @ApiModelProperty(value = "书屋名字")
    private String bookName;
    @ApiModelProperty(value = "审核状态 1待审核 2审核成功 3审核失败")
    private Byte checkStatus;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }
}

