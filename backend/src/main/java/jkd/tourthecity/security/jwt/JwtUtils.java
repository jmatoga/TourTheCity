package jkd.tourthecity.security.jwt;

import java.text.ParseException;
import java.util.Date;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;


import jkd.tourthecity.security.services.UserDetailsImpl;

@Component
@Log
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${tourthecity.app.jwtSecret}")
    private String jwtSecret;

    @Value("${tourthecity.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    public String generateJwtToken(UserDetailsImpl userPrincipal) {
                return Jwts.builder()
                       .setSubject((userPrincipal.getUsername()))
                       .setIssuedAt(new Date())
                       .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                       .signWith(SignatureAlgorithm.HS512, jwtSecret)
                       .compact();
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                       .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                       .signWith(SignatureAlgorithm.HS512, jwtSecret)
                       .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            JWTParser.parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            return false;
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
            return false;
        } catch (ParseException e) {
            logger.error("Token failed to parse: {}", e.getMessage());
            return false;
        }
    }

    public String getEmailFromJwtResponse(String token) throws ParseException {
        JWT jwt = JWTParser.parse(token);
        JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
        return claimsSet.getStringClaim("email");
    }

}
