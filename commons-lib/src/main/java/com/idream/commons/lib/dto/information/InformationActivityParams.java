package com.idream.commons.lib.dto.information;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author charles
 */
@ApiModel("活动信息录入")
public class InformationActivityParams {
    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("录入信息")
    private List<InformationRuleParams> infos;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public List<InformationRuleParams> getInfos() {
        return infos;
    }

    public void setInfos(List<InformationRuleParams> infos) {
        this.infos = infos;
    }
}
