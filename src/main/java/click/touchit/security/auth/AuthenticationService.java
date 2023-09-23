package click.touchit.security.auth;

import click.touchit.security.config.JwtService;
import click.touchit.security.dto.request.AuthenticationRequest;
import click.touchit.security.dto.request.RegisterRequest;
import click.touchit.security.dto.response.AuthenticationResponse;
import click.touchit.security.dto.response.Login;
import click.touchit.security.user.Role;
import click.touchit.security.user.User;
import click.touchit.security.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(@NotNull RegisterRequest request) {


    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();

    userRepository.save(user);

    var jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public Login authenticate(AuthenticationRequest request) {


    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("USER_NOT_FOUND"));

    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);


    return Login.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  public Login refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) {
    final String authHeader = request.getHeader(AUTHORIZATION);
    final String refreshToken;
    final String userEmail;

    if (authHeader == null || !authHeader.startsWith("Bearer ")){
      response.setStatus(BAD_REQUEST.value());
//      return Login.builder()
//          .token("Invalid refresh token")
//          .build();
//      TODO: throw the right exception here
    }

    assert authHeader != null;

    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken); // todo: extract user email from refresh token

    if (userEmail != null) {

      var appUser = this.userRepository.findByEmail(userEmail).orElseThrow(()->new UsernameNotFoundException("User not found"));

      if (jwtService.isTokenValid(refreshToken, appUser)) {
        var newAccessToken = jwtService.generateToken(appUser);

//        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);

        return Login.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();
      }
    }
    throw new IllegalStateException("Invalid refresh token");
  }
}
