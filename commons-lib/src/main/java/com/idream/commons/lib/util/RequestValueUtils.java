package com.idream.commons.lib.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RequestValueUtils {
    public static Integer getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        Integer userId = Integer.valueOf(request.getParameter("authUserId"));
        return userId;
    }

    public static String getIp() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        //XFF头，它代表客户端，也就是HTTP的请求端真实的IP
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //代理客户端IP
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.length() > 0) {
            String[] ipArray = ip.split(",");
            if (ipArray != null && ipArray.length > 1) {
                return ipArray[0];
            }
            return ip;
        }
        return "未知IP";
    }

    public static Object getAttributeValue(String name) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getAttribute(name);
    }

    public static void setAttributeValue(String name, Object obj) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        request.setAttribute(name, obj);
    }


}
