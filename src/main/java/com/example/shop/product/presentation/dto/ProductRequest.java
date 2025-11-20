package com.example.shop.product.presentation.dto;

import com.example.shop.product.application.dto.ProductCommand;
import com.example.shop.product.application.dto.ProductInfo;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String status
) {

    public ProductCommand toCommand() {
        return new ProductCommand(name, description, price, stock, status);
    }
}
