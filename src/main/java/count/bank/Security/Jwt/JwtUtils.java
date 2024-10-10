package count.bank.Security.Jwt;

import count.bank.Security.CustomersDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExpiration;
    private SecretKey getSecretKey() {
        byte[] decodeKey = Base64.getDecoder().decode(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(decodeKey);
    }

    public String generateToken(Authentication authentication) {
        CustomersDetails principal = (CustomersDetails) authentication.getPrincipal();
            //Generar UUID
            String uuid = UUID.randomUUID().toString();

            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + jwtExpiration * 1000L);
            // Agregar reclamaciones privadas
            String token = Jwts.builder()
                    .subject(uuid)
                    .claim("Id", principal.getId())
                    .claim("Email", principal.getUsername())
                    .claim("Roles", principal.getAuthorities())
                    .issuedAt(new Date())
                    .expiration(expiryDate)
                    .signWith(getSecretKey()).compact();

            return token;

    }

    // Método para validar el token JWT
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().verifyWith(getSecretKey()).build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            // Maneja la excepción como sea necesario
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token){
        Claims claims= Jwts.parser().verifyWith(getSecretKey()).build()
                .parseSignedClaims(token)
                .getPayload();
        return String.valueOf(claims);
    }



//    public Claims decode(String token)  {
//        try {
//            return Jwts.parser()
//                    .verifyWith(getSecretKey())
//                    .build()
//                    .parseSignedClaims(token)
//                    .getPayload();
//        } catch (ExpiredJwtException e) {
//            throw ExceptionHelper.unauthorized("El token JWT ha expirado");
//        }
//
//    }

}
