package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ColorProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String datePost1;
    private String description1;
    private String image11;
    private String image21;
    private String image31;
    private String productName1;
    private String statusProduct1;
    private int idCategory1;
    private String idAccount1;

    private String color1;
    private int quantity1;
    private double price1;
    private String statusColor1;

    public ColorProduct() {
    }

    public ColorProduct(int id, String datePost1, String description1, String image11, String image21, String image31, String productName1, String statusProduct1, int idCategory1, String idAccount1, String color1, int quantity1, double price1, String statusColor1) {
        this.id = id;
        this.datePost1 = datePost1;
        this.description1 = description1;
        this.image11 = image11;
        this.image21 = image21;
        this.image31 = image31;
        this.productName1 = productName1;
        this.statusProduct1 = statusProduct1;
        this.idCategory1 = idCategory1;
        this.idAccount1 = idAccount1;
        this.color1 = color1;
        this.quantity1 = quantity1;
        this.price1 = price1;
        this.statusColor1 = statusColor1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatePost1() {
        return datePost1;
    }

    public void setDatePost1(String datePost1) {
        this.datePost1 = datePost1;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getImage11() {
        return image11;
    }

    public void setImage11(String image11) {
        this.image11 = image11;
    }

    public String getImage21() {
        return image21;
    }

    public void setImage21(String image21) {
        this.image21 = image21;
    }

    public String getImage31() {
        return image31;
    }

    public void setImage31(String image31) {
        this.image31 = image31;
    }

    public String getProductName1() {
        return productName1;
    }

    public void setProductName1(String productName1) {
        this.productName1 = productName1;
    }

    public String getStatusProduct1() {
        return statusProduct1;
    }

    public void setStatusProduct1(String statusProduct1) {
        this.statusProduct1 = statusProduct1;
    }

    public int getIdCategory1() {
        return idCategory1;
    }

    public void setIdCategory1(int idCategory1) {
        this.idCategory1 = idCategory1;
    }

    public String getIdAccount1() {
        return idAccount1;
    }

    public void setIdAccount1(String idAccount1) {
        this.idAccount1 = idAccount1;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public int getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(int quantity1) {
        this.quantity1 = quantity1;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public String getStatusColor1() {
        return statusColor1;
    }

    public void setStatusColor1(String statusColor1) {
        this.statusColor1 = statusColor1;
    }
}

