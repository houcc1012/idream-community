package com.idream.commons.lib.dto.region;

import io.swagger.annotations.ApiModel;

@ApiModel("社区公告模型")
public class UnityBulletinDto {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
