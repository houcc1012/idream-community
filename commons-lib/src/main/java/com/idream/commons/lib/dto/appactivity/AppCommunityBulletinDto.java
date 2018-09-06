package com.idream.commons.lib.dto.appactivity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles
 */
@ApiModel("社区公告实体")
public class AppCommunityBulletinDto {
    @ApiModelProperty("公告信息")
    private String bulletin;

    public String getBulletin() {
        return bulletin;
    }

    public void setBulletin(String bulletin) {
        this.bulletin = bulletin;
    }
}
