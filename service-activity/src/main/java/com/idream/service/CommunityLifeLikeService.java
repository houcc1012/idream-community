package com.idream.service;

import com.idream.commons.lib.dto.app.CommunityLifeLikeCountDto;
import com.idream.commons.lib.dto.app.LifeLikeResponseDto;

import java.util.List;

/**
 * Created by DELL2018-005 on 2018/5/18.
 */
public interface CommunityLifeLikeService {

    //邻里生活点赞通知(已查看)
    List<LifeLikeResponseDto> selectCommunityLifeLikeChecked(Integer authUserId);

    //邻里生活点赞通知(未查看)
    List<LifeLikeResponseDto> selectCommunityLifeLikeUnchecked(Integer authUserId);

    //邻里生活点赞通知(未查看)数量
    CommunityLifeLikeCountDto selectCountCommunityLifeLikeUnchecked(Integer authUserId);

    //清空点赞记录
    int updateEmptyLike(Integer authUserId);
}
