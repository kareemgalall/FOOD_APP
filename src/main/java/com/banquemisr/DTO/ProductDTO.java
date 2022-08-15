package com.banquemisr.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String price;
    private String description ;
    private Boolean isAddedToCart;
    private Integer boughtItemsCount;
    private String category;
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
