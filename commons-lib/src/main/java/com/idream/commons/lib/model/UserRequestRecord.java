package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserRequestRecord {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户编号")
    private Integer userId;

    @ApiModelProperty(value = "请求ip")
    private String ip;

    @ApiModelProperty(value = "设备类型:1-ios ;2-andriod; 3-微信小程序；4-web")
    private Byte deviceType;

    @ApiModelProperty(value = "app版本号")
    private String appVersion;

    @ApiModelProperty(value = "请求时间 单位毫秒")
    private Integer requestTime;

    @ApiModelProperty(value = "请求开始时间")
    private Date startTime;

    @ApiModelProperty(value = "请求方法")
    private String requestMethod;

    @ApiModelProperty(value = "请求url")
    private String requestUrl;

    @ApiModelProperty(value = "请求参数")
    private String requestParam;

    @ApiModelProperty(value = "请求完成时间")
    private Date endTime;

    @ApiModelProperty(value = "")
    private String responseData;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Byte getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Byte deviceType) {
        this.deviceType = deviceType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public Integer getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Integer requestTime) {
        this.requestTime = requestTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod == null ? null : requestMethod.trim();
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam == null ? null : requestParam.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData == null ? null : responseData.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}