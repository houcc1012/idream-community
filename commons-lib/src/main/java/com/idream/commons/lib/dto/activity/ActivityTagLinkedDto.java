package com.idream.commons.lib.dto.activity;

import java.util.List;

import com.idream.commons.lib.model.ActivityTag;

public class ActivityTagLinkedDto {

    private List<ActivityTag> allTags;
    private List<Integer> selectedTags;

    public List<ActivityTag> getAllTags() {
        return allTags;
    }

    public void setAllTags(List<ActivityTag> allTags) {
        this.allTags = allTags;
    }

    public List<Integer> getSelectedTags() {
        return selectedTags;
    }

    public void setSelectedTags(List<Integer> selectedTags) {
        this.selectedTags = selectedTags;
    }

}
