package com.example.twitter.security;

import com.example.twitter.dto.UserDto;
import com.example.twitter.entity.Client;
import com.example.twitter.mapper.UserMapper;
import com.example.twitter.repository.ClientRepository;
import com.example.twitter.security.dto1.SignupRequest;
import com.example.twitter.user.RoleRepository;
import com.example.twitter.user.UserRepository;
import com.example.twitter.user.model.ERole;
import com.example.twitter.user.model.Role;
import com.example.twitter.user.model.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authenticationManager;
  private final ClientRepository clientRepository;

  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public void register(@Valid SignupRequest signUpRequest) {
    User user = User.builder()
        .username(signUpRequest.getUsername())
        .password(encoder.encode(signUpRequest.getPassword()))
        .email(signUpRequest.getEmail())
        .build();

    String roleStr = signUpRequest.getRole();
    Role defaultRole = new Role();
    if (roleStr == null) {
      defaultRole = roleRepository.findByName(ERole.CUSTOMER)
          .orElseThrow(() -> new EntityNotFoundException("Cannot find CUSTOMER role"));
      user.setRole(defaultRole);
    }else{
      user.setRole(roleRepository.findByName(ERole.ADMIN).get());
    }

    User user1 = userRepository.save(user);
    Client client = Client.builder()
            .user(user1)
            .bio(new String(""))
            .followers(0)
            .accountsFollowed(0)
            .followersClient(new ArrayList<>())
            .followingAccounts(new ArrayList<>())
            .posts(new ArrayList<>())
            .build();
    clientRepository.save(client);
  }
  @Transactional
  public UserDto viewUserPrivacy(Long id){
    return userMapper.UserToDto(userRepository.findById(id).get());
  }
  public Authentication authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
    return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
  }
}