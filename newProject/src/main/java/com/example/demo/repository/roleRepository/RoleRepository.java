package com.example.demo.repository.roleRepository;

import com.example.demo.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
//    List<Role> findAccounts(Account account);
    Set<Role> findByRoleName(String roleName);

    @Query(value = "select * from `role` r " +
            "inner join account_role ar on r.id_role=ar.id_role " +
            "inner join `account` a on ar.id_account=a.id_account " +
            "inner join acc_user au on au.`account`=a.id_account " +
            "inner join address aress on aress.id_user=au.id_user ;",nativeQuery = true)
    Page<Role> findByAlAndAccountRoleAndAccountAndAccUserAndAddress(Pageable pageable);
}
