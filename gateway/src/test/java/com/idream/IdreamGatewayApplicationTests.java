package com.idream;

import com.auth0.jwt.JWTVerifyException;
import com.idream.commons.db.token.JWTTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdreamGatewayApplicationTests {

    @Resource
    private JWTTokenService jwtTokenService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testJwt() throws SignatureException, NoSuchAlgorithmException, JWTVerifyException, InvalidKeyException, IOException {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlvbklkIjoib25lR2cwc2dkeEpQOVdFSmV2NTMyUnM1eWpQQSIsInBob25lIjpudWxsLCJuaWNrTmFtZSI6Iuavm-avm-iZqyIsIm9wZW5JZCI6bnVsbCwidXNlclR5cGUiOjEsInVzZXJOYW1lIjpudWxsLCJ1c2VySWQiOjYxOCwidHMiOjE1Mjk0NzY3MzYwMjJ9.mTpunz80Jt2vGlYk8gcDDD5dB37TVv4gYCe91tzNZa4";

        Map<String, Object> authUserMap = jwtTokenService.getJwtVerifier().verify(token);
        System.out.println(">>>>>>>>>>>>>>>:" + authUserMap);
    }
}
