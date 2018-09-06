package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "最新五条动态列表查询结果")
public class AppNewCommunityLifeDto extends AppNeighborInfoDto {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "时间显示(大于30分钟显示“最近发布了动态“，小于则显示多少分钟以前)")
    private String showTime;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "图片")
    private List<AppImageParam> imageList;

    @ApiModelProperty(value = "其他动态")
    private List<AppNewLifeDetailDto> otherLifeList;

    public List<AppImageParam> getImageList() {
        return imageList;
    }

    public void setImageList(List<AppImageParam> imageList) {
        this.imageList = imageList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public List<AppNewLifeDetailDto> getOtherLifeList() {
        return otherLifeList;
    }

    public void setOtherLifeList(List<AppNewLifeDetailDto> otherLifeList) {
        this.otherLifeList = otherLifeList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
