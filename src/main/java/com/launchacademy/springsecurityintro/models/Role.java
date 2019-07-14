package com.launchacademy.springsecurityintro.models;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
  @Id
  @SequenceGenerator(name="role_generator",
      sequenceName="roles_id_seq")
  @GeneratedValue(strategy= GenerationType.SEQUENCE,
      generator="role_generator")
  @Column(name="id", nullable=false, unique=true)
  private Integer id;

  private String name;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users;
}
