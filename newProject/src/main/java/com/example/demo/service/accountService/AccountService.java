package com.example.demo.service.accountService;

import com.example.demo.model.Account;

import java.util.List;

public interface AccountService {
    List<Account>  findAll();

    void save(Account account);

    Account findById(String idAccount);

    String findByPassword(String password);
}
