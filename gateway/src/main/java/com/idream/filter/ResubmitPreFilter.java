package com.idream.filter;

import com.alibaba.fastjson.JSON;
import com.idream.commons.db.redis.RedisLock;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.NamedThreadLocal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.zip.CRC32;

/**
 * @author hejiang
 */
public class ResubmitPreFilter extends ZuulFilter {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass().getName());

    static final ThreadLocal<String> requestResubmitLockKey = new NamedThreadLocal<String>("RequestResubmitLockKey");

    @Resource
    private RedisLock redisLock;

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        if ((boolean) context.get("isSuccess")) {
            HttpServletRequest request = context.getRequest();
            // 方法类型
            String method = request.getMethod();
            if (!"post".equalsIgnoreCase(method)) {
                return false;
            } else {
                context.set("isResubmitSuccess", true);
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 用户token
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            String result = (System.currentTimeMillis() / 1000) + "";
            String preStr = result.substring(0, 9);
            if (Integer.parseInt(result.substring(9)) < 5) {
                token = preStr + "1";
            } else {
                token = preStr + "9";
            }
        }
        // 请求url
        String requestURI = (String) ctx.get(FilterConstants.REQUEST_URI_KEY);
        StringBuilder sb = new StringBuilder(token).append(requestURI);
        //锁KEY
        String lockKey = crc32Code(sb.toString().getBytes()) + "";
        if (!redisLock.lock(lockKey)) {
            String contentType = request.getContentType();
            // 图片提交不做限制
            if (contentType == null || !contentType.contains("multipart/form-data")) {
                setNoPassRequestContext(ctx);
            }
        } else {
            requestResubmitLockKey.set(lockKey);
        }
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
        return 20;
    }

    private void setNoPassRequestContext(RequestContext ctx) {
        ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
        ctx.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
        JSONPublicDto body = JSONPublicDto.returnErrorData(RetCodeConstants.RE_SUBMIT);
        ctx.setResponseBody(JSON.toJSONString(body));
        ctx.set("isResubmitSuccess", false);
        //解决中文乱码问题
        HttpServletResponse response = ctx.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setLocale(new java.util.Locale("zh", "CN"));
    }

    private static long crc32Code(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        return crc32.getValue();
    }

}
