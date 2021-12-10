package com.example.demo.repository.UserRepository;

import com.example.demo.model.AccUser;
import com.example.demo.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<AccUser, Integer> {
    List<AccUser> findByGmail(String email);

    AccUser findByAccount_IdAccount(String idAccount);


    Page<AccUser> findByName(String userName, Pageable pageable);

}
