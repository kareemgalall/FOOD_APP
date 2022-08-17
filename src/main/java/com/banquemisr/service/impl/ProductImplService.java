package com.banquemisr.service.impl;

import com.banquemisr.DTO.ProductDTO;
import com.banquemisr.entity.Product;
import com.banquemisr.repository.ProductRepository;
import com.banquemisr.service.IProductService;
import com.banquemisr.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImplService implements IProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product product=modelMapper.map(productDTO,Product.class);
        return productRepository.save(product);
    }
    public Optional<Product> getProduct(Long id)
    {
        try
        {
            Optional<Product>product=Optional.of(productRepository.getById(id));
            return product;
        }
        catch (Exception e)
        {
            return Optional.empty();
        }
    }
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        try
        {
            Optional<Product>product=Optional.of(productRepository.getById(productId));
            if(product.isPresent()) {
                productRepository.delete(product.get());
            }
            return product.isPresent();
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
