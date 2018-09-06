package com.idream.service;

import com.idream.commons.lib.dto.app.LotteryListResponseDto;

import java.util.List;

public interface LotteryListService {

    //现场开奖 有奖活动 app端
    List<LotteryListResponseDto> selectLotteryList(Integer regionId);
}
