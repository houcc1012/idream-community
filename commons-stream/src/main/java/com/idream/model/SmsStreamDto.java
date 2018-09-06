package com.idream.model;

/**
 * 短信发送平台MQ传输对象
 *
 * @author hejiang
 */
public class SmsStreamDto {

    // 1-小程序验证码;2-管理后端密码
    private Byte type;

    //手机号码
    private String phone;

    //发送数据
    private Object smsSendData;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getSmsSendData() {
        return smsSendData;
    }

    public void setSmsSendData(Object smsSendData) {
        this.smsSendData = smsSendData;
    }
}
