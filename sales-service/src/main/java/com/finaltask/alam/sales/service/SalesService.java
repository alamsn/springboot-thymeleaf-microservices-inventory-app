package com.finaltask.alam.sales.service;

import com.finaltask.alam.sales.repository.SalesRepository;
import com.finaltask.alam.sales.entity.Sales;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SalesService {

    private SalesRepository salesRepository;

    public List<Sales> getAllSales() {
        return salesRepository.findByIsDeleted();
    }

    public void addSales(Sales sales, Double value) {
        Sales newSales = new Sales();
        changeSales(sales, newSales, value);
    }

    public void deleteSales(Integer salesId) {
        Sales deletedSales = salesRepository.getReferenceById(salesId);
        deletedSales.setIsDeleted(1);
        salesRepository.save(deletedSales);
    }

    public void updateSales(Sales sales, Double value) {
        Sales updatedSales = salesRepository.getReferenceById(sales.getSalesId());
        changeSales(sales, updatedSales, value);
    }

    private void changeSales(Sales salesDto, Sales updatedSales, Double value) {
        updatedSales.setDate(salesDto.getDate());
        updatedSales.setProductName(salesDto.getProductName());
        updatedSales.setQuantity(salesDto.getQuantity());
        updatedSales.setUnitPrice(value);
        updatedSales.setTotalPrice(value * salesDto.getQuantity());
        updatedSales.setNotes(salesDto.getNotes());

        updatedSales.setIsDeleted(0);
        salesRepository.save(updatedSales);
    }

    public Double getTotalSales() {
        return salesRepository.getTotalSales();
    }

    public Optional<Sales> getSalesById(Integer salesId) {
        return salesRepository.findById(salesId);
    }

    public List<String> getTopSellingProduct() {
        return salesRepository.getTopSellingProduct();
    }

    public List<Object> getSalesProductAndQuantity() {
        return salesRepository.getSalesProductAndQuantity();
    }

    public Integer getQuantityByProductName(String productName) {
        return salesRepository.getQuantityByProductName(productName);
    }

    public Integer getQuantityById(Integer salesId) {
        return salesRepository.getQuantityById(salesId);
    }
}
