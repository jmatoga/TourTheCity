package jkd.tourthecity.configuration;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import jkd.tourthecity.security.jwt.AuthTokenFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Log
public class WebSecurityConfig {
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Qualifier("allowedPaths")
    @Autowired
    public String[] allowedPaths;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));  // domena klienta, która ma mieć dostęp
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                       .csrf(csrf -> csrf.disable())
//                       .cors()
//                       .and()
//                       .authorizeHttpRequests(auth ->
//                                                      auth.requestMatchers(allowedPaths).permitAll().anyRequest().authenticated())
//                       .sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                       .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//                       .and()
//                       .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                       .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                       .csrf(csrf -> csrf.disable())
                       .cors()
                       .and()
                       .authorizeHttpRequests()
                       .requestMatchers(allowedPaths).permitAll() // Ustawienie określonych ścieżek jako dostępnych dla wszystkich użytkowników
                       .anyRequest().authenticated() // Pozostałe ścieżki wymagają uwierzytelnienia
                       .and()
                       .sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                       .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                       .and()
                       .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                       .build();
    }

}
