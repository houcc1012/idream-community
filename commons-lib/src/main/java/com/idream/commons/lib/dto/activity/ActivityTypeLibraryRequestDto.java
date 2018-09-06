package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesParam;
import com.sun.org.apache.xpath.internal.operations.String;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/6/11 10:05
 * @Description:
 */
@ApiModel(value = "活动类型库查询请求参数")
public class ActivityTypeLibraryRequestDto extends PagesParam {

    @ApiModelProperty("活动类型名")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

