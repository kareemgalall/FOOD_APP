package com.banquemisr.service;

import com.banquemisr.DTO.ProductDTO;
import com.banquemisr.entity.Product;

import java.util.List;

public interface IProductService {
    public Product addProduct(ProductDTO productDTO) ;
    public List<Product> getProducts();
    public Boolean deleteProduct(Long productId);
}
