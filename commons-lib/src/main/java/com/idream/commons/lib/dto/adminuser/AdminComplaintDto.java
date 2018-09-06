package com.idream.commons.lib.dto.adminuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ApiModel("举报信息")
public class AdminComplaintDto {
    @ApiModelProperty("举报编号")
    private Integer complaintId;
    @ApiModelProperty("举报人手机号")
    private String fromPhone;
    @ApiModelProperty("被举报人手机号")
    private String toPhone;
    @ApiModelProperty("被举报人昵称")
    private String nickName;
    @ApiModelProperty("举报内容")
    private String content;
    @ApiModelProperty("举报时间")
    private Date complaintTime;
    @ApiModelProperty("举报状态")
    private Integer complaintStatus;
    @ApiModelProperty("举报图片")
    private List<String> images;
    @ApiModelProperty("群名")
    private String groupName;
    @ApiModelProperty("业务类型,1用户,2群")
    private Integer businessType;
    @ApiModelProperty("类型名称")
    private String typeName;

    private String strImages;

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getGroupName() {
        return groupName;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public void setStrImages(String strImages) {
        this.strImages = strImages;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    public void setComplaintStatus(Integer complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public String getToPhone() {
        return toPhone;
    }

    public String getContent() {
        return content;
    }

    public Date getComplaintTime() {
        return complaintTime;
    }

    public Integer getComplaintStatus() {
        return complaintStatus;
    }

    public List<String> getImages() {
        return strImages.isEmpty() ? Collections.emptyList() : Arrays.asList(strImages.split(";"));
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
