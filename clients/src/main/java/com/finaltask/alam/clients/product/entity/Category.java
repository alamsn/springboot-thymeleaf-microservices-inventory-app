package com.finaltask.alam.clients.product.entity;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Category {
    private Integer categoryId;
    @Size(min = 3, max = 5, message = "minimal 3 huruf dan maksimal 5 huruf")
    private String code;
    @NotBlank(message = "nama tidak boleh kosong")
    private String name;
    private Integer isDeleted;
}