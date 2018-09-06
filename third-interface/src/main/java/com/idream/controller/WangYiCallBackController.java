package com.idream.controller;

import com.idream.rabbit.WangyiIMCallBackSendService;
import com.idream.utils.wangyi.CheckSumBuilder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 网易IM消息抄送
 *
 * @author hejiang
 */
@RestController
@ApiIgnore
public class WangYiCallBackController {

    private Logger logger = LoggerFactory.getLogger(WangYiCallBackController.class);

    @Resource
    private WangyiIMCallBackSendService wangyiIMCallBackSendService;

    @Value("${wangyi_secret}")
    private String wangyiSecret;

    //网易用户注册
    @RequestMapping(value = "/wangyi/notice/callback", method = RequestMethod.POST)
    public void createIMUser(HttpServletRequest request) {
        try {
            // 获取请求体
            byte[] body = readBody(request);
            if (body == null) {
                logger.warn("网易消息抄送内容为空！");
                return;
            }
            // 获取部分request header，并打印
            String CurTime = request.getHeader("CurTime");
            String MD5 = request.getHeader("MD5");
            String CheckSum = request.getHeader("CheckSum");
            logger.info("request headers:  CurTime = {}, MD5 = {}, CheckSum = {}", CurTime, MD5, CheckSum);
            // 将请求体转成String格式，并打印
            String requestBody = new String(body, "utf-8");
            logger.info("request body = {}", requestBody);
            // 获取计算过的md5及checkSum
            String verifyMD5 = CheckSumBuilder.getMD5(requestBody);
            String verifyChecksum = CheckSumBuilder.getCheckSum(wangyiSecret, verifyMD5, CurTime);
            logger.debug("verifyMD5 = {}, verifyChecksum = {}", verifyMD5, verifyChecksum);
            // TODO: 比较md5、checkSum是否一致，以及后续业务处理
            if (verifyMD5.equals(MD5) && verifyChecksum.equals(CheckSum)) {
                wangyiIMCallBackSendService.sendImNoticeCallbackMessage(requestBody);
                logger.info("网易消息发送完毕！");
            } else {
                logger.warn("网易消息抄送校验失败！");
            }
        } catch (Exception e) {
            logger.error("网易消息抄送处理失败！", e);
        }
    }

    private byte[] readBody(HttpServletRequest request) throws IOException {
        if (request.getContentLength() > 0) {
            byte[] body = new byte[request.getContentLength()];
            IOUtils.readFully(request.getInputStream(), body);
            return body;
        }
        return null;
    }
}
