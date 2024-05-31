package jkd.tourthecity.service;


//import com.google.gson.Gson;
//import com.google.gson.JsonObject;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jkd.tourthecity.configuration.PropertiesConfig;
//import jkd.tourthecity.exception.InvalidResetPasswordException;
//import jkd.tourthecity.exception.UserInactiveException;
import jkd.tourthecity.model.RefreshToken;
import jkd.tourthecity.model.User;
//import jkd.tourthecity.repository.PasswordResetRepository;
import jkd.tourthecity.repository.UserRepository;
import jkd.tourthecity.security.jwt.JwtUtils;
import jkd.tourthecity.security.payload.request.LoginRequest;
//import jkd.tourthecity.security.payload.request.ResetPasswordRequestFromMail;
import jkd.tourthecity.security.payload.response.JwtResponse;
import jkd.tourthecity.security.services.UserDetailsImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class LoginServiceImpl {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final PropertiesConfig propertiesConfig;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken((UserDetailsImpl) authentication.getPrincipal());

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                                     .map(item -> item.getAuthority())
                                     .toList();

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    public HttpHeaders createHeaders(JwtResponse jwtResponse) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, String.valueOf(createAccessTokenCookie(jwtResponse.getToken(), propertiesConfig.getJwtExpirationMs() / 1000)));

        if (refreshTokenService.findByUser(jwtResponse.getEmail()) != null)
            refreshTokenService.deleteByUserId(jwtResponse.getId());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(jwtResponse.getId());
        responseHeaders.add(HttpHeaders.SET_COOKIE, String.valueOf(createRefreshTokenCookie(refreshToken.getToken(), propertiesConfig.getRefreshTokenExpirationSec())));
        return responseHeaders;
    }

    private HttpCookie createAccessTokenCookie(String token, Long duration) {
        return ResponseCookie.from(propertiesConfig.getAccessTokenCookieName(), token)
                       .maxAge(duration)
                       .httpOnly(true)
                       .sameSite("Strict")
                       .path("/")
                       .build();
    }

    private HttpCookie createRefreshTokenCookie(String token, Long duration) {
        return ResponseCookie.from(propertiesConfig.getRefreshTokenCookieName(), token)
                       .maxAge(duration)
                       .httpOnly(true)
                       .sameSite("Strict")
                       .path("/")
                       .build();
    }

    @Transactional
    public ResponseEntity<JwtResponse> getJwtResponseResponseEntity(LoginRequest loginRequest) {
        JwtResponse jwtResponse = authenticateUser(loginRequest);
        HttpHeaders responseHeaders = createHeaders(jwtResponse);
        return ResponseEntity.ok().headers(responseHeaders).body(jwtResponse);
    }

//    public String getUserEmailFromAccessToken(String accessToken) {
//        String[] chunks = accessToken.split("\\.");
//        Base64.Decoder decoder = Base64.getUrlDecoder();
//        String payload = new String(decoder.decode(chunks[1]));
//        JsonObject converted = new Gson().fromJson(payload, JsonObject.class);
//        return converted.get("sub").getAsString();
//    }

    public ResponseEntity<JwtResponse> getJwtResponseFromUser(User user) {
        UserDetailsImpl userPrincipal = UserDetailsImpl.build(user);
        String accessToken = jwtUtils.generateJwtToken(userPrincipal);
        JwtResponse jwtResponse = new JwtResponse(
                accessToken,
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles().stream().map(r -> r.name()).toList());
        HttpHeaders httpHeaders = createHeaders(jwtResponse);
        return ResponseEntity.ok().headers(httpHeaders).body(jwtResponse);
    }

//    public ResponseEntity<MessageResponse> checkIfTokesExpired(String token) {
//        PasswordReset passwordReset = getPasswordResetByToken(token);
//
//        if (passwordReset != null && passwordReset.getExpirationDate().isAfter(LocalDateTime.now()))
//            return ResponseEntity.ok(new MessageResponse("Token is still valid"));
//        else
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Token expired"));
//    }
}