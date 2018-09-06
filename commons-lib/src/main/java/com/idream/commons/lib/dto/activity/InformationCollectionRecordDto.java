package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.model.InformationCollectionRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 信息收集表
 *
 * @author idream
 *         描述： 自动生成的代码
 */
@ApiModel("信息收集")
public class InformationCollectionRecordDto extends InformationCollectionRecord {

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
