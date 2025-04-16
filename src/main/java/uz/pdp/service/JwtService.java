package uz.pdp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.pdp.model.User;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public record JwtService(
        @Value("${app.jwt.access.secretKey}") String accessSecretKey,
        @Value("${app.jwt.access.expirationAt}") long accessExpirationAt
) {


    public String generateAccessToken(User user) {
        return Jwts
                .builder()
                .subject(user.getId().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpirationAt))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(accessSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getIdFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
