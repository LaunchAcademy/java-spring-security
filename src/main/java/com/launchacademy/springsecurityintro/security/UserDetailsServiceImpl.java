package com.launchacademy.springsecurityintro.security;

import com.launchacademy.springsecurityintro.models.Role;
import com.launchacademy.springsecurityintro.models.User;
import com.launchacademy.springsecurityintro.repositories.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    for (Role role : user.getRoles()) {
      grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"  + role.getName().toUpperCase()));
    }

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), grantedAuthorities);
  }
}
