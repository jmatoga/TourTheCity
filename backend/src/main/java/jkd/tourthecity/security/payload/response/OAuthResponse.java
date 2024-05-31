package jkd.tourthecity.security.payload.response;

import java.util.UUID;

public record OAuthResponse(
        String access_token,
        int expires_in,
        String scope,
        String token_type,
        String id_token
) {}
