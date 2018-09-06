package com.idream.commons.lib.dto.admin;

import com.idream.commons.lib.model.ActivityOperateRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/7/25 15:45
 * @Description:
 */
@ApiModel("活动操作记录返回参数")
public class ActivityOperateRecordResponseDto extends ActivityOperateRecord {

    @ApiModelProperty("操作人昵称")
    private String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}

