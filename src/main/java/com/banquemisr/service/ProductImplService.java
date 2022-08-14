package com.banquemisr.service;

import com.banquemisr.DTO.ProductDTO;
import com.banquemisr.entity.Product;
import com.banquemisr.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductImplService implements IProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product product=modelMapper.map(productDTO,Product.class);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product=productRepository.getById(productId);
        productRepository.delete(product);
    }

}
