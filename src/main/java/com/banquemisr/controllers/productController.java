package com.banquemisr.controllers;

import com.banquemisr.DTO.ProductDTO;
import com.banquemisr.entity.Product;
import com.banquemisr.service.ProductImplService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class productController {
    @Autowired
    ProductImplService productImplService;
    @Autowired
    ModelMapper modelMapper;
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO productDTO)
    {
        Product product=productImplService.addProduct(productDTO);
        ProductDTO productDTORespone=modelMapper.map(product,ProductDTO.class);
        return ResponseEntity.ok().body(productDTORespone);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productImplService.deleteProduct(id);
    }
    @GetMapping("/getAll")
    public List<ProductDTO> getAllProducts()
    {
        return productImplService.getProducts().stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
