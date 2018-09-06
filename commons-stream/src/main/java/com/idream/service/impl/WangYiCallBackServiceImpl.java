package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.idream.commons.lib.dto.wangyi.WangyiIMNoticeCCParams;
import com.idream.commons.lib.mapper.WyimGroupMsgRecordMapper;
import com.idream.commons.lib.mapper.WyimUserMsgRecordMapper;
import com.idream.commons.lib.model.WyimGroupMsgRecord;
import com.idream.commons.lib.model.WyimUserMsgRecord;
import com.idream.service.WangYiCallBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hejiang
 */
@Service
public class WangYiCallBackServiceImpl implements WangYiCallBackService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private WyimUserMsgRecordMapper wyimUserMsgRecordMapper;

    @Autowired
    private WyimGroupMsgRecordMapper wyimGroupMsgRecordMapper;

    /**
     * 新增网易IM 用户消息记录
     *
     * @param params
     */
    @Override
    public void insertWangyiImUserRecord(WangyiIMNoticeCCParams params) {
        try {
            WyimUserMsgRecord record = new WyimUserMsgRecord();
            record.setFromAccount(params.getFromAccount());
            record.setSendClientType(params.getFromClientType());
            record.setMsgType(params.getMsgType());
            record.setContent(params.getBody());
            record.setAttach(params.getAttach());
            record.setSendTime(new Date(new Long(params.getMsgTimestamp())));
            record.setResendFlag("1".equals(params.getResendFlag()) ? true : false);
            record.setMsgId(params.getMsgidServer());
            record.setToAccount(params.getTo());
            record.setExt(params.getExt());
            record.setCreateTime(new Date());
            wyimUserMsgRecordMapper.insert(record);
        } catch (Exception e) {
            logger.error("存储网易用户抄送消息失败， data = " + JSON.toJSONString(params), e);
        }
    }

    /**
     * 新增网易IM 群组消息记录
     *
     * @param params
     */
    @Override
    public void insertWangyiImGroupRecord(WangyiIMNoticeCCParams params) {
        try {
            WyimGroupMsgRecord record = new WyimGroupMsgRecord();
            record.setFromAccount(params.getFromAccount());
            record.setSendClientType(params.getFromClientType());
            record.setMsgType(params.getMsgType());
            record.setContent(params.getBody());
            record.setAttach(params.getAttach());
            record.setSendTime(new Date(new Long(params.getMsgTimestamp())));
            record.setResendFlag("1".equals(params.getResendFlag()) ? true : false);
            record.setMsgId(params.getMsgidServer());
            record.setToGroupId(params.getTo());
            record.setExt(params.getExt());
            record.settMembers(params.gettMembers());
            record.setCreateTime(new Date());
            wyimGroupMsgRecordMapper.insert(record);
        } catch (Exception e) {
            logger.error("存储网易群组抄送消息失败， data = " + JSON.toJSONString(params), e);
        }
    }
}
