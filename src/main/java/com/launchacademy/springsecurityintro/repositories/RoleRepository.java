package com.launchacademy.springsecurityintro.repositories;

import com.launchacademy.springsecurityintro.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
  Role findByName(String name);
}
