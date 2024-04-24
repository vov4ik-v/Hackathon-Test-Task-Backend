package com.awl.hackathontesttaskbackend.security;



import com.awl.hackathontesttaskbackend.repository.UserRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JWTTokenProvider {
    public static final Logger LOG = LoggerFactory.getLogger(JWTTokenProvider.class);
    private final UserRepository userRepository;

    public JWTTokenProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(Authentication authentication){
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
//        Date expiryDate = new Date(now.getTime()+ SecurityConstants.EXPIRATION_TIME);
        String userId = Long.toString(user.getId());

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", userId);
        claimsMap.put("email", user.getEmail());
        claimsMap.put("authories", authentication.getAuthorities());
        return Jwts.builder().setSubject(userId).setClaims(claimsMap).setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
    }

    public boolean validateToken (String token){
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
            return true;
        }
        catch (SignatureException |
                MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException|
                IllegalArgumentException ex) {

            LOG.error(ex.getMessage());
            return false;
        }
    }

    public Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token).getBody();

      String id = (String) claims.get("id");
      return  Long.parseLong(id);
    }
}
