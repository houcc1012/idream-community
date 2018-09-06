package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author charles
 */
@ApiModel("高德IP地址信息")
public class AmapIPLocationDto {
    private final static int SUCCESS_CODE=10000;
    @ApiModelProperty("状态,值为0或1,0表示失败；1表示成功")
    private Integer status;
    @ApiModelProperty("返回状态说明，status为0时，info返回错误原因，否则返回OK")
    private String info;
    @ApiModelProperty("返回状态说明,10000代表正确")
    private Integer infocode;
    @ApiModelProperty("若为直辖市则显示直辖市名称;如果在局域网 IP网段内，则返回'局域网',非法IP以及国外IP则返回空")
    private String province;
    @ApiModelProperty("若为直辖市则显示直辖市名称;如果在局域网 IP网段内,非法IP以及国外IP则返回空")
    private String city;
    @ApiModelProperty("地区编码")
    private String adcode;
    @ApiModelProperty("城市范围的左下右上对标对,116.0119343,39.66127144;116.7829835,40.2164962")
    private String rectangle;
    @ApiModelProperty("是否是局域网")
    private boolean locationNetwork = false;
    @ApiModelProperty("正常返回的数据是否有效")
    private boolean vaild = false;

    public boolean success(){
        return check();
    }
    private boolean check(){
        return status == 1 && infocode.equals(SUCCESS_CODE) && vaild && !locationNetwork;
    }

    public BigDecimal getLongitude() {
        if (check()) {
            String[] split = rectangle.split(";");
            if (split.length > 1) {
                String[] f = split[0].split(",");
                String[] s = split[1].split(",");
                BigDecimal a = new BigDecimal(f[0]);
                BigDecimal b = new BigDecimal(s[0]);
                return (a.add(b).divide(BigDecimal.valueOf(2), 7, BigDecimal.ROUND_FLOOR));
            }
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getLatitude() {
        if (check()) {
            String[] split = rectangle.split(";");
            if (split.length > 1) {
                String[] f = split[0].split(",");
                String[] s = split[1].split(",");
                BigDecimal a = new BigDecimal(f[1]);
                BigDecimal b = new BigDecimal(s[1]);
                return (a.add(b).divide(BigDecimal.valueOf(2), 7, BigDecimal.ROUND_FLOOR));
            }
        }
        return BigDecimal.ZERO;
    }
    public String getCityCode(){
        if (check()) {
            return adcode.replaceAll("00\\d{2}", "0100").substring(0,4)+"00";
        }
        return "";
    }
    public String getProvinceCode(){
        if (check()) {
            return adcode.substring(0,2)+"0000";
        }
        return "";
    }

    public void setVaild(boolean vaild) {
        this.vaild = vaild;
    }

    public boolean isLocationNetwork() {
        return locationNetwork;
    }

    public void setLocationNetwork(boolean locationNetwork) {
        this.locationNetwork = locationNetwork;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setInfocode(Integer infocode) {
        this.infocode = infocode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public void setRectangle(String rectangle) {
        this.rectangle = rectangle;
    }

    public String getProvince() {
        return province.replaceAll("市","");
    }

    public String getCity() {
        return city;
    }

}
