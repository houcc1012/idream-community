package com.idream.commons.lib.dto.information;

import com.idream.commons.lib.model.UserTag;

import java.util.List;

/**
 * @author charles
 */
public class InformationUserTagDto {
    private Integer id;
    private String label;
    private Integer pid;
    private Boolean active = false;
    private List<InformationUserTagDto> children;

    public static InformationUserTagDto convertUserTag(UserTag userTag) {
        InformationUserTagDto a = new InformationUserTagDto();
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

    public List<InformationUserTagDto> getChildren() {
        return children;
    }

    public void setChildren(List<InformationUserTagDto> children) {
        this.children = children;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
