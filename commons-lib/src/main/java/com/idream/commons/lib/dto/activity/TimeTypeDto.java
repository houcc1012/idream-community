package com.idream.commons.lib.dto.activity;

import java.util.List;

public class TimeTypeDto {

    private List<TimeDto> timeDto;

    private Integer type;

    public List<TimeDto> getTimeDto() {
        return timeDto;
    }

    public void setTimeDto(List<TimeDto> timeDto) {
        this.timeDto = timeDto;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


}
