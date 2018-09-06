package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DELL2018-005 on 2018/5/18.
 */
@ApiModel("维护点赞记录返回参数")
public class EgisLikeRecordResponseDto {

    @ApiModelProperty(value = "维护点赞记录id")
    private Integer likeRecordId;

    @ApiModelProperty(value = "清空id")
    private Integer emptyId;

    public Integer getLikeRecordId() {
        return likeRecordId;
    }

    public void setLikeRecordId(Integer likeRecordId) {
        this.likeRecordId = likeRecordId;
    }

    public Integer getEmptyId() {
        return emptyId;
    }

    public void setEmptyId(Integer emptyId) {
        this.emptyId = emptyId;
    }
}
