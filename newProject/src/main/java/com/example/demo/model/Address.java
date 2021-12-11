package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAddress;
    private String nameAddress;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idUser")
    private AccUser accUser;

    public Address() {
    }

    public Address(String nameAddress, AccUser accUser) {
        this.nameAddress = nameAddress;
        this.accUser = accUser;
    }

    public Address(int idAddress, String nameAddress, AccUser accUser) {
        this.idAddress = idAddress;
        this.nameAddress = nameAddress;
        this.accUser = accUser;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getNameAddress() {
        return nameAddress;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }

    public AccUser getAccUser() {
        return accUser;
    }

    public void setAccUser(AccUser accUser) {
        this.accUser = accUser;
    }
}
