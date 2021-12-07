package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class DiscountStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStatus")
    private int idStatus;
    private String nameStatus;

    @OneToMany(mappedBy = "discountStatus")
    private Set<DiscountCode> discountCodes;

    public DiscountStatus() {
    }

    public DiscountStatus(int idStatus, String nameStatus, Set<DiscountCode> discountCodes) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
        this.discountCodes = discountCodes;
    }

    public DiscountStatus(int idStatus, String nameStatus) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }

    public DiscountStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public Set<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

    public void setDiscountCodes(Set<DiscountCode> discountCodes) {
        this.discountCodes = discountCodes;
    }

    public Set<DiscountCode> getCodeAvailableSet() {
        return discountCodes;
    }

    public void setCodeAvailableSet(Set<DiscountCode> codeAvailableSet) {
        this.discountCodes = codeAvailableSet;
    }
}
