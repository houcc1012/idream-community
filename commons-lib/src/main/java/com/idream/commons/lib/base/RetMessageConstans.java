package com.idream.commons.lib.base;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author hejiang
 */
public class RetMessageConstans {

    private static final Map<String, String> RET_MESSAGE = Maps.newHashMap();

    static {
        RET_MESSAGE.put(RetCodeConstants.UNKOWN_ERROR, "未知的错误！");
        RET_MESSAGE.put(RetCodeConstants.PARAMS_ERROR, "参数错误！");
        RET_MESSAGE.put(RetCodeConstants.DATA_PARSE_ERROR, "数据解析错误！");
        RET_MESSAGE.put(RetCodeConstants.REQUEST_VALIDATE_ERROR, "请求鉴权失败");
        RET_MESSAGE.put(RetCodeConstants.REQUEST_VERIFICATIONCODE, "短信验证码失败");
        RET_MESSAGE.put(RetCodeConstants.TOO_MANY_REQUEST, "服务器繁忙,请您休息一下吧");
        RET_MESSAGE.put(RetCodeConstants.RE_SUBMIT, "服务器正在努力处理中, 请勿重复提交");
        RET_MESSAGE.put(RetCodeConstants.SERVICE_UOGRADED, "服务正在升级中...");
    }

    public static Map<String, String> getRetMessage() {
        return RET_MESSAGE;
    }
}
