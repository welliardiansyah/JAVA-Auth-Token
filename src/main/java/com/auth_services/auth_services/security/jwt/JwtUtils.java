package com.auth_services.auth_services.security.jwt;

import com.auth_services.auth_services.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${welliardiansyah.app.jwtSecret}")
    private String jwtSecret;

    @Value("${welliardiansyah.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${welliardiansyah.app.jwtCookieName}")
    private String jwtCookie;

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
        return cookie.getValue();
        } else {
        return null;
        }
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", userPrincipal.getId());
        response.put("username", userPrincipal.getUsername());
        response.put("email", userPrincipal.getEmail());
        response.put("roles", userPrincipal.getAuthorities());

        return Jwts.builder()
                .claim(jwtCookie, response)
                .setSubject((userPrincipal.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
        return cookie;
      }
    
      public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
        return cookie;
      }
    
      public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
            .parseClaimsJws(token).getBody().getSubject();
      }
      
      private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
      }
    
      public boolean validateJwtToken(String authToken) {
        try {
          Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
          return true;
        } catch (MalformedJwtException e) {
          logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
          logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
          logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
          logger.error("JWT claims string is empty: {}", e.getMessage());
        }
    
        return false;
      }
      
      public String generateTokenFromUsername(String username) {   
        return Jwts.builder()
                  .setSubject(username)
                  .setIssuedAt(new Date())
                  .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                  .signWith(key(), SignatureAlgorithm.HS256)
                  .compact();
      }
}
