package jkd.tourthecity.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AllowedPathsConfig {

    private final PropertiesConfig propertiesConfig;

    @Bean("allowedPaths")
    public String[] allowedPaths() {
        return new String[] {
                propertiesConfig.getPATH_LOGIN(),
                propertiesConfig.getPATH_LOGOUT(),
                "/api/auth/**",
        };
    }
}
