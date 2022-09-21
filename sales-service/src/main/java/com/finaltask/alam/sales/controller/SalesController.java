package com.finaltask.alam.sales.controller;

import com.finaltask.alam.sales.entity.Sales;
import com.finaltask.alam.sales.service.SalesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController @AllArgsConstructor
@RequestMapping("/sales")
public class SalesController {

    private SalesService salesService;

    @GetMapping("/list")
    public List<Sales> getAllSales() {
        List<Sales> allSales = salesService.getAllSales();
        log.info("total sales: {}", allSales.size());
        return allSales;
    }

    @PostMapping("/add")
    public void addSales(@Valid @RequestBody Sales sales, Double value) {
        salesService.addSales(sales, value);
        log.info("sales ditambahkan: {}", sales);
    }

    @PostMapping("/delete/{salesId}")
    public void deleteSales(@PathVariable("salesId") Integer salesId) {
        salesService.deleteSales(salesId);
        log.info("Sales dengan id {} terhapus", salesId);
    }

    @PostMapping("/update")
    public void updateSales(@Valid @RequestBody Sales sales, Double value) {
        salesService.updateSales(sales, value);
        log.info("Sales diupdate: {}", sales);
    }

    @GetMapping("/total")
    public Double getTotalSales() {
        return salesService.getTotalSales();
    }

    @GetMapping("/edit/{salesId}")
    public Optional<Sales> getSalesById(@PathVariable("salesId") Integer salesId) {
        return salesService.getSalesById(salesId);
    }

    @GetMapping("/topselling")
    public List<String> getTopSellingProduct() {
        return salesService.getTopSellingProduct();
    }

    @GetMapping("/productandquantity")
    public List<Object> getSalesProductAndQuantity() {
        return salesService.getSalesProductAndQuantity();
    }

    @GetMapping("/quantityofproduct")
    public Integer getQuantityByProductName(String productName) {
        return salesService.getQuantityByProductName(productName);
    }

    @GetMapping("/{salesId}")
    public Integer getQuantityById(@PathVariable("salesId") Integer salesId) {
        return salesService.getQuantityById(salesId);
    }
}
