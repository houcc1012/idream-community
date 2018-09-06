package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.marketing.InformationRuleDto;
import com.idream.commons.lib.dto.marketing.InformationRuleValueDto;
import com.idream.commons.lib.model.InformationRule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface InformationRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InformationRule record);

    int insertSelective(InformationRule record);

    InformationRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InformationRule record);

    int updateByPrimaryKey(InformationRule record);

    List<InformationRule> getInformationRules();

    List<InformationRule> getAwardInformationRules();

    InformationRule getEntityByActivityinfoId(Integer infoId);

    List<InformationRule> getEntityByActivityId(Integer activityId);

    //根据兑奖池Id查询需要补录的规则
    @Select("select * from information_rule where id in (" +
            "select info_id from award_information_relation where award_pool_id = #{awardId})")
    List<InformationRule> selectByIntegrationRuleByAwardId(@Param("awardId") Integer awardId);

    //根据抽奖池Id查询需要补录的规则
    @Select("select * from information_rule where id in (" +
            "select info_id from integration_information_relation where integration_pool_id = #{awardId})")
    List<InformationRule> selectByLotteryDrawRuleByAwardId(@Param("awardId") Integer awardId);

    //根据现场开奖池Id查询需要补录的规则
    @Select("select * from information_rule where id in (" +
            "select info_id from lottery_information_relation where lottery_pool_id = #{awardId})")
    List<InformationRule> selectByRunALotteryRuleByAwardId(@Param("awardId") Integer awardId);

    List<InformationRule> selectInfoListByType(@Param("typeId") byte typeId);

    List<InformationRuleDto> getInfoItemsByCouponId(@Param(value = "couponId") Integer couponId);

    List<InformationRule> getInfoRuleByActivityId(@Param(value = "activityId") Integer activityId);

    String getInfoItemsValueByUserId(@Param(value = "userId") Integer userId, @Param(value = "code") String code);
}