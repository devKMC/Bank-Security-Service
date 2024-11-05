package com.devkmc.Bank_Security_Service.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

// jwt 생성 및 검증 기능 제공자
// - jwt 암호화 알고리즘 HS256
// - 비밀키는 환경변수에 있는 jwt.secret-key 
// - jwt 만료 기간 10시간 
//  TODO (이후 1시간)
@Component
public class JwtProvider {

    // 비밀키 작업
    @Value("${jwt.secret-key}")
    private String secretKey;

    // JWT 생성 메서드
    // JWT 생성할때 userId를 사용
    public String create(String userId) {


        // 만료시간 = 현재시간 + 10시간
        Date expiredDate = Date.from(Instant.now().plus(10, ChronoUnit.HOURS));
        
        String jwt = null;
        try{
            // 예외가 발생 할 수 있어 트라이 캐치문 작성
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            // JWT 만들기
                jwt = Jwts.builder()
                    .signWith(key, SignatureAlgorithm.HS256)
                    .setSubject(userId) // 주체
                    .setIssuedAt(new Date()) // 생성시간
                    .setExpiration(expiredDate) // 완료시간
                    .compact(); // 인코딩
        
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
        return jwt;
    }
    // JWT 검증 메서드
    public String validate(String jwt) {

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String userId = null; // 반환할 변수 생성

        try {
            // 키가 일치한다면
            userId = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build() // 유효성 검사
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
        } catch (Exception exception) {
            exception.printStackTrace(); // 예외가 발생한다면
            return null;
        }
        return userId; // 검증이 끝나면 user ID 반환
    }

}