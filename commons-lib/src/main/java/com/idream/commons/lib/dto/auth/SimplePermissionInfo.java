package com.idream.commons.lib.dto.auth;

public class SimplePermissionInfo {
    private Integer id;
    private String code;
    private String name;
    private Integer menuId;
    private Integer pmId;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public Integer getPmId() {
        return pmId;
    }
}
