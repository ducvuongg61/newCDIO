package com.example.demo.service.roleService;

import com.example.demo.model.Role;
import com.example.demo.repository.roleRepository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByIdRole(int idRole) {
        return roleRepository.findById(idRole).orElse(null);
    }

    @Override
    public Set<Role> findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }


    @Override
    public void delete(int idRole) {
        roleRepository.deleteById(idRole);
    }
}
