package com.example.demo.service.roleService;

import com.example.demo.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    Role findByIdRole(int idRole);

    Set<Role> findByRoleName(String roleName);

    void delete(int idRole);
}
