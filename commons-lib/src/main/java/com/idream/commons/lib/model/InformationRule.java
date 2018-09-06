package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModelProperty;

public class InformationRule {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "收集信息的code")
    private String code;

    @ApiModelProperty(value = "收集信息的名称")
    private String name;

    @ApiModelProperty(value = "收集类型,待定1-用户；2-奖品")
    private Byte type;

    @ApiModelProperty(value = "标签排序")
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}