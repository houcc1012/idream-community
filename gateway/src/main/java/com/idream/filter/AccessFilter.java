package com.idream.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.idream.commons.db.token.JWTTokenService;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.token.TokenVerifyDto;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.model.UserRequestApiModel;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RefreshScope
public class AccessFilter extends ZuulFilter {

    static final ThreadLocal<UserRequestApiModel> requestApiModelThreadLocal = new NamedThreadLocal<UserRequestApiModel>("ThreadLocal requestApiModel");

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass().getName());

    //过滤器排除路径例外
    @Value("#{'${exclude.path}'.split(',')}")
    private List<String> excludePath;

    @Value("${spring.cloud.config.profile}")
    private String profile;

    @Resource
    private JWTTokenService jwtTokenService;

    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器，true代表需要过滤
        RequestContext ctx = RequestContext.getCurrentContext();

        // 临时处理生产app 限制访问问题
//        if ("pro".equals(profile)) {
//            String deviceType = ctx.getRequest().getHeader("deviceType");
//            if ("1".equals(deviceType) || "2".equals(deviceType)) {
//                ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
//                ctx.setResponseStatusCode(HttpStatus.SC_OK);
//                JSONPublicDto body = JSONPublicDto.returnErrorData(RetCodeConstants.SERVICE_UOGRADED);
//                ctx.setResponseBody(JSON.toJSONString(body));
//                ctx.set("isSuccess", false);
//                //解决中文乱码问题
//                HttpServletResponse response = ctx.getResponse();
//                response.setContentType("application/json;charset=UTF-8");
//                response.setCharacterEncoding("UTF-8");
//                response.setLocale(new java.util.Locale("zh", "CN"));
//                return false;
//            }
//        }

        boolean isSuccess = ctx.get("isSuccess") == null ? true : (boolean) ctx.get("isSuccess");
        if (!isSuccess) {
            return false;
        } else {
            String requestURI = (String) ctx.get(FilterConstants.REQUEST_URI_KEY);
            //排除例外
            if (excludePath.contains(requestURI)) {
                ctx.set("isSuccess", true);
                return false;
            } else {
                ctx.set("isSuccess", true);
                return true;
            }
        }
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();


        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            logger.info("token为空, url:{}", request.getRequestURI());
            setNoPassRequestContext(ctx);
            return null;
        } else {
            //校验token合法性
            TokenVerifyDto tokenVerify = jwtTokenService.verify(token);
            //令牌无效或过期
            if (!tokenVerify.isVerifyResult()) {
                setNoPassRequestContext(ctx);
                return null;
            } else {
                //用户信息
                AuthUserInfo userInfo = tokenVerify.getAuthUserInfo();
                //游客身份请求,校验游客接口权限
                if (UserEnum.UserRoleEnum.VISITOR.getCode().equals(userInfo.getUserRole())) {
                    String method = request.getMethod();
                    if (!method.equals(Http.HttpMethod.GET.toString())) {
                        setNoPassRequestContext(ctx);
                        return null;
                    }
                }
                UserRequestApiModel userRequestApiModel = new UserRequestApiModel();
                userRequestApiModel.setUserId(userInfo.getUserId());
                String contentType = request.getContentType();
                if (contentType == null || !contentType.contains("multipart/form-data")) {
                    //排除文件上传
                    InputStream in = null;
                    try {
                        //body数据
                        in = request.getInputStream();
                        String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                        userRequestApiModel.setBodyParam(body.replaceAll("\\n", ""));
                        JSONObject json = null;
                        if (org.apache.commons.lang3.StringUtils.isNotEmpty(body)) {
                            json = JSONObject.parseObject(body);
                        }
                        if (json == null) {
                            json = new JSONObject();
                        }
                        json.put("authUserInfo", tokenVerify.getAuthUserInfo());
                        final byte[] reqBodyBytes = json.toString().getBytes();
                        Map<String, List<String>> queryParamsMap = ctx.getRequestQueryParams();
                        if (queryParamsMap == null) {
                            queryParamsMap = Maps.newHashMap();
                        }

                        userRequestApiModel.setUrlParam(getUrlParams(queryParamsMap));

                        queryParamsMap.put("authUserId", new ArrayList<String>() {{
                            add(userInfo.getUserId() + "");
                        }});
                        ctx.setRequestQueryParams(queryParamsMap);
                        ctx.setRequest(new HttpServletRequestWrapper(request) {
                            @Override
                            public ServletInputStream getInputStream() throws IOException {
                                return new ServletInputStreamWrapper(reqBodyBytes);
                            }

                            @Override
                            public int getContentLength() {
                                return reqBodyBytes.length;
                            }

                            @Override
                            public long getContentLengthLong() {
                                return reqBodyBytes.length;
                            }
                        });
                    } catch (IOException e) {
                        logger.error("zuul过滤器设置用户信息失败", e);
                    } finally {
                        IOUtils.closeQuietly(in);
                    }
                }
                requestApiModelThreadLocal.set(userRequestApiModel);
                setPassRequestContext(ctx);
            }
        }
        return null;
    }

    private void setPassRequestContext(RequestContext ctx) {
        ctx.setSendZuulResponse(true);// 对该请求进行路由
        ctx.setResponseStatusCode(HttpStatus.SC_OK);
    }

    private void setNoPassRequestContext(RequestContext ctx) {
        ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
        ctx.setResponseStatusCode(HttpStatus.SC_OK);
        JSONPublicDto body = JSONPublicDto.returnErrorData(RetCodeConstants.REQUEST_VALIDATE_ERROR);
        ctx.setResponseBody(JSON.toJSONString(body));
        ctx.set("isSuccess", false);
        //解决中文乱码问题
        HttpServletResponse response = ctx.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setLocale(new java.util.Locale("zh", "CN"));
    }

    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //优先级为0，数字越大，优先级越低
        return 10;
    }

    public static String getUrlParams(Map<String, List<String>> requestParams) {
        if (requestParams != null && !requestParams.isEmpty()) {
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String key = (String) iter.next();
                List<String> values = requestParams.get(key);
                paramsMap.put(key, values.stream().collect(Collectors.joining(",")));
            }
            return JSON.toJSONString(paramsMap);
        } else {
            return "";
        }

    }
}

