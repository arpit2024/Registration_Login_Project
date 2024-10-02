package org.example.registration_login.repository;

import org.example.registration_login.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    Role save(Role role);
}
