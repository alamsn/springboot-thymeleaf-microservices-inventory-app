package com.finaltask.alam.sales.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "sales")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id", nullable = false)
    private Integer salesId;

    @Column(name = "product_name", length = 128, nullable = false)
    private String productName;

    @Positive(message = "minimal 0")
    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "notes", length = 512)
    private String notes;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    @NotNull(message = "tanggal harus diisi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private Date date;

}