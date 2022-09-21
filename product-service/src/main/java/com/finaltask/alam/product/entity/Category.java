package com.finaltask.alam.product.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Size(min = 3, max = 5, message = "minimal 3 huruf dan maksimal 5 huruf")
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotBlank(message = "nama kategori tidak boleh kosong")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_deleted")
    private Integer isDeleted;

}