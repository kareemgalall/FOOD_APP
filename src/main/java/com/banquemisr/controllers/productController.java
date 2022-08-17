package com.banquemisr.controllers;

import com.banquemisr.DTO.ProductDTO;
import com.banquemisr.entity.Product;
import com.banquemisr.service.impl.ProductImplService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("product")
public class productController {
    @Autowired
    ProductImplService productImplService;
    @Autowired
    ModelMapper modelMapper;

    @PreAuthorize("permitAll())")
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO productDTO)
    {
        Product product=productImplService.addProduct(productDTO);
        ProductDTO productDtoRespone=modelMapper.map(product,ProductDTO.class);
        return ResponseEntity.ok().body(productDtoRespone);
    }

    @Transactional
    @PreAuthorize("permitAll()")
    @GetMapping("/getById")
    public ResponseEntity<?> getProductById(@RequestBody Long id)
    {
        Optional<Product>product=productImplService.getProduct(id);
        if(product.isPresent())
        {
            ProductDTO productDTO=modelMapper.map(product.get(),ProductDTO.class);
            return ResponseEntity.ok().body(productDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/getAll")
    public List<ProductDTO> getAllProducts()
    {
        return productImplService.getProducts().stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @PreAuthorize("permitAll()")

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id)
    {
        if(productImplService.deleteProduct(id))
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
