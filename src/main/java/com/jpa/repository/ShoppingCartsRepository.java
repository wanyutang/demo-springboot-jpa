package com.jpa.repository;

import com.jpa.entity.ShoppingCarts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartsRepository extends JpaRepository<ShoppingCarts, Integer> {
}