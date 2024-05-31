package jkd.tourthecity.configuration;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@Scope("singleton")
@PropertySource("classpath:paths.properties")
@Getter
public class PropertiesConfig {
    @SuppressWarnings("squid:S116")
    @Value("${tourthecity.app.pagination.defaultSizeOfPage}")
    private int PAGE_SIZE;

    @SuppressWarnings("squid:S116")
    @Value("${tourthecity.app.accessTokenCookieName}")
    private String accessTokenCookieName;

    @SuppressWarnings("squid:S116")
    @Value("${tourthecity.app.refreshTokenCookieName}")
    private String refreshTokenCookieName;

    @SuppressWarnings("squid:S116")
    @Value("${tourthecity.app.jwtExpirationMs}")
    private Long jwtExpirationMs;

    @SuppressWarnings("squid:S116")
    @Value("${tourthecity.app.refreshTokenExpirationSec}")
    private Long refreshTokenExpirationSec;

    @Value("${tourthecity.app.paths.login}")
    private String PATH_LOGIN;

    @Value("${tourthecity.app.paths.logout}")
    private String PATH_LOGOUT;
}
