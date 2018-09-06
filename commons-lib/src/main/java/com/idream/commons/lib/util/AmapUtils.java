package com.idream.commons.lib.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.idream.commons.lib.dto.amap.AmapAddress2LocationDto;
import com.idream.commons.lib.dto.amap.AmapLocation2AddressDto;
import com.idream.commons.lib.dto.user.AmapIPLocationDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * 高德地图通过经纬度查询city通用方法
 *
 * @param
 *
 * @author DELL2018-005
 */
public class AmapUtils {
    public final static String AMAP_KEY = "4706ea0de6c8a99a9b1bc632ff825111";

    static RestTemplate restTemplate;

    private static  final int TIME_OUT = 2000;

    static String EMPTY_RESULT = "[]";
    static String LOCAL_NETWORK = "局域网";

    static {
        restTemplate = new RestTemplateBuilder().setReadTimeout(TIME_OUT).setConnectTimeout(TIME_OUT).build();
    }

    public static AmapIPLocationDto getIpLocation(String ip) {
        String url = "http://restapi.amap.com/v3/ip?ip=" + ip + "&key=" + AMAP_KEY;

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        AmapIPLocationDto locationDto = new AmapIPLocationDto();
        locationDto.setStatus(0);
        if (entity.getStatusCode().is2xxSuccessful()) {
            String body = entity.getBody();
            JSONObject jsonObject = JSONObject.parseObject(body);
            String status = jsonObject.getString("status");
            String info = jsonObject.getString("info");
            String infocode = jsonObject.getString("infocode");
            String province = jsonObject.getString("province");
            String city = jsonObject.getString("city");
            String adcode = jsonObject.getString("adcode");
            String rectangle = jsonObject.getString("rectangle");

            locationDto.setStatus(Integer.parseInt(status));
            locationDto.setInfo(info);

            locationDto.setAdcode(adcode);
            locationDto.setInfocode(Integer.parseInt(infocode));
            locationDto.setCity(city);
            locationDto.setRectangle(rectangle);
            locationDto.setProvince(province);
            if (province.equals(LOCAL_NETWORK)) {
                locationDto.setLocationNetwork(true);
                locationDto.setCity("");
            }
            if (!adcode.equals(EMPTY_RESULT)) {
                locationDto.setVaild(true);
            }

        }
        return locationDto;
    }

    /**
     * 根据地址转换经纬度
     *
     * @param address 省份＋城市＋区县＋城镇＋乡村＋街道+地址
     */
    public static AmapAddress2LocationDto getLocation(String address) {
        String url = "http://restapi.amap.com/v3/geocode/geo?address=" + address + "&key=" + AMAP_KEY;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        if (!forEntity.getStatusCode().is2xxSuccessful()) {
            return AmapAddress2LocationDto.emptyDto();
        }
        JSONObject jsonObject = JSONObject.parseObject(forEntity.getBody());

        AmapAddress2LocationDto dto=new AmapAddress2LocationDto();

        int status = Integer.parseInt(jsonObject.getString("status"));
        String info = jsonObject.getString("info");
        String infocode = jsonObject.getString("infocode");
        int count = Integer.parseInt(jsonObject.getString("count"));
        dto.setStatus(status);
        dto.setInfo(info);
        dto.setCount(count);
        dto.setInfocode(infocode);
        dto.setCount(count);

        String geocodes = jsonObject.getString("geocodes");
        JSONArray objects = JSONObject.parseArray(geocodes);
        String province="";
        String city ="";
        String adcode="";
        String location ="";

        if (!objects.isEmpty()) {
            Object o = objects.get(0);
            JSONObject object = JSONObject.parseObject(o.toString());
            province = object.getString("province");
            city = object.getString("city");
            adcode = object.getString("adcode");
            location = object.getString("location");
        }

        dto.setAdcode(adcode);
        dto.setCity(city);
        dto.setLocation(location);
        dto.setProvince(province);

        return dto;
    }

    public static AmapLocation2AddressDto getAddress(BigDecimal longitude, BigDecimal latitude){
        String url = "http://restapi.amap.com/v3/geocode/regeo?output=json&location=" + longitude + "," + latitude + "&key=" + AMAP_KEY;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        if (!forEntity.getStatusCode().is2xxSuccessful()) {
            return AmapLocation2AddressDto.emptyDto();
        }

        JSONObject jsonObject = JSONObject.parseObject(forEntity.getBody());

        AmapLocation2AddressDto dto=new AmapLocation2AddressDto();
        int status = Integer.parseInt(jsonObject.getString("status"));
        String info = jsonObject.getString("info");
        String infocode = jsonObject.getString("infocode");

        String successCode = "10000";
        if (status != 1 || !successCode.equals(infocode)) {
            return AmapLocation2AddressDto.emptyDto();
        }
        dto.setStatus(status);
        dto.setInfo(info);
        dto.setInfocode(infocode);



        JSONObject regeocode = jsonObject.getJSONObject("regeocode");
        JSONObject addressComponent = regeocode.getJSONObject("addressComponent");
        String regex = "\\[]";
        dto.setCountry(addressComponent.getString("country").replaceAll(regex,""));
        dto.setProvince(addressComponent.getString("province").replaceAll(regex,""));
        dto.setCity(addressComponent.getString("city").replaceAll(regex,""));
        return dto;


    }
}
