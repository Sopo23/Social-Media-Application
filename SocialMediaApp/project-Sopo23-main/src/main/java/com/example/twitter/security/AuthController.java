package com.example.twitter.security;

import com.example.twitter.dto.ClientDTO;
import com.example.twitter.dto.UserDto;
import com.example.twitter.security.dto1.JwtResponse;
import com.example.twitter.security.dto1.LoginRequest;
import com.example.twitter.security.dto1.MessageResponse;
import com.example.twitter.security.dto1.SignupRequest;
import com.example.twitter.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.twitter.user.UrlMapping.*;


@RequestMapping(AUTH)
@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final JwtUtilsService jwtUtilsService;

  @PostMapping(SIGN_IN)
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authService.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtilsService.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    return ResponseEntity.ok(
        JwtResponse.builder()
            .token(jwt)
            .id(userDetails.getId())
            .username(userDetails.getUsername())
            .email(userDetails.getEmail())
            .roles(roles)
            .build());
  }

  @PostMapping(SIGN_UP)
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (authService.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (authService.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    authService.register(signUpRequest);


    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  @GetMapping(VIEW_PRIVACY)
  public ResponseEntity<UserDto> getPrivacyData(@PathVariable Long id){
    return ResponseEntity.ok(authService.viewUserPrivacy(id));
  }
  //de testat ceva nu merge 403

}