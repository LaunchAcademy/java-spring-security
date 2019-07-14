package com.launchacademy.springsecurityintro.models;


import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

  @Id
  @SequenceGenerator(name = "user_generator",
      sequenceName = "users_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "user_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @Column(name="username", unique = true)
  private String username;

  private String password;

  @ManyToMany
  @JoinTable(name="users_roles",
    joinColumns=
    @JoinColumn(name="user_id", referencedColumnName="id"),
    inverseJoinColumns=
    @JoinColumn(name="role_id", referencedColumnName="id")
  )
  private Set<Role> roles;

}
