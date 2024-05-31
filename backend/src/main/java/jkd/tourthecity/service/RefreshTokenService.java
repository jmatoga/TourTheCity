package jkd.tourthecity.service;

import jkd.tourthecity.model.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String userId);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token);
    void deleteByUserId(String userId);
    void deleteByToken(String token);
    String findByUser(String email);
}
