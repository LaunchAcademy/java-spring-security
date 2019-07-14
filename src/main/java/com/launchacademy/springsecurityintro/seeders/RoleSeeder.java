package com.launchacademy.springsecurityintro.seeders;

import com.launchacademy.springsecurityintro.models.Role;
import com.launchacademy.springsecurityintro.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {
  private final RoleRepository roleRepository;

  @Autowired
  public RoleSeeder(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    final String[] roles = {"user", "admin"};
    for(String roleName : roles) {
      if(roleRepository.findByName(roleName) == null) {
        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
      }
    }
  }
}
