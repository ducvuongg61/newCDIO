package com.example.demo.service.accountService;

import com.example.demo.model.Account;
import com.example.demo.repository.accountRepository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findById(String idAccount) {
        return accountRepository.findById(idAccount).orElse(null);
    }

    @Override
    public String findByPassword(String password) {
        return accountRepository.findByPassword(password);
    }
}
