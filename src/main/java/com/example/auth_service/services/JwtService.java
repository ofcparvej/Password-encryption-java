package com.example.auth_service.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecretJwk;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.security.KeyRep.Type.SECRET;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private  String SECRET;

    public String createToken(Map<String, Object> payload , String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry);
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                        .claims(payload)
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(expiryDate)
                        .subject(username)
                        .signWith(key)
                        .compact();


    }

    public String createToken(String email){
        return createToken(new HashMap<>() , email);
    }

    public Claims extractAllPayload(String token ){
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts
                .parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token , Function<Claims , T>claimsResolver){
        final Claims claims = extractAllPayload(token);
        return claimsResolver.apply(claims);
    }



    public Date extractExpirationDate(String token){
          return extractClaim(token , Claims::getExpiration);
    }

    public String extractUsername(String token){
        return extractClaim(token , Claims::getSubject);
    }


    public Boolean isTokenExpired(String token){
         return extractExpirationDate(token).before(new Date());
    }


    @Override
    public void run(String... args) throws Exception {
        Map<String , Object> mp = new HashMap<>();
        mp.put("email" , "a@b.com");
        mp.put("phone : " , "123321");

        String result = createToken(mp , "parvej");
        System.out.println("TOKEN----> "+result);

        System.out.println("valid----> ?" + extractUsername(result));

    }
}
