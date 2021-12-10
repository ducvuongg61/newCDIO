package com.example.demo.service.userService;

import com.example.demo.model.AccUser;
import com.example.demo.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void save(AccUser user);

    Page<AccUser> findByAlAndAccountRoleAndAccountAndAccUserAndAddress(Pageable pageable);

    AccUser findById(int id);

    void delete(int id);

    AccUser findByAccount(String idAccount);

    List<AccUser> findByEmail(String email);

    Page<AccUser> findByName(String nameUser, Pageable pageable);
}
