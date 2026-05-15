package org.example.healthcare.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class JwtService {

    private final String secretKey = "mySecretKeymySecretKeymySecretKeymySecretKey";
    private final long expiration = 86400000;

    private SecretKey getKey() {
             return Keys.hmacShaKeyFor(secretKey.getBytes());}


    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey())
                .compact();
    }
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey())
                .build().parseClaimsJws(token)
                .getBody().getSubject();
    }


//    public boolean validateToken(String token){
//        try{
//            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
//            return true;
//        } catch (RuntimeException e) {
//            return false;
//        }
//    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public Date extractExpiration(String token){
        return Jwts.parserBuilder().setSigningKey(getKey())
                .build().parseClaimsJws(token)
                .getBody().getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

//
//package com.healthcare.security;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//@Service
//public class JwtService {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private long expiration;
//
//    private SecretKey getKey() {
//        return Keys.hmacShaKeyFor(secret.getBytes());
//    }
//
//    // Générer le token
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(getKey())
//                .compact();
//    }
//
//    // Extraire le username
//    public String extractUsername(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getKey()).build()
//                .parseClaimsJws(token).getBody().getSubject();
//    }
//
//    // Valider le token
//
//
//    private boolean isExpired(String token) {
//        Date exp = Jwts.parserBuilder().setSigningKey(getKey()).build()
//                .parseClaimsJws(token).getBody().getExpiration();
//        return exp.before(new Date());
//    }
//}
//

//    public boolean validatToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public Date extratExpiration(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getExpiration();
//    }





}
