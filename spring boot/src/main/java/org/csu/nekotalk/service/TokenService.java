package org.csu.nekotalk.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenService {
    public static String getToken(String phoneNumber, String password ) {



        String token="";
        token= JWT.create().withAudience(phoneNumber)// 将 phoneNumber保存到 token 里面
                .sign(Algorithm.HMAC256(password));// 以 password 作为 token 的密钥
        return token;
    }

}
