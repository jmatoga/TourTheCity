//package jkd.tourthecity.configuration;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//    public void g() {
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
//        System.out.println("New SECRET_KEY: " + encodedKey);
//    }
//    //private static final String SECRET_KEY = "3fc78f29307c30ac2fde00f0524d8520f2659fd91c58d409b4c41aff2098b36c";
//   // private static final String SECRET_KEY = "9fzhvDvgCXh+E5a81eDyj+sIsCenQj37ZepLVwZOk72jsI2dfc35C9VnkFORacBDZfh3NoQrKnh0u4kClmpPpg==";
////    public String extractUsername(String token) {
////        return extractClaim(token, Claims::getSubject);
////    }
////
////    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
////        final Claims claims = extractAllClaims(token);
////        return claimsResolver.apply(claims);
////    }
////
////    public String generateToken(UserDetails userDetails) {
////        return generateToken(new HashMap<>(), userDetails);
////    }
////
////    public String generateToken(
////            Map<String, Object> extraClaims,
////            UserDetails userDetails
////    ) {
////        return Jwts
////            .builder()
////            .setClaims(extraClaims)
////            .setSubject(userDetails.getUsername())
////            .setIssuedAt(new Date(System.currentTimeMillis()))
////            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
////            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
////            .compact();
////    }
////
////    public Boolean isTokenValid(String token, UserDetails userDetails) {
////        final String username = extractUsername(token);
////        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
////    }
////
////    private boolean isTokenExpired(String token) {
////        return extractExpiration(token).before(new Date());
////    }
////
////    private Date extractExpiration(String token) {
////        return extractClaim(token, Claims::getExpiration);
////    }
////
////    private Claims extractAllClaims(String token) {
////        g();
////        return Jwts
////            .parserBuilder()
////            .setSigningKey(getSignInKey())
////            .build()
////            .parseClaimsJws(token)
////            .getBody();
////    }
////
////    private Key getSignInKey() {
////        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
////        return Keys.hmacShaKeyFor(keyBytes);
////    }
//
//    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Ensure this key remains consistent
//
//    // Generate token using username
//    public String generateToken(String username) {
//        return Jwts.builder()
//                       .setSubject(username)
//                       .signWith(key)
//                       .compact();
//    }
//
//    // Extract all claims from token
//    public Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                       .setSigningKey(key)
//                       .build()
//                       .parseClaimsJws(token)
//                       .getBody();
//    }
//
//    // Extract username from token
//    public String extractUsername(String token) {
//        return extractAllClaims(token).getSubject();
//    }
//
//    // Extract a specific claim from token
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    // Generate token using UserDetails
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userDetails.getUsername());
//    }
//
//    // Create token with claims and subject
//    private String createToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                       .setClaims(claims)
//                       .setSubject(subject)
//                       .setIssuedAt(new Date(System.currentTimeMillis()))
//                       .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiry
//                       .signWith(key, SignatureAlgorithm.HS512)
//                       .compact();
//    }
//
//}
