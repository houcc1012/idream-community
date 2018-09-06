package com.idream.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 限流过滤器
 *
 * @author hejiang
 */
@Component
@RefreshScope
public class RateLimitFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    private Map<String, RateLimiter> map = Maps.newConcurrentMap();

    @Value("${rate.limit.no:500}")
    private int rateLimitNo;

    @Value("${rate.limit.flag:false}")
    private boolean rateLimitFlag;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 8;
    }

    @Override
    public boolean shouldFilter() {
        return rateLimitFlag;
    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            // 获取serviceId
            String serviceId = (String) context.get(FilterConstants.SERVICE_ID_KEY);
            if (serviceId != null) {
                map.putIfAbsent(serviceId, RateLimiter.create(rateLimitNo));
            }
            RateLimiter rateLimiter = map.get(serviceId);
            if (rateLimiter != null && !rateLimiter.tryAcquire()) {
                logger.info("限流了!context:{}", JSON.toJSONString(context));
                //配置文件有更新的情况,动态刷新令牌桶内令牌生成数量
                if (rateLimiter.getRate() != rateLimitNo) {
                    map.put(serviceId, RateLimiter.create(rateLimitNo));
                }
                setNoPassRequestContext(context);
                return null;
            } else {
                context.setSendZuulResponse(true);// 对该请求进行路由
                context.setResponseStatusCode(HttpStatus.SC_OK);
            }
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }

    private void setNoPassRequestContext(RequestContext ctx) {
        ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
        ctx.setResponseStatusCode(HttpStatus.SC_BAD_GATEWAY);
        JSONPublicDto body = JSONPublicDto.returnErrorData(RetCodeConstants.TOO_MANY_REQUEST);
        ctx.setResponseBody(JSON.toJSONString(body));
        ctx.set("isSuccess", false);
        //解决中文乱码问题
        HttpServletResponse response = ctx.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setLocale(new java.util.Locale("zh", "CN"));
    }
}
