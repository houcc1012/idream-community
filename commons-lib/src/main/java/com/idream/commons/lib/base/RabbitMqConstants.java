package com.idream.commons.lib.base;

/**
 * 相关rabbit mq 初始化时候的一些常量配置
 *
 * @author hejiang
 */
public class RabbitMqConstants {

    //死信队列 交换机标识符
    public static final String DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

    //死信队列交换机绑定键标识符
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    // 活动服务 虚拟消息服务器
    public static final String VIRTUAL_HOST = "/";

    /*-------------------------------------------------活动服务相关---------------------------------------------------------*/

    // 邻里圈交换机
    public static final String COMMUNITY_LIFE_DIRECT_EXCHANGE = "communityLifeDirectExchange";

    // 邻里圈同步时间线队列名称
    public static final String COMMUNITY_LIFE_TIME_LINE_QUEUE = "communityLifeTimeLineQueue";

    // 邻里圈死信交换机
    public static final String DL_COMMUNITY_LIFE_DIRECT_EXCHANGE = "dl_communityLifeDirectExchange";

    // 邻里圈同步时间线死信转发队列
    public static final String DL_COMMUNITY_LIFE_TIME_LINE_QUEUE = "dl_communityLifeTimeLineQueue";

    // 邻里圈路由键
    public static final String COMMUNITY_LIFE_ROUTING_KEY = "communityLifeKey";

    /*---------------------------------------------------IM---------------------------------------------------*/

    // IM交换机
    public static final String IM_DIRECT_EXCHANGE = "imDirectExchange";

    // IM队列名称
    public static final String IM_QUEUE = "imQueue";

    // IM死信交换机
    public static final String DL_IM_DIRECT_EXCHANGE = "dl_imDirectExchange";

    // IM死信转发队列
    public static final String DL_IM_QUEUE = "dl_imQueue";

    // IM路由键
    public static final String IM_ROUTING_KEY = "imKey";

    /*---------------------------------------------------抽奖相关---------------------------------------------------*/

    // 抽奖交换机
    public static final String AWARD_DIRECT_EXCHANGE = "awardDirectExchange";

    // 抽奖队列名称
    public static final String AWARD_QUEUE = "awardQueue";

    // 抽奖死信交换机
    public static final String DL_AWARD_DIRECT_EXCHANGE = "dl_awardDirectExchange";

    // 抽奖死信转发队列
    public static final String DL_AWARD_QUEUE = "dl_awardQueue";

    // 抽奖路由键
    public static final String AWARD_ROUTING_KEY = "awardKey";

}
