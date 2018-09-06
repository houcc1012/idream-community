package com.idream.commons.lib.dto.event.basis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("基础事件模型")
public class BasisEvent {
    public final static String ACTION_ADD = "add";
    public final static String ACTION_UPDATE = "update";
    public final static String ACTION_QUERY = "query";
    public final static String ACTION_DEL = "delete";
    @ApiModelProperty("id,为以后分区用")
    protected Integer id;
    @ApiModelProperty("类型,事件类型")
    protected String type;
    @ApiModelProperty("基础CRUD,4种操作")
    protected String action;
    @ApiModelProperty("组织id,一般代表事件操作中数据库主键")
    protected Integer organizationId;
    @ApiModelProperty("关联信息id,一般是userId")
    protected Integer correlationId;

    public BasisEvent() {
    }

    public BasisEvent(String action, Integer organizationId, Integer correlationId) {

        this(0, action, organizationId, correlationId);

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public void setCorrelationId(Integer correlationId) {
        this.correlationId = correlationId;
    }

    public BasisEvent(Integer id, String action, Integer organizationId, Integer correlationId) {
        this.id = id;
        this.type = this.getClass().getSimpleName();
        this.action = action;
        this.organizationId = organizationId;
        this.correlationId = correlationId;
    }


    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public Integer getCorrelationId() {
        return correlationId;
    }
}
