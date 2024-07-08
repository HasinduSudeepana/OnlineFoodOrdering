package com.Hasindu.Online.Food.Ordering.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtProvider {
    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    //create a method for generated token
    public String generateToken(Authentication auth){
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();  //Unbounded Wildcard (<?>):  authorities =  it is a collection that can hold elements of any type of GrantedAuthority.
        String roles = populateAuthorities(authorities);   //convert the collection of authorities into a comma-separated string of roles.

        String jwt = Jwts.builder().setIssuedAt(new Date())
                .setExpiration((new Date(new Date().getTime()+86400000)))   //set expire date within 24 hours
                .claim("email", auth.getName())
                .claim("authorities",roles)
                .compact();
        return jwt;
    }

    private String getEmailFromJwtToken(String jwt){
        jwt = jwt.substring(7);

        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));
        return email;
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();

        for (GrantedAuthority authority : authorities){
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }

}
