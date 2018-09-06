/**
 *
 */
package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaogang
 */
@ApiModel("数据统计")
public class CountTaskDto {

    @ApiModelProperty(value = "参加人数")
    private Integer attendPeople;

    @ApiModelProperty(value = "打卡人数")
    private Integer taskCount;

    @ApiModelProperty(value = "累计打卡人数")
    private Integer allTaskCount;

    /**
     * @return the attendPeople
     */

    public Integer getAttendPeople() {
        return attendPeople;
    }

    /**
     * @param attendPeople the attendPeople to set
     */
    public void setAttendPeople(Integer attendPeople) {
        this.attendPeople = attendPeople;
    }

    /**
     * @return the taskCount
     */
    public Integer getTaskCount() {
        return taskCount;
    }

    /**
     * @param taskCount the taskCount to set
     */
    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    /**
     * @return the allTaskCount
     */
    public Integer getAllTaskCount() {
        return allTaskCount;
    }

    /**
     * @param allTaskCount the allTaskCount to set
     */
    public void setAllTaskCount(Integer allTaskCount) {
        this.allTaskCount = allTaskCount;
    }


}
