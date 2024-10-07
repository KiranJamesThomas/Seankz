package com.finalProject.Sneakz.controller;

import com.finalProject.Sneakz.entity.Brand;
import com.finalProject.Sneakz.entity.Product;
import com.finalProject.Sneakz.service.BrandService;
import com.finalProject.Sneakz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Sneakz")
public class ProductController {
    @Autowired
    public ProductService productService;
    @Autowired
    public BrandService brandService;

    @PostMapping("/Products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        String name = product.getBrand().getBrandName();
        if(!brandService.brandExists(name)){
            Brand brand = brandService.create(product.getBrand());
        }else{
           product.setBrand(brandService.brandByName(name));
        }
        Product productCreated  = productService.create(product);
        return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
    }

    @GetMapping("/Products")
    public ResponseEntity<List<Product>> findAllProducts(){
        List<Product> Products = productService.findAll();
        return new ResponseEntity<>(Products,HttpStatus.OK);

    }

    @GetMapping("/Products/{id}")
    public ResponseEntity<Product> productById(@PathVariable long id){
        Optional<Product> optionalProduct = productService.findById(id);
        if(optionalProduct.isPresent()){
            return new ResponseEntity<>(optionalProduct.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<List<Product>> productByName(@PathVariable String name){
        List<Product> products = productService.findByName(name);
        if(!products.isEmpty()){
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/Products/{id}")
    public ResponseEntity<Product> updateProducts(@PathVariable long id, @RequestBody Product product){
        Optional<Product> optionalProduct = productService.findById(id);
        Product ProductToUpdate = optionalProduct.get();
        ProductToUpdate.setPrice(product.getPrice());
        Product updatedProduct = productService.update(ProductToUpdate);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

    @DeleteMapping("/Products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
