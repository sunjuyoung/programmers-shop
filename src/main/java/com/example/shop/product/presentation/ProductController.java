package com.example.shop.product.presentation;

import com.example.shop.common.ResponseEntity;
import com.example.shop.product.application.ProductService;
import com.example.shop.product.application.dto.ProductInfo;
import com.example.shop.product.presentation.dto.ProductRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1}/products")
public class ProductController {

    private final ProductService productService;


    @Operation(
        summary = "모든 상품 조회",
        description = "등록된 모든 상품의 정보를 조회합니다."
    )
    @GetMapping
    public ResponseEntity<List<ProductInfo>> findAll(Pageable pageable) {

        return productService.findAll(pageable);
    }

    @Operation(
        summary = "상품 등록",
        description = "새로운 상품을 등록합니다."
    )
    @PostMapping
    public ResponseEntity<ProductInfo> save(@RequestBody ProductRequest request) {

        return productService.create(request.toCommand());
    }

    @Operation(
        summary = "상품 단건 조회",
        description = "특정 상품의 정보를 조회합니다."
    )
    @GetMapping("{id}")
    public ResponseEntity<ProductInfo> findById(@PathVariable String id) {

        return productService.findById(UUID.fromString(id));
    }

    @Operation(
        summary = "상품 수정",
        description = "기존 상품의 정보를 수정합니다."
    )
    @PutMapping("{id}")
    public ResponseEntity<ProductInfo> update(@PathVariable String id, @RequestBody ProductRequest request) {

        return productService.update(UUID.fromString(id), request.toCommand());
    }

    @Operation(
        summary = "상품 삭제",
        description = "기존 상품의 정보를 삭제합니다."
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {

        return productService.deleteById(UUID.fromString(id));
    }

}
