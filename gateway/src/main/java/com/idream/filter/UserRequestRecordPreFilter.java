package com.idream.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author hejiang
 */
@Component
public class UserRequestRecordPreFilter extends ZuulFilter {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass().getName());

    static final ThreadLocal<Date> startTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal apiRequestStartTime");

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return (boolean) context.get("isSuccess");
    }

    @Override
    public Object run() throws ZuulException {
        startTimeThreadLocal.set(new Date());
        return null;
    }


    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //优先级为0，数字越大，优先级越低
        return 15;
    }

}
