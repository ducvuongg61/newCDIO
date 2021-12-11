package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    @Id
    @Column(name = "idAccount")
    private String idAccount;
    private String password;
    private String rePassword;
    private boolean status;

//     @ManyToMany(mappedBy = "accounts",fetch = FetchType.EAGER)
//    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "accountRole" ,
            joinColumns = @JoinColumn(name = "idAccount") ,
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "account")
    private AccUser user;

    @OneToMany(mappedBy = "accounts")
    private Set<Product> products;

    @OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
    private Set<Discount> discounts;

    @OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
    private Set<Color> colors;

    public Account() {
    }

    public Account(String idAccount, String password, boolean status, Set<Role> roles) {
        this.idAccount = idAccount;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public Account(String idAccount) {
        this.idAccount = idAccount;
    }

    public Account(String idAccount, String password, String rePassword, boolean status, Set<Role> roles, AccUser user, Set<Product> products, Set<Discount> discounts, Set<Color> colors) {
        this.idAccount = idAccount;
        this.password = password;
        this.rePassword = rePassword;
        this.status = status;
        this.roles = roles;
        this.user = user;
        this.products = products;
        this.discounts = discounts;
        this.colors = colors;
    }

    public Account(String idAccount, String password, Set<Role> roles, boolean status) {
        this.idAccount = idAccount;
        this.password = password;
        this.roles = roles;
        this.status = status;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public AccUser getUser() {
        return user;
    }

    public void setUser(AccUser user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }
}
