package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 管理端  社区书屋请求参数
 *
 * @param
 *
 * @author zhanfeifei
 */

@ApiModel(value = "社区书屋请求参数")
public class CommunityBookHouseRequestDto extends PagesParam {

    @ApiModelProperty(value = "社区名称")
    private String communityName;

    @ApiModelProperty(value = "是否有社区书屋  true:false:null 有|无 |全部")
    //有 | 无
    private Boolean isHaving;

    @ApiModelProperty(value = "省,市,区")
    private String adCode;

    @ApiModelProperty(value = "书屋名称")
    private String bookHouseName;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Boolean getIsHaving() {
        return isHaving;
    }

    public void setIsHaving(Boolean isHaving) {
        this.isHaving = isHaving;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }


    public String getBookHouseName() {
        return bookHouseName;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }


}
