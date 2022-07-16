package com.sistema.blog.security;

import com.sistema.blog.exceptions.BlogAppException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationMs;


    public String generateToken(Authentication authentication){
        String username=authentication.getName();
        Date dateNow= new Date();
        Date expirationDate= new Date(dateNow.getTime()+ jwtExpirationMs);

        String token= Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();

        return  token;
    }

    public String getUsername(String token){
        Claims claims= Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Firma JWT no valida");
        }catch (MalformedJwtException e){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST,"Firma JWT no valida");
        }catch (ExpiredJwtException e){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
        }catch (UnsupportedJwtException e){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
        }catch (IllegalArgumentException e){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "Cadena clamins JWT está vacía");
        }
    }
}
