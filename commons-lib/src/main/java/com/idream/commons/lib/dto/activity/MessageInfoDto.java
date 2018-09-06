package com.idream.commons.lib.dto.activity;

import java.util.List;

public class MessageInfoDto {
    SuggestionDto SuggestionVO;
    String info;
    String status;
    String count;
    List<PoiDto> pois;

    public SuggestionDto getSuggestionVO() {
        return SuggestionVO;
    }

    public void setSuggestionVO(SuggestionDto SuggestionVO) {
        this.SuggestionVO = SuggestionVO;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<PoiDto> getPois() {
        return pois;
    }

    public void setPois(List<PoiDto> pois) {
        this.pois = pois;
    }


}
