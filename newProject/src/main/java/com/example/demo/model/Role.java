package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRole")
    private int idRole;
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts;

//    @ManyToMany()
//    @JoinTable(name = "accountRole" ,
//            joinColumns = @JoinColumn(name = "idRole") ,
//            inverseJoinColumns = @JoinColumn(name = "idAccount"))
//    private Set<Account> accounts;

    public Role() {
    }

    public Role(int idRole, String roleName, Set<Account> accounts) {
        this.idRole = idRole;
        this.roleName = roleName;
        this.accounts = accounts;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
