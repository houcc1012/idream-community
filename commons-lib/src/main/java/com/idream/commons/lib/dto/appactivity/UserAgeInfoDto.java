package com.idream.commons.lib.dto.appactivity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户年龄标签")
public class UserAgeInfoDto {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String value;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "默认1")
    private Byte type;

    @ApiModelProperty(value = "排序")
    private Integer sorted;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getSorted() {
        return sorted;
    }

    public void setSorted(Integer sorted) {
        this.sorted = sorted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
