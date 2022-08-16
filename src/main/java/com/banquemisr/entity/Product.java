package com.banquemisr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
@Data
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
    @Column(name="category")
    private String category;
}
