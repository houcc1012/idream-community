package com.idream.commons.mvc.resolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.exception.ApiRequestException;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.exception.RequestParamValidException;
import com.idream.commons.lib.exception.ValidateException;
import org.apache.catalina.connector.Response;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Exception 异常处理
 * 异常时返回抛出的错误信息
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        JSONObject json = new JSONObject();
        //businessException处理
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            json.put(RetCodeConstants.RET_CODE, businessException.getErrorCode());
            json.put(RetCodeConstants.RET_MSG, businessException.getMessage());
        } else if (ex instanceof ValidateException) {
            ValidateException validateException = (ValidateException) ex;
            json.put(RetCodeConstants.RET_CODE, validateException.getErrorCode());
            json.put(RetCodeConstants.RET_MSG, validateException.getMessage());
        } else if (ex.getClass().isAssignableFrom(DataAccessException.class)) {
            json.put(RetCodeConstants.RET_CODE, RetCodeConstants.DATABASE_ERROR);
            json.put(RetCodeConstants.RET_MSG, "数据库请求异常！");
        } else if (ex.getClass().isAssignableFrom(ApiRequestException.class)) {
            json.put(RetCodeConstants.RET_CODE, RetCodeConstants.DATA_PARSE_ERROR);
            json.put(RetCodeConstants.RET_MSG, "接口请求错误！");
        } else if (ex.getClass().isAssignableFrom(RequestParamValidException.class)) {
            RequestParamValidException requestParamValidException = (RequestParamValidException) ex;
            json.put(RetCodeConstants.RET_CODE, RetCodeConstants.DATA_PARSE_ERROR);
            json.put(RetCodeConstants.RET_MSG, requestParamValidException.getMessage());
        } else {
            json.put(RetCodeConstants.RET_CODE, RetCodeConstants.UNKOWN_ERROR);
            json.put(RetCodeConstants.RET_MSG, "未知错误!");
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(Response.SC_OK);

        OutputStream os = null;
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(JSON.toJSONString(json).getBytes("UTF-8"));
            os = response.getOutputStream();
            IOUtils.copy(is, os);
        } catch (Exception e) {
            LOGGER.error("Failed to serialize the object to json for exception handling.", e);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
        return null;
    }
}
