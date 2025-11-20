package com.example.shop.product.application;

import com.example.shop.common.ResponseEntity;
import com.example.shop.product.application.dto.ProductCommand;
import com.example.shop.product.application.dto.ProductInfo;
import com.example.shop.product.domain.Product;
import com.example.shop.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ResponseEntity<List<ProductInfo>> findAll(Pageable pageable) {

        Page<Product> page = productRepository.findAll(pageable);

        List<ProductInfo> list = page.stream().map(ProductInfo::from)
                .toList();

        return new ResponseEntity<>(HttpStatus.OK.value(), list, (int) page.getTotalElements());
    }

    public ResponseEntity<ProductInfo> create(ProductCommand command){

        Product product = Product.create(command);

        Product save = productRepository.save(product);

        ProductInfo productInfo = ProductInfo.from(save);

        return new ResponseEntity<>(HttpStatus.CREATED.value(),productInfo,1);
    }

    public ResponseEntity<ProductInfo> update(UUID productId, ProductCommand command){

        Product product = productRepository.findById(productId).orElseThrow();

        product.update(command,productId);

        Product save = productRepository.save(product);

        ProductInfo productInfo = ProductInfo.from(save);

        return new ResponseEntity<>(HttpStatus.OK.value(), productInfo,1);
    }

    public ResponseEntity<ProductInfo> findById(UUID productId){

        Product product = productRepository.findById(productId).orElseThrow();

        ProductInfo productInfo = ProductInfo.from(product);

        return new ResponseEntity<>(HttpStatus.OK.value(),productInfo,1);
    }


    public ResponseEntity<?> deleteById(UUID productId){

        productRepository.deleteById(productId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT.value(),productId, 0 );
    }

}
