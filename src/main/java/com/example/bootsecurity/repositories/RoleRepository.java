package com.example.bootsecurity.repositories;

import com.example.bootsecurity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
Role findRoleByRole(String nameRole);
Role findRoleById(Long id);
}
