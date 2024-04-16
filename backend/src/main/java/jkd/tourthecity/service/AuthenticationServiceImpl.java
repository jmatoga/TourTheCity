package jkd.tourthecity.service;

import jkd.tourthecity.Exception.UserNotFoundException;
import jkd.tourthecity.builder.AuthenticationRequest;
import jkd.tourthecity.builder.AuthenticationResponse;
import jkd.tourthecity.builder.RegisterRequest;
import jkd.tourthecity.config.JwtService;
import jkd.tourthecity.model.ERole;
import jkd.tourthecity.model.User;
import jkd.tourthecity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                           .orElseThrow(UserNotFoundException::new);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                       .token(jwtToken)
                       .build();
    }
}
