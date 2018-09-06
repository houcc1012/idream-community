package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动信息表
 */
@ApiModel("活动信息表")
public class ActivityInfoDto {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 副标题
     */
    @ApiModelProperty(value = "副标题")
    private String subtitle;

    /**
     * 展示图片
     */
    @ApiModelProperty(value = "展示图片")
    private String image;

    /**
     * 展示的内容
     */
    @ApiModelProperty(value = "展示的内容")
    private String content;

    /**
     * 1草稿,2创建完成,3发布,4审核通过,5审核不通过,0取消活动
     */
    @ApiModelProperty(value = "1草稿,2创建完成,3发布,4审核通过,5审核不通过,0取消活动")
    private Integer status;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 发起人
     */
    @ApiModelProperty(value = "发起人")
    private Integer userId;

    /**
     * 活动类别id
     */
    @ApiModelProperty(value = "活动类别id")
    private Integer typeId;

    /**
     * 城市编码
     */
    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    private String city;

    /**
     * 参加人数,默认0,不限制
     */
    @ApiModelProperty(value = "参加人数,默认0,不限制")
    private Integer count;

    /**
     * 活动类型,1官方
     */
    @ApiModelProperty(value = "活动类型,1官方")
    private Integer type;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}