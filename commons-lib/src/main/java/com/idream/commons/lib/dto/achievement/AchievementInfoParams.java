package com.idream.commons.lib.dto.achievement;

import io.swagger.annotations.ApiModel;

@ApiModel("成就参数")
public class AchievementInfoParams {
    private Byte category;
    private Byte kind;

    public void setCategory(Byte category) {
        this.category = category;
    }

    public void setKind(Byte kind) {
        this.kind = kind;
    }

    public Byte getCategory() {
        return category;
    }

    public Byte getKind() {
        return kind;
    }
}
