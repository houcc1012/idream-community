package com.idream.commons.lib.dto.auth;

import java.util.List;

public class SimpleAuthMenu {
    private Integer id;
    private String code;
    private List<Object> children;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public List<Object> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }

    public void setName(String name) {
        this.name = name;
    }
}
