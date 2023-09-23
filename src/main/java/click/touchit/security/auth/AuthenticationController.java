package click.touchit.security.auth;

import click.touchit.security.dto.request.AuthenticationRequest;
import click.touchit.security.dto.request.RegisterRequest;
import click.touchit.security.dto.response.AuthenticationResponse;
import click.touchit.security.dto.response.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ){
    return ResponseEntity.ok(authenticationService.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<Login> login(
      @RequestBody AuthenticationRequest request
  ){
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }

  @PostMapping("refresh_token")
  public ResponseEntity<Login> refreshToken(HttpServletRequest request, HttpServletResponse response) {
    return ResponseEntity.ok(authenticationService.refreshToken(request, response));
  }
}
