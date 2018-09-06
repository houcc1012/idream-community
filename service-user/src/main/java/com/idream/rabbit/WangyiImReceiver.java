package com.idream.rabbit;

import com.alibaba.fastjson.JSON;
import com.idream.commons.lib.base.RabbitMqConstants;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.activity.UpdateGroupInfoRequestParam;
import com.idream.commons.lib.dto.mail.SendMailParams;
import com.idream.commons.lib.dto.rabbitmq.OperateWangyiIMInfoDto;
import com.idream.commons.lib.dto.wangyi.*;
import com.idream.commons.lib.enums.WangYiEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.feign.FeignSendMailService;
import com.idream.service.UserIMGroupService;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.ULongLongSeqHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author hejiang
 */
@Component
public class WangyiImReceiver {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private UserIMGroupService userIMGroupService;

    @Autowired
    private FeignSendMailService feignSendMailService;

    @RabbitListener(queues = RabbitMqConstants.IM_QUEUE, containerFactory = "wangyiImListenContainer")
    public void imHandler(@Payload String msg,
                          @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                          Channel channel) throws IOException {
        logger.info("接收到来自" + RabbitMqConstants.IM_QUEUE + "队列的消息, data=" + msg);
        try {
            if (StringUtils.isNotEmpty(msg)) {
                boolean resultFlad = handlerWangyiImInfo(msg);
                if (resultFlad) {
                    channel.basicAck(deliveryTag, true);
                    return;
                }
            }
            channel.basicReject(deliveryTag, false);
        } catch (Exception e) {
            logger.error("处理失败, msg = " + msg, e);
            channel.basicReject(deliveryTag, false);
        }
    }

    @RabbitListener(queues = RabbitMqConstants.DL_IM_QUEUE)
    public void dlImHandler(@Payload String msg,
                            @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                            Channel channel) throws IOException {
        logger.info("接收到来自" + RabbitMqConstants.DL_IM_QUEUE + "队列的消息, data=" + msg);
        try {
            boolean resultFlad = handlerWangyiImInfo(msg);
            if (!resultFlad) {
                throw new BusinessException(RetCodeConstants.UNKOWN_ERROR);
            }
        } catch (Exception e) {
            logger.error("处理网易群组相关信息失败, msg = " + msg, e);
            //处理失败, 发送邮件
            SendMailParams sendMailParams = new SendMailParams();
            sendMailParams.setSubject("处理网易群组相关信息失败");
            sendMailParams.setText("MQ处理消息失败信息通知:" + msg);
            sendMailParams.setToMailUser("742664726@qq.com");
            sendMailParams.setMailAttachments(null);
            feignSendMailService.sendSimpleMail(sendMailParams);
        } finally {
            channel.basicAck(deliveryTag, true);
        }
    }

    private boolean handlerWangyiImInfo(String msg) throws IOException {
        OperateWangyiIMInfoDto data = JSON.parseObject(msg, OperateWangyiIMInfoDto.class);
        //建群
        if (WangYiEnum.OperateWangyiIMType.CTREATE_GROUP.getType().equals(data.getType())) {
            CreateGroupRequestParam param = JSON.parseObject(JSON.toJSONString(data.getObj()), CreateGroupRequestParam.class);
            CreateGroupResponseDto createGroupResponseDto = userIMGroupService.createIMGroup(param);
            if (createGroupResponseDto != null && StringUtils.isNotEmpty(createGroupResponseDto.getTid())) {
                logger.info("建群成功, tid = " + createGroupResponseDto.getTid());
                return true;
            }
        } else if (WangYiEnum.OperateWangyiIMType.QUIT_GROUP.getType().equals(data.getType())) {
            LeaveGroupRequestParam param = JSON.parseObject(JSON.toJSONString(data.getObj()), LeaveGroupRequestParam.class);
            //退群
            int result = userIMGroupService.doLeaveGroup(param);
            if (result > 0) {
                return true;
            }
        } else if (WangYiEnum.OperateWangyiIMType.ADD_GROUP_USER.getType().equals(data.getType())) {
            //拉人进群
            JoinGroupRequestParam param = JSON.parseObject(JSON.toJSONString(data.getObj()), JoinGroupRequestParam.class);
            int result = userIMGroupService.doJoinGroup(param);
            if (result > 0) {
                return true;
            }
        } else if (WangYiEnum.OperateWangyiIMType.UPDATE_GROUP.getType().equals(data.getType())) {
            UpdateGroupInfoRequestParam param = JSON.parseObject(JSON.toJSONString(data.getObj()), UpdateGroupInfoRequestParam.class);
            int result = userIMGroupService.updateGroupInfo(param);
            if (result > 0) {
                return true;
            }
        }else if (WangYiEnum.OperateWangyiIMType.DELETE_IM_GROUP.getType().equals(data.getType())) {
            DeleteGroupRequestParam deleteGroupRequestParam = JSON.parseObject(JSON.toJSONString(data.getObj()), DeleteGroupRequestParam.class);
            WangYiCommonResponseDto wangYiCommonResponseDto = userIMGroupService.deleteGroup(deleteGroupRequestParam);
            if (wangYiCommonResponseDto != null) {
                return true;
            }
        }
//        else if(WangYiEnum.OperateWangyiIMType.ADD_FRIEND.getType().equals(data.getType())){
//            //加好友
//            AddFriendRequestParams param = JSON.parseObject(JSON.toJSONString(data.getObj()), AddFriendRequestParams.class);
//            int result = userIMService.addIMFriend(param);
//            if(result > 0){
//                return true;
//            }
//        }else if(WangYiEnum.OperateWangyiIMType.DELETE_FRIEND.getType().equals(data.getType())){
//            //删好友
//            DeleteFriendRequestParam param = JSON.parseObject(JSON.toJSONString(data.getObj()), DeleteFriendRequestParam.class);
//            int result = userIMService.deleteFriend(param);
//            if(result > 0){
//                return true;
//            }
//        }
        return false;
    }
}
