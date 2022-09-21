package com.finaltask.alam.clients.product.entity;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class Product {
    private Integer productId;
    @NotBlank(message = "nama tidak boleh kosong")
    private String name;
    @Positive(message = "minimal 0")
    private Double price;
    @Positive(message = "minimal 0")
    private Integer stock;
    private Integer isDeleted;
    private Category categoryId;
}