package com.example.shop.product.domain;

import com.example.shop.product.application.dto.ProductCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "product", schema = "public")
@NoArgsConstructor
public class Product {

    @Id
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "reg_id", nullable = false)
    private UUID regId;

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "modify_id", nullable = false)
    private UUID modifyId;

    @Column(name = "modify_dt", nullable = false)
    private LocalDateTime modifyDt;


    private Product(UUID id,
                    String name,
                    String description,
                    BigDecimal price,
                    Integer stock,
                    String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }

    public static Product create(ProductCommand command) {
        var creatorId = UUID.randomUUID();
        Product product = new Product(
                creatorId,
                command.name(),
                command.description(),
                command.price(),
                command.stock(),
                command.status()

        );
        product.regId = creatorId;
        product.modifyId = creatorId;
        return product;
    }

    public void update(ProductCommand command, UUID id) {
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();
        this.stock = command.stock();
        this.status = command.status();
        this.modifyId = id;
    }



    @PrePersist
    public void onCreate() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (regId == null) {
            regId = id;
        }
        if (modifyId == null) {
            modifyId = regId;
        }
        if (regDt == null) {
            regDt = LocalDateTime.now();
        }
        if (modifyDt == null) {
            modifyDt = regDt;
        }
        if (status == null) {
            status = "ACTIVE";
        }
        if (stock == null) {
            stock = 0;
        }
    }

    @PreUpdate
    public void onUpdate() {
        modifyDt = LocalDateTime.now();
        if (modifyId == null) {
            modifyId = id;
        }
    }
}
