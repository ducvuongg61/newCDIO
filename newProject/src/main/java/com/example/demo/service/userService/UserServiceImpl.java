package com.example.demo.service.userService;

import com.example.demo.model.AccUser;
import com.example.demo.model.Role;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.repository.accountRepository.AccountRepository;
import com.example.demo.repository.roleRepository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public void save(AccUser user) {
        userRepository.save(user);
    }

    @Override
    public Page<AccUser> findByAlAndAccountRoleAndAccountAndAccUserAndAddress(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public AccUser findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public AccUser findByAccount(String idAccount) {
        return userRepository.findByAccount_IdAccount(idAccount);
    }

    @Override
    public List<AccUser> findByEmail(String email) {
        return userRepository.findByGmail(email);
    }

    @Override
    public Page<AccUser> findByName(String nameUser, Pageable pageable) {
        return userRepository.findByName(nameUser , pageable);
    }
}
