package jkd.tourthecity.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jkd.tourthecity.security.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import jkd.tourthecity.configuration.PropertiesConfig;
import jkd.tourthecity.security.services.UserDetailsServiceImpl;
import jkd.tourthecity.service.RefreshTokenService;

import java.io.IOException;
import java.util.Arrays;

@Log
@RequiredArgsConstructor
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;
    private final PropertiesConfig propertiesConfig;

    private String getJwtFromAccessCookie(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Wycinamy "Bearer "
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (propertiesConfig.getAccessTokenCookieName().equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtAccessFromCookie = getJwtFromAccessCookie(request);

            if (jwtAccessFromCookie != null  && jwtUtils.validateJwtToken(jwtAccessFromCookie)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwtAccessFromCookie);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.info("Cannot set user authentication " + e);
        }
        filterChain.doFilter(request, response);
    }
}
