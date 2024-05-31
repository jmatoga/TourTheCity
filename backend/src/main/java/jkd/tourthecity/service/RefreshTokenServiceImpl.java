package jkd.tourthecity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import jkd.tourthecity.configuration.PropertiesConfig;
import jkd.tourthecity.exception.RefreshTokenException;
import jkd.tourthecity.exception.internal.data.inconsistency.ResourceNotFoundException;
import jkd.tourthecity.model.RefreshToken;
import jkd.tourthecity.model.User;
import jkd.tourthecity.repository.RefreshTokenRepository;
import jkd.tourthecity.repository.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final PropertiesConfig propertiesConfig;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userId) {
        RefreshToken refreshToken = new RefreshToken();
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {

            refreshToken.setUser(optionalUser.get());
            refreshToken.setExpiryDate(Instant.now().plusSeconds(propertiesConfig.getRefreshTokenExpirationSec()));
            refreshToken.setToken(UUID.randomUUID().toString());

            refreshToken = refreshTokenRepository.save(refreshToken);
            return refreshToken;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find user with id: " + userId);
    }

    @Transactional
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenException("Refresh token was expired. Please make a new signin request") ;
        }

        return token;
    }

    @Transactional
    public void deleteByUserId(String userId) {
        User user = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("not found"));
        refreshTokenRepository.deleteByUser(user);
    }

    @Transactional
    public void deleteByToken(String token) {
        refreshTokenRepository.findByToken(token)
                .ifPresent(refreshTokenRepository::delete);
    }

    @Override
    public String findByUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(User.class,"email",email));
        return refreshTokenRepository.findByUser(user.getId());
    }

}
