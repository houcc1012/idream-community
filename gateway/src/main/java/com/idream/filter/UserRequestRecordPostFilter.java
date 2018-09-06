package com.idream.filter;

import com.alibaba.fastjson.JSON;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.model.UserRequestRecord;
import com.idream.model.UserRequestApiModel;
import com.idream.rabbit.UserRequestRecordSendService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import static com.idream.filter.AccessFilter.requestApiModelThreadLocal;
import static com.idream.filter.UserRequestRecordPreFilter.startTimeThreadLocal;

/**
 * @author hejiang
 */
@Component
public class UserRequestRecordPostFilter extends ZuulFilter {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass().getName());

    protected static final String SWAGGER_URL = "/v2/api-docs";

    //过滤器排除路径例外
    @Value("#{'${exclude.path}'.split(',')}")
    private List<String> excludePath;

    @Resource
    private UserRequestRecordSendService userRequestRecordSendService;

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return (boolean) context.get("isSuccess");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = (String) ctx.get(FilterConstants.REQUEST_URI_KEY);
        UserRequestRecord record = new UserRequestRecord();
        try {
            //用户ID
            record.setIp(getRemoteIP(request));
            String deviceTypeStr = request.getHeader("deviceType");
            Byte deviceType = StringUtils.isEmpty(deviceTypeStr) ? SystemEnum.ClientChannelEnum.UNKNOW.getCode() : Byte.parseByte(deviceTypeStr);
            record.setDeviceType(deviceType);
            record.setAppVersion(request.getHeader("appVersion"));
            record.setStartTime(startTimeThreadLocal.get());

            record.setRequestMethod(request.getMethod());
            record.setRequestUrl(request.getRequestURI());
            UserRequestApiModel model = requestApiModelThreadLocal.get();
            if (model == null) {
                model = new UserRequestApiModel();
            }
            //解决 排除路径的日志记录问题
            if (excludePath.contains(requestURI)) {
                InputStream in = null;
                try {
                    //body数据
                    in = request.getInputStream();
                    String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                    model.setBodyParam(body.replaceAll("\\n", ""));
                    model.setUrlParam(AccessFilter.getUrlParams(ctx.getRequestQueryParams()));
                } catch (IOException e) {
                    logger.error("zuul过滤器设置用户请求记录失败", e);
                } finally {
                    IOUtils.closeQuietly(in);
                }
            }
            record.setRequestParam(JSON.toJSONString(model));
            record.setUserId(model.getUserId());
            //处理返回数据
            InputStream in = null;
            String responseData = "";
            try {
                in = ctx.getResponseDataStream();
                responseData = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                record.setResponseData(responseData);
            } catch (IOException e) {
                logger.error("处理用户请求记录接口响应数据失败", e);
            } finally {
                IOUtils.closeQuietly(in);
                ctx.setResponseBody(responseData);
            }
            Date date = new Date();
            record.setEndTime(date);
            record.setRequestTime((int) (date.getTime() - startTimeThreadLocal.get().getTime()));
            record.setCreateTime(date);
            userRequestRecordSendService.sendUserRequestRecord(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            startTimeThreadLocal.remove();
            requestApiModelThreadLocal.remove();
        }
        return null;
    }


    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        //优先级为0，数字越大，优先级越低
        return 30;
    }


    private String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for"); //XFF头，它代表客户端，也就是HTTP的请求端真实的IP
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP"); //代理客户端IP
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

}
