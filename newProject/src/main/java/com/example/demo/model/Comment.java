package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComment")
    private int idComment;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "idProduct", referencedColumnName = "idProduct")
    private Product product;

    @Column(length = 2000)
    private String Content;

    @ManyToMany(mappedBy = "comments", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AccUser> userSet;

    public Comment() {
    }

    public Comment(int idComment, Product product, String content, Set<AccUser> userSet) {
        this.idComment = idComment;
        this.product = product;
        Content = content;
        this.userSet = userSet;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Set<AccUser> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<AccUser> userSet) {
        this.userSet = userSet;
    }
}
