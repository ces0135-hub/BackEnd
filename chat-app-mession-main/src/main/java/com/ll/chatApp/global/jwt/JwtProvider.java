package com.ll.chatApp.global.jwt;

import com.ll.chatApp.domain.member.member.entity.Member;
import com.ll.chatApp.global.util.Ut;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    @Value("${custom.jwt.secretKey}")
    private String secretKeyOrigin;

    @Value("${custom.accessToken.expirationSeconds}")
    private int accessTokenExpirationSeconds;  // 유효 시간도 암호화


    private SecretKey cachedSecretKey;

    public SecretKey getSecretKey() {
        if(cachedSecretKey == null) {
            cachedSecretKey = _getSecretKey();  // _는 내부적으로 쓰이는 함수 표현
        }
        return cachedSecretKey;
    }

    // 정해진 암호화 양식
    private SecretKey _getSecretKey() {
        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyOrigin.getBytes());
        return Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
    }

    public String genAccessToken(Member member) {
        return genToken(member, accessTokenExpirationSeconds);  // 10분 동안 유효
    }

    public String genRefreshToken(Member member) {
        return genToken(member, accessTokenExpirationSeconds);  // 1년 동안 유효
    }

    // Token 생 => 회원정보, 유효기간을 입력받음
    public String genToken(Member member, int seconds) {
        Map<String, Object> claims = new HashMap<>();  // hashMap 생성

        claims.put("id", member.getId());  // id라는 키값에 member의 id 넣기
        claims.put("username", member.getUsername());  // username이라는 키값에 member의 username 넣기

        long now = new Date().getTime();
        Date accessTokenExpiresIn = new Date(now + 1000L * seconds);  // 유효기간 생성

        return Jwts.builder()
                .claim("body", Ut.json.toStr(claims))  // map으로 받아서 JSON으로 변환 => body라는 키값에 저장
                .setExpiration(accessTokenExpiresIn)  // Token 유효 시간
                .signWith(getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }
}
