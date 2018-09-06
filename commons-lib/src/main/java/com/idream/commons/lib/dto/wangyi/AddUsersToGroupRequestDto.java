package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/12 13:13
 * @Description:
 */
@ApiModel("往群组中添加用户请求参数")
public class AddUsersToGroupRequestDto {

    @ApiModelProperty("群组id,必填")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    @ApiModelProperty("群主账户,必填")
    @NotBlank(message = "群主账户不能为空")
    private String owner;

    @ApiModelProperty("要添加的成员,多个成员用逗号隔开,一次最多拉200个成员,必填")
    @NotBlank(message = "群成员不能为空")
    private String members;

    @ApiModelProperty("管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414,必填")
    @NotBlank(message = "magree不能为空")
    private Integer magree;

    @ApiModelProperty("邀请发送的文字，最大长度150字符,必填")
    @NotBlank(message = "邀请信息不能为空")
    @Length(max = 150, message = "邀请文字最大长度150字符")
    private String msg;

    public AddUsersToGroupRequestDto() {
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public Integer getMagree() {
        return magree;
    }

    public void setMagree(Integer magree) {
        this.magree = magree;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

