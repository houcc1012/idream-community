package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 00:23
 * @Description:
 */
@ApiModel("用户参与的群组")
public class JoinTeamsResponseDto {

    @ApiModelProperty("参与的群组集合")
    private List<GroupInfos> infos;
    @ApiModelProperty("参与的群组的数量")
    private int count;

    public void setInfos(List<GroupInfos> infos) {
        this.infos = infos;
    }

    public List<GroupInfos> getInfos() {
        return infos;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }


}

