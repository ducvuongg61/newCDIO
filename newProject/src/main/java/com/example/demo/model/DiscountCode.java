package com.example.demo.model;

import javax.persistence.*;

@Entity
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCode")
    private int idDiscountCode;
    private Integer code;

    @ManyToOne(targetEntity = Discount.class)
    @JoinColumn(name = "idDiscount")
    private Discount discount;

    @ManyToOne(targetEntity = DiscountStatus.class)
    @JoinColumn(name = "idStatus")
    private DiscountStatus discountStatus;

    public DiscountCode() {
    }

    public DiscountCode(int idDiscountCode, Discount discount, DiscountStatus discountStatus) {
        this.idDiscountCode = idDiscountCode;
        this.discount = discount;
        this.discountStatus = discountStatus;
    }

    public int getIdDiscountCode() {
        return idDiscountCode;
    }

    public void setIdDiscountCode(int idDiscountCode) {
        this.idDiscountCode = idDiscountCode;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public DiscountStatus getDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(DiscountStatus discountStatus) {
        this.discountStatus = discountStatus;
    }
}
