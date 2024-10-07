package com.finalProject.Sneakz.repository;

import com.finalProject.Sneakz.entity.Cart;
import com.finalProject.Sneakz.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
}