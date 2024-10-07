package com.finalProject.Sneakz.service;

import com.finalProject.Sneakz.entity.Brand;
import com.finalProject.Sneakz.entity.Image;
import com.finalProject.Sneakz.repository.BrandRepository;
import com.finalProject.Sneakz.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image create(Image image){
        return imageRepository.save(image);
    }

    public List<Image> findAll(){
        return new ArrayList<>(imageRepository.findAll());
    }


    public Image getImageById(long id){
        return imageRepository.findById(id).orElseThrow();
    }

    public List<Image> findAllByProduct(long id){
        return new ArrayList<>(imageRepository.findByProductId(id));
    }
}
