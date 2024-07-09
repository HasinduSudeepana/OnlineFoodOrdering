package com.Hasindu.Online.Food.Ordering.repository;

import com.Hasindu.Online.Food.Ordering.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart , Long> {
}
