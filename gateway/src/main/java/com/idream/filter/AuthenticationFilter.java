package com.idream.filter;

import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.db.token.JWTTokenService;
import com.idream.commons.lib.dto.auth.PermissionInfo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author charles.wei
 */
@Component
public class AuthenticationFilter extends ZuulFilter {


    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    //例外
    @Value("#{'${exclude.path}'.split(',')}")
    private List<String> adminExcludePath;

    @Resource
    private JWTTokenService jwtTokenService;
    @Autowired
    private RedisCache redisCache;

    @Override
    public Object run() throws ZuulException {
        // TODO Auto-generated method stub
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String finalRequestURI = requestURI.substring(requestURI.indexOf("/", requestURI.indexOf("/") + 1));
        Integer authUserId = Integer.valueOf(request.getParameter("authUserId"));
        logger.info(authUserId.toString());
        List<PermissionInfo> permissions = redisCache.getList(authUserId.toString(), RedisKeyConstants.AUTH_PERMISSION, PermissionInfo.class);

        Predicate<PermissionInfo> test = p -> {
            String uri = p.getUri().replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
            return Pattern.compile("^" + uri + "$").matcher(finalRequestURI).find() && method.equals(p.getMethod());
        };
        if (!permissions.stream().anyMatch(test)) {
            setFailedRequest("no-authorized", 401);
        }
        return null;
    }

    //没有权限,调用
    private void setFailedRequest(String body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }


    @Override
    public boolean shouldFilter() {
        // TODO Auto-generated method stub
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        String requestURI = request.getRequestURI();
//        logger.info(requestURI);
//        requestURI = requestURI.substring(requestURI.indexOf("/", requestURI.indexOf("/") + 1));
//        if(!requestURI.startsWith("/admin")){
//            logger.info("前台接口路径排除过滤, requestUrl=" + requestURI);
//            return false;
//        }
//        //排除例外
//        if(adminExcludePath.contains(requestURI)) {
//            return false;
//        }else {
//            return true;
//        }
        return false;
    }

    @Override
    public int filterOrder() {
        // TODO Auto-generated method stub
        return 40;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

}
