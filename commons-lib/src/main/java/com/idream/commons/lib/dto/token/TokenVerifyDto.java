package com.idream.commons.lib.dto.token;

/**
 * @Author: hejiang
 * @Description: token校验返回参数
 * @Date: 9:14 2018/3/23
 */
public class TokenVerifyDto {

    //token解析后参数
    private AuthUserInfo authUserInfo;

    //token
    private String token;

    //校验是否通过 true-通过；false-未通过
    private boolean verifyResult = true;

    public AuthUserInfo getAuthUserInfo() {
        return authUserInfo;
    }

    public void setAuthUserInfo(AuthUserInfo authUserInfo) {
        this.authUserInfo = authUserInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(boolean verifyResult) {
        this.verifyResult = verifyResult;
    }
}
