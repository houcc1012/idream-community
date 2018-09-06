package com.idream.commons.lib.dto.app;

import com.idream.commons.lib.dto.activity.AppUserAchievementDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author charles
 */
@ApiModel("用户成就分类显示")
public class AppUserAchievementCategoryDto {
    @ApiModelProperty(hidden = true)
    private Integer categoryId;
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("成就列表")
    private List<AppUserAchievementDto> achievements;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<AppUserAchievementDto> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<AppUserAchievementDto> achievements) {
        this.achievements = achievements;
    }


}
