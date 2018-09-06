package com.idream.commons.lib.dto.activity;

import java.util.List;

public class SuggestionDto {
    List<String> keywords;
    List<String> cities;

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

}
