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

@RestController
@RequestMapping("product")
public class productController {
    @Autowired
    ProductImplService productImplService;
    @Autowired
    ModelMapper modelMapper;

    @PreAuthorize("permitAll())")
    @CrossOrigin(origins =  "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO productDTO)
    {
        Product product=productImplService.addProduct(productDTO);
        ProductDTO productDtoRespone=modelMapper.map(product,ProductDTO.class);
        return ResponseEntity.ok().body(productDtoRespone);
    }

    @Transactional
    @CrossOrigin(origins =  "http://localhost:4200")
    @PreAuthorize("permitAll()")
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id)
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
    @CrossOrigin(origins =  "http://localhost:4200")
    @GetMapping("/getAll")
    public List<ProductDTO> getAllProducts()
    {
        return productImplService.getProducts().stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @PreAuthorize("permitAll()")
    @CrossOrigin(origins =  "http://localhost:4200")
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
