package jkd.tourthecity.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import jkd.tourthecity.exception.LoginAttemptException;
import jkd.tourthecity.security.payload.LoginAttempt;

import jakarta.servlet.http.HttpServletRequest;

@Log
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Value("${tourthecity.app.max-attempts}")
    Integer maxAttempt;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        String userEmail = e.getAuthentication().getPrincipal().toString();

        LoginAttempt attempt = loginAttemptService.loginFailed(userEmail);
        throw new LoginAttemptException(
                "Incorrect login details were provided! Attempts left: " + (maxAttempt + 1 - attempt.getAttempt())
        );
    }
}
