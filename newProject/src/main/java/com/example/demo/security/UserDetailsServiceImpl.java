package com.example.demo.security;

import com.example.demo.model.Account;
import com.example.demo.model.Role;
import com.example.demo.repository.accountRepository.AccountRepository;
import com.example.demo.repository.roleRepository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public UserDetails loadUserByUsername(String idAccount) throws UsernameNotFoundException {
        Account account = this.accountRepo.findByIdAccount(idAccount);

        if (account == null) {
            System.out.println("User not found! " + idAccount);
            throw new UsernameNotFoundException("User " + idAccount + " was not found in the database");
        }
        System.out.println("Found user:! " + account);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = account.getRoles();
        for (Role role : roles) {
            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthorities.add(authority);
        }

        UserDetails userDetails = (UserDetails)  new User(account.getIdAccount(), account.getPassword(), grantedAuthorities);
        return userDetails;
    }
}
