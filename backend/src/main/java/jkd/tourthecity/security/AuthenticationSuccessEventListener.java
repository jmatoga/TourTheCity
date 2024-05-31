package jkd.tourthecity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationSuccessEvent e) {
        String userEmail = e.getAuthentication().getName();
        loginAttemptService.loginSucceeded(userEmail);
    }

}