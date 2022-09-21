package com.finaltask.alam.sales.repository;

import com.finaltask.alam.sales.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
    @Query("select s from Sales s where s.isDeleted = 0")
    List<Sales> findByIsDeleted();

    @Query("select sum(s.totalPrice) from Sales s")
    Double getTotalSales();

    @Query("select s.productName from Sales s group by s.productName order by sum(s.quantity) desc")
    List<String> getTopSellingProduct();


    @Query("select s.productName, sum(s.quantity) from Sales s group by s.productName order by s.productName asc")
    List<Object> getSalesProductAndQuantity();

    @Query("select sum(s.quantity)  from Sales s where s.productName = ?1")
    Integer getQuantityByProductName(String productName);

    @Query("select s.quantity from Sales s where s.salesId = :salesId")
    Integer getQuantityById(@Param("salesId") Integer salesId);

}