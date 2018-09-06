package com.idream.service;

/**
 * 用户积分相关
 *
 * @author hejiang
 */
public interface UserScoreService {

    /**
     * 新增用户积分和免费抽奖次数
     *
     * @param score       积分
     * @param freeLottery 抽奖次数
     * @param userId      用户ID
     */
    int updateUserScoreAndFreeLottery(Integer score, Integer freeLottery, Integer userId);

    /**
     * 新增用户积分记录
     *
     * @param score
     * @param fromType
     * @param recordType
     * @param businessId
     * @param userId
     */
    int addUserScoreRecord(Integer score, Byte fromType, Byte recordType, Integer businessId, Integer userId);

    /**
     * 新增用户积分记录
     *
     * @param score      积分
     * @param fromType   来源渠道 1签到,2打卡,3抽奖,4兑换
     * @param recordType 记录类型 1获取,2使用
     * @param userId     用户编号
     */
    int addUserScoreRecord(Integer score, Byte fromType, Byte recordType, Integer userId);

    /**
     * 修改用户积分和记录用户积分使用记录
     *
     * @param score      积分 无论扣除新增都是正数
     * @param fromType   来源 1签到,2打卡,3抽奖,4兑换
     * @param recordType 记录类型 1-获取;2-使用
     * @param userId     用户ID
     */
    void updateUserScoreAndRecord(Integer score, Byte fromType, Byte recordType, Integer userId);

    /**
     * 修改用户积分和记录用户积分使用记录
     *
     * @param score       积分 无论扣除新增都是正数
     * @param fromType    来源 1签到,2打卡,3抽奖,4兑换
     * @param recordType  记录类型 1-获取;2-使用
     * @param freeLottery 免费抽奖次数
     * @param authUserId  用户ID
     */
    void updateUserScoreAndRecord(Integer score, Byte fromType, Byte recordType, Integer freeLottery, Integer authUserId);

    /**
     * 修改用户积分和记录用户积分使用记录
     *
     * @param score       积分 无论扣除新增都是正数
     * @param fromType    来源 1签到,2打卡,3抽奖,4兑换
     * @param recordType  记录类型 1-获取;2-使用
     * @param freeLottery 免费抽奖次数
     * @param businessId  业务ID
     * @param authUserId  用户ID
     */
    void updateUserScoreAndRecord(Integer score, Byte fromType, Byte recordType, Integer freeLottery, Integer businessId, Integer authUserId);
}
