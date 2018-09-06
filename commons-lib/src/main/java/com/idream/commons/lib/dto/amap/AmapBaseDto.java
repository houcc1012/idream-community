package com.idream.commons.lib.dto.amap;

/**
 * 高德基础返回值
 *
 * @author charles
 */
public class AmapBaseDto {
    /**
     * 状态,值为0或1,0表示失败；1表示成功
     */
    protected Integer status;
    /**
     * 返回状态说明，status为0时，info返回错误原因，否则返回OK
     */
    protected String info;
    /**
     * 返回状态说明,1000代表正确
     */
    protected String infocode;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }
}
