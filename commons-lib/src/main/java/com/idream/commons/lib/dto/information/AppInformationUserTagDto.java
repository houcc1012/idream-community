package com.idream.commons.lib.dto.information;

import com.idream.commons.lib.model.UserTag;

/**
 * @author charles
 */
public class AppInformationUserTagDto {
    private Integer id;
    private String label;
    private Integer pid;
    private Boolean active = false;

    public static AppInformationUserTagDto convertUserTag(UserTag userTag) {
        AppInformationUserTagDto a = new AppInformationUserTagDto();
        a.setId(userTag.getId());
        a.setLabel(userTag.getLabel());
        a.setPid(userTag.getPid());
        return a;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
