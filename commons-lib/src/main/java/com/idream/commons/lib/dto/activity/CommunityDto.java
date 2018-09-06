/**
 *
 */
package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>Title: CommunityDto.java</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.idream.com</p >
 *
 * @author penghekai
 * @version 1.0
 */
@ApiModel("查询社区返回参数")
public class CommunityDto {

    @ApiModelProperty(value = "id", required = true)
    private Integer id;

    @ApiModelProperty(value = "社区名称", required = true)
    private String name;

    @ApiModelProperty(value = "距离", required = true)
    private Double distance;

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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
