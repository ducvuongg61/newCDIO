package com.example.demo.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDiscount")
    private int idDiscount;
    private String nameDiscount;
    private int quantity;
    private String description;
    private String datePost;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "account" , referencedColumnName = "idAccount")
    private Account accounts;

    @OneToMany(mappedBy = "discount" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Set<DiscountCode> discountCodes = new LinkedHashSet<>();

    public Discount() {
    }

    public Discount(int idDiscount, String nameDiscount, int quantity, String description, String datePost, Account accounts, Set<DiscountCode> discountCodes) {
        this.idDiscount = idDiscount;
        this.nameDiscount = nameDiscount;
        this.quantity = quantity;
        this.description = description;
        this.datePost = datePost;
        this.accounts = accounts;
        this.discountCodes = discountCodes;
    }

    public void generateCode(DiscountCode code) {
        discountCodes.add(code);
    }

    public void pickDiscount() {
        if (quantity > 0) quantity--;
    }

    public int getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public String getNameDiscount() {
        return nameDiscount;
    }

    public void setNameDiscount(String nameDiscount) {
        this.nameDiscount = nameDiscount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public Account getAccounts() {
        return accounts;
    }

    public void setAccounts(Account accounts) {
        this.accounts = accounts;
    }

    public Set<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

    public void setDiscountCodes(Set<DiscountCode> discountCodes) {
        this.discountCodes = discountCodes;
    }
}
