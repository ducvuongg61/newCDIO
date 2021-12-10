package com.example.demo.repository.accountRepository;

import com.example.demo.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByIdAccount(String account);

    @Query(value = "select a.password from Account a where a.idAccount =: idAccount")
    String findByPassword(@Param("idAccount") String idAccount);

}
