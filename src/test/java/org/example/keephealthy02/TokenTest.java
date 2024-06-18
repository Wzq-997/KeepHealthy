package org.example.keephealthy02;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
public class TokenTest {
    @Test
    public void test(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","www");
        String nonine = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "Nonine")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 43200 * 1000))//一小时有效期
                .compact();

        System.out.println(nonine);
    }

//    解析令牌
    @Test
    public void testParse(){
        Claims nonine = Jwts.parser()
                .setSigningKey("Nonine")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoid3d3IiwiaWQiOjEsImV4cCI6MTcxODEzNzQxM30.r5B8wNs2yPJ1k1uo3L5yqyWaESivAbgEebx37YeurbM")
                .getBody();
        System.out.println(nonine);

    }
}
