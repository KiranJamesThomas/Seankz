package com.finalProject.Sneakz.controller;

import com.finalProject.Sneakz.entity.Brand;
import com.finalProject.Sneakz.entity.Image;
import com.finalProject.Sneakz.entity.Product;
import com.finalProject.Sneakz.service.BrandService;
import com.finalProject.Sneakz.service.ImageService;
import com.finalProject.Sneakz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Sneakz")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @PostMapping("/image")
    public ResponseEntity<Image> createImage(@RequestBody Image image){
        long p_id= image.getProduct().getId();
        Product product = productService.findById(p_id).orElseThrow();
        image.setProduct(product);
        Image _image  = imageService.create(image);
        return new ResponseEntity<>(_image, HttpStatus.CREATED);
    }

    @GetMapping("/image")
    public ResponseEntity<List<Image>> getAllImages(){
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<Image> getImagesById(@PathVariable long id){
        return new ResponseEntity<>(imageService.getImageById(id), HttpStatus.OK);
    }

    @GetMapping("/image/{product_id}")
    public ResponseEntity<List<Image>> getImagesByProductId(@PathVariable long product_id){
        return new ResponseEntity<>(imageService.findAllByProduct(product_id), HttpStatus.OK);
    }
}
