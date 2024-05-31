package jkd.tourthecity.security.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginAttempt {
    Instant duration = Instant.now();
    Integer attempt = 1;
}
