package com.example.bootsecurity.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column
private String role;

@ManyToMany(mappedBy = "roles")
private Set<User> users;

    public String getRole() {
        return role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
