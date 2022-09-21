package com.finaltask.alam.product.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "product")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @NotBlank(message = "nama tidak boleh kosong")
    @Column(name = "name", nullable = false)
    private String name;

    @Positive(message = "minimal 0")
    @Column(name = "price", nullable = false)
    private Double price;

    @Positive(message = "minimal 0")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "is_deleted", nullable = false)
    private Integer isDeleted;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId;

}