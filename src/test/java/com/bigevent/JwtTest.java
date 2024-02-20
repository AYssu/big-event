package com.bigevent;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGen()
    {
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");

        String sign = JWT.create().withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256("ayssu"));
        System.out.println("token: "+sign);
    }

    //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MDg0NDM1NjN9.y6M6ClY7fAdqknvbRpnQzxqfoPMoEPnw7ZG5o3_MdQ8

    @Test
    public void parseToken()
    {
        String sign = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MDg0NDM1NjN9.y6M6ClY7fAdqknvbRpnQzxqfoPMoEPnw7ZG5o3_MdQ8";
        JWTVerifier build = JWT.require(Algorithm.HMAC256("ayssu")).build();
        DecodedJWT verify = build.verify(sign);
        Map<String , Claim> claimMap = verify.getClaims();
        System.out.println("user:"+claimMap.get("user"));
    }
}
