package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 09:28
 * @Description:
 */
@ApiModel("修改群组内用户的昵称")
public class UpdateGroupUserNickNameRequestDto {

    @ApiModelProperty("群组id. 必填")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    @ApiModelProperty("群主accid. 必填")
    @NotBlank(message = "群主accid不能为空")
    private String owner;

    @ApiModelProperty("要修改昵称的群成员的accid. 必填")
    @NotBlank(message = "成员accid不能为空")
    private String accid;

    @ApiModelProperty("昵称. 必填")
    @NotBlank(message = "昵称不能为空")
    private String nick;

    public UpdateGroupUserNickNameRequestDto() {
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

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}

