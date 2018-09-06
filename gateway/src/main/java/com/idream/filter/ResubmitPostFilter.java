package com.idream.filter;

import com.idream.commons.db.redis.RedisLock;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.annotation.Resource;

/**
 * @author hejiang
 */
public class ResubmitPostFilter extends ZuulFilter {

    @Resource
    private RedisLock redisLock;

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        Object flag = context.get("isResubmitSuccess");
        return flag == null ? false : (boolean) flag;
    }

    @Override
    public Object run() throws ZuulException {
        redisLock.releaseLock(ResubmitPreFilter.requestResubmitLockKey.get());
        ResubmitPreFilter.requestResubmitLockKey.remove();
        return null;
    }


    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        //优先级为0，数字越大，优先级越低
        return 25;
    }
}
