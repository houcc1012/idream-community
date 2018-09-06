package com.idream.utils;

import com.idream.commons.lib.dto.user.AmapIPLocationDto;
import com.idream.commons.lib.dto.user.GetInfoByIpDto;
import com.idream.commons.lib.util.AmapUtils;

import java.util.regex.Pattern;

/**
 * @param
 */

public class IpToAddressUtils {

    /**
     * @param : ip
     */
    public static GetInfoByIpDto getUserInfoByIp(String ip) {
//        String url = "http://ip.taobao.com/service/getIpInfo.php";
//        Map<String, String> map = Maps.newHashMap();
//        String pattern = "[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}";
//        boolean isMatch = Pattern.matches(pattern, ip);
//        if (!isMatch) {
//            ip = "115.236.160.82";
//        }
//        map.put("ip", ip);
//        String resultJson = HttpRequestUtils.sendGet(url, map);
//        JSONObject json = null;
//        GetInfoByIpDto getInfoByIpDto = null;
//        if (!StringUtils.isEmpty(resultJson)) {
//            json = JSONObject.parseObject(resultJson);
//            if (json.getString("code").equals("0")) {
//                getInfoByIpDto = JSON.parseObject(json.getString("data"), GetInfoByIpDto.class);
//                getInfoByIpDto.setCity(getInfoByIpDto.getCity() + "市");
//                getInfoByIpDto.setRegion(getInfoByIpDto.getRegion() + "省");
//            }
//        } else {
//            getInfoByIpDto = new GetInfoByIpDto();
//            getInfoByIpDto.setCity("未知城市");
//        }
        String pattern = "[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}";
        boolean isMatch = Pattern.matches(pattern, ip);
        if (!isMatch) {
            ip = "115.236.160.82";
        }
        AmapIPLocationDto dto = AmapUtils.getIpLocation(ip);
        GetInfoByIpDto getInfoByIpDto = new GetInfoByIpDto();
        if(dto != null && dto.success()){
            getInfoByIpDto.setRegion(dto.getProvince());
            getInfoByIpDto.setCity(dto.getCity());
            getInfoByIpDto.setCityId(dto.getCityCode());
            getInfoByIpDto.setRegionId(dto.getProvinceCode());
        }else{
            getInfoByIpDto.setCity("未知城市");
        }
        return getInfoByIpDto;
    }
}
