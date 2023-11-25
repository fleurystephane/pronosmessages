package com.yosfl.secure;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTSecret {
    public static byte[] SECRETKEY = System.getenv("JWT_SECRET_KEY").getBytes();
    public static String ISSUER = System.getenv("JWT_ISSUER");

    public static String generateToken(String subject, String userAgent){

        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(ISSUER)
                .claim("user-agent", userAgent)
                .signWith(Keys.hmacShaKeyFor(SECRETKEY), SignatureAlgorithm.HS256)
                .compact();
    }
}
