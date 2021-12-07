package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBill")
    private int idBill;
    private String current;
    private double totalCost;
    private String status;

    @ManyToOne(targetEntity = AccUser.class)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private AccUser user;

    @OneToMany(mappedBy = "bill")
    private Set<ProductBill> productBills;

    public Bill() {
    }


    public Bill(int idBill, String current, double totalCost, String status, AccUser user, Set<ProductBill> productBills) {
        this.idBill = idBill;
        this.current = current;
        this.totalCost = totalCost;
        this.status = status;
        this.user = user;
        this.productBills = productBills;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AccUser getUser() {
        return user;
    }

    public void setUser(AccUser user) {
        this.user = user;
    }

    public Set<ProductBill> getProductBills() {
        return productBills;
    }

    public void setProductBills(Set<ProductBill> productBills) {
        this.productBills = productBills;
    }
}
