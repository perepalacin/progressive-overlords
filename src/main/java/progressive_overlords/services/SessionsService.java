package progressive_overlords.services;

import jakarta.servlet.http.Cookie;
import progressive_overlords.entities.dao.UserDao;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.Claims;


public class SessionsService {
    private static final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000; // 7 days
    private final static String secretKey = "RrQS0MvN+9H8f3H5+M8Y/gLvgZDtu4y/0AKJGq4JsqA=";

    public static String generateToken(UserDao user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("roles", "USER")
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey())
                .compact();
    }

    private static SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Claims getClaimsOfOneTimeToken(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static boolean isTokenValid(String token){
        return !isExpired(token);
    }

    private static boolean isExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    public static Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static UUID getUserId(String token) {
        return UUID.fromString(extractClaim(token, Claims::getSubject));
    }

    private static  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

}
