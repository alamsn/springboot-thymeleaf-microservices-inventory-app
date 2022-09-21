package com.finaltask.alam.clients.sales.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class Sales {

    private Integer salesId;
    private String productName;
    @Positive(message = "minimal 0")
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private String notes;
    private Integer isDeleted;
    @NotNull(message = "tanggal harus diisi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}