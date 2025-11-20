package com.example.shop.product.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(UUID productId);

    Product save(Product product);

    void deleteById(UUID productId);


}
