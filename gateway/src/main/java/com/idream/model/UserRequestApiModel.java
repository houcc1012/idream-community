package com.idream.model;

/**
 * @author hejiang
 */
public class UserRequestApiModel {

    private String urlParam;

    private String bodyParam;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrlParam() {
        return urlParam;
    }

    public void setUrlParam(String urlParam) {
        this.urlParam = urlParam;
    }

    public String getBodyParam() {
        return bodyParam;
    }

    public void setBodyParam(String bodyParam) {
        this.bodyParam = bodyParam;
    }
}
