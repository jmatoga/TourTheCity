package jkd.tourthecity.service;

import jkd.tourthecity.builder.AuthenticationRequest;
import jkd.tourthecity.builder.AuthenticationResponse;
import jkd.tourthecity.builder.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
