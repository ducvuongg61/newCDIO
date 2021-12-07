package com.example.demo.repository.roleRepository;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
//    List<Role> findAccounts(Account account);
    Set<Role> findByRoleName(String roleName);
}
