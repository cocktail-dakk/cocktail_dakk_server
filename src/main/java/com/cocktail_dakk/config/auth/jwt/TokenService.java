package com.cocktail_dakk.config.auth.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;


@Service
public class TokenService {

    public Token generateToken(String uid, String role){
        long tokenPeriod=1000L*60L*60L*2L;
        long refreshPeriod=1000L*60L*60L*24L*30L;

        Claims claims= Jwts.claims().setSubject(uid);
        claims.put("role", role);

        Date now=new Date();
        return new Token(
                Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime()+tokenPeriod))
                    .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                    .compact(),
                Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime()+refreshPeriod))
                    .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                    .compact()
        );
    }

    public boolean verifyToken(String token){
        try{
            Jws<Claims> claims=Jwts.parser()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .parseClaimsJws(token);
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        }catch (Exception e){
            return false;
        }
    }

    public String getUid(String token){
        return Jwts.parser().setSigningKey(Secret.JWT_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(Secret.JWT_SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
