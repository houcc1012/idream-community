package com.idream.channel.handle;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.idream.model.SmsStreamDto;
import com.idream.rabbit.SmsProcessor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 监听mq,消费消息
 *
 * @author charles.wei
 */
@Component
@EnableBinding({SmsProcessor.class})
public class SmsHandler {

    @Value("${spring.cloud.config.profile}")
    private String environment;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass().getName());
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    static final String accessKeyId = "LTAIUQ3LpWzuhKSf";
    static final String accessKeySecret = "CT2JkVycA492JxHdcaym2ZzWLvI0Hz";
    static final String SMS_SIGN = "风和邻里";

    /**
     * 方法处理消息
     */
    @StreamListener(SmsProcessor.SMS_INPUT)
    @SendTo(SmsProcessor.SMS_OUTPUT)
    public String hanle(Message<SmsStreamDto> message) {
        SmsStreamDto params = message.getPayload();
        logger.info("处理短信发送:" + JSON.toJSONString(params));
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(params.getPhone());
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SMS_SIGN);

        //根据业务不同处理短信模板
        String sendData = (String) params.getSmsSendData();
        if (params.getType() == 1) {//验证码
            request.setTemplateCode("SMS_133020050");
            request.setTemplateParam("{\"code\":\"" + sendData + "\"}");
        } else if (params.getType() == 2) {//管理端密码
            request.setTemplateCode("SMS_133005274");
            request.setTemplateParam("{\"password\":\"" + sendData + "\"}");
        }
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        try {
            // 测试环境不发送验证码
            if (!"test".equals(environment) && !"dev".equals(environment)) {
                SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
                logger.info("发送返回：" + sendSmsResponse.getCode());
                return sendSmsResponse.getCode();
            }
        } catch (ClientException e) {
            logger.error("发送短信失败", e);
        }
        return "fail";
    }
}
