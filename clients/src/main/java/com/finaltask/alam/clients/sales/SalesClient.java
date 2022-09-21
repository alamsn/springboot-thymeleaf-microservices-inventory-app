package com.finaltask.alam.clients.sales;

import com.finaltask.alam.clients.sales.entity.Sales;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@FeignClient(name = "sales-service", path = "/sales")
public interface SalesClient {
    @GetMapping("/list")
    List<Sales> getAllSales();

    @PostMapping("/add")
    void addSales(@Valid @RequestBody Sales sales, Double value);

    @PostMapping("/delete/{salesId}")
    void deleteSales(@PathVariable("salesId") Integer salesId);

    @PostMapping("/update")
    void updateSales(@Valid @RequestBody Sales sales, Double value);

    @GetMapping("/total")
    Double getTotalSales();

    @GetMapping("/edit/{salesId}")
    Optional<Sales> getSalesById(@PathVariable("salesId") Integer salesId);

    @GetMapping("/topselling")
    List<String> getTopSellingProduct();

    @GetMapping("/productandquantity")
    List<Object> getSalesProductAndQuantity();

    @GetMapping("/quantityofproduct")
    Integer getQuantityByProductName(String productName);

    @GetMapping("/{salesId}")
    Integer getQuantityById(@PathVariable("salesId") Integer salesId);
}
