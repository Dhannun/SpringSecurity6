package click.touchit.security.auth;

import click.touchit.security.dto.request.AuthenticationRequest;
import click.touchit.security.dto.request.RegisterRequest;
import click.touchit.security.dto.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  public AuthenticationResponse register(RegisterRequest request) {
    return null;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    return null;
  }
}
