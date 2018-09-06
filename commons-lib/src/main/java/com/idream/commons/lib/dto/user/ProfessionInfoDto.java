package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * <p>Title: ProfessionInfoDto.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: www.idream.com</p>
 *
 * @author huayuliang
 * @version 1.0
 */
@ApiModel("查询职业信息返回参数")
public class ProfessionInfoDto {

    @ApiModelProperty(value = "主键ID", required = true)
    private Integer id;

    //职业名称
    @ApiModelProperty(value = "职业名称", required = true)
    private String name;

    //默认0,父级id
    @ApiModelProperty(value = "父级id", required = true)
    private Integer pid;

    @ApiModelProperty(value = "子职业信息", required = true)
    private List<ProfessionInfoDto> childProfessionInfos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<ProfessionInfoDto> getChildProfessionInfos() {
        return childProfessionInfos;
    }

    public void setChildProfessionInfos(List<ProfessionInfoDto> childProfessionInfos) {
        this.childProfessionInfos = childProfessionInfos;
    }
}
