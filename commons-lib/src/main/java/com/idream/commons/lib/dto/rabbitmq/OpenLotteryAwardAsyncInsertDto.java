package com.idream.commons.lib.dto.rabbitmq;

/**
 * @author hejiang
 */
public class OpenLotteryAwardAsyncInsertDto {
    // 奖池奖品ID
    private int lotteryAwardId;

    // 用户ID
    private int userId;

    // 奖品缓存Key
    private String redisKey;

    public OpenLotteryAwardAsyncInsertDto() {
    }

    public OpenLotteryAwardAsyncInsertDto(int lotteryAwardId, int userId, String redisKey) {
        this.lotteryAwardId = lotteryAwardId;
        this.userId = userId;
        this.redisKey = redisKey;
    }

    public int getLotteryAwardId() {
        return lotteryAwardId;
    }

    public void setLotteryAwardId(int lotteryAwardId) {
        this.lotteryAwardId = lotteryAwardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }
}
