package seu.edu.bd.southeast_portal.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private String secret;

    private String genSecretKey(String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        SecretKey key = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public JwtService() {
        try{
            secret = genSecretKey("HmacSHA256");
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    public String generateJwtToken(String userName, boolean longExpiration) {
        Map<String, Object> claims = new HashMap<>();
        long expiration;
        if (longExpiration) {
            expiration = 60*60*60*48;
        }else {
            expiration = 60*60*60*6;
        }


        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .and()
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey(){
        byte[] keyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
