package jkd.tourthecity.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jkd.tourthecity.security.payload.LoginAttempt;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@Log
public class LoginAttemptService {


    @Value("${tourthecity.app.max-attempts}")
    private Integer maxAttempt;

    @Value("${tourthecity.app.login-locker-in-min}")
    private Long loginLockerInMin;

    private LoadingCache<String, LoginAttempt> attemptsCache;

    public LoginAttemptService(@Value("${tourthecity.app.login-locker-in-min}") Long loginLockerInMin) {
        super();
        attemptsCache = CacheBuilder.newBuilder().
                                expireAfterWrite(loginLockerInMin, TimeUnit.MINUTES).build(new CacheLoader<String, LoginAttempt>() {
                    public LoginAttempt load(String key) {
                        return new LoginAttempt();
                    }
                });
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void unBlockUser(String key) {
        attemptsCache.invalidate(key);
    }
    public LoginAttempt loginFailed(String key) {
        int attempts;
        Instant duration = Instant.now();

        try {
            attempts = attemptsCache.get(key).getAttempt();
            duration = attemptsCache.get(key).getDuration();
        } catch (ExecutionException e) {
            attempts = 1;
        }
        attempts++;
        LoginAttempt loginAttempt = new LoginAttempt(duration, attempts);
        if(attempts <= maxAttempt) {
            loginAttempt.setDuration(Instant.now().plusSeconds(60 * loginLockerInMin));
            attemptsCache.put(key, loginAttempt);
        }
        return loginAttempt;
    }

    public boolean isBlocked(String key) {
        try {
            attemptsCache.cleanUp();
            return attemptsCache.get(key).getAttempt() >= maxAttempt;
        } catch (ExecutionException e) {
            return false;
        }
    }

    public Integer isBlockedFor(String key) {
        try {
            attemptsCache.cleanUp();
            return Duration.between(Instant.now(), attemptsCache.get(key).getDuration()).toMinutesPart() + 1;
        } catch (ExecutionException e) {
            return null;
        }
    }
}