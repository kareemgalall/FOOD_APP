package com.banquemisr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
@Entity
public class Product {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private String price;
    @Column(name="description")
    private String description ;
    @Column(name="is_added_to_cart")
    private Boolean isAddedToCart;
    @Column(name="bought_items_count")
    private Integer boughtItemsCount;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAddedToCart() {
        return isAddedToCart;
    }

    public void setAddedToCart(Boolean addedToCart) {
        isAddedToCart = addedToCart;
    }

    public Integer getBoughtItemsCount() {
        return boughtItemsCount;
    }

    public void setBoughtItemsCount(Integer boughtItemsCount) {
        this.boughtItemsCount = boughtItemsCount;
    }

}
