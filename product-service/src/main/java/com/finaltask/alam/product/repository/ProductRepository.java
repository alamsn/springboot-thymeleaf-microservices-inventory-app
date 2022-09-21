package com.finaltask.alam.product.repository;

import com.finaltask.alam.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.isDeleted = 0")
    List<Product> findByIsDeletedIsZero();

    @Query("select sum(p.stock) from Product p")
    Integer totalProduct();

    @Query("select p.name, p.stock from Product p order by p.name asc")
    List<Object> getProductAndStock();

    @Query("select count(p) from Product p where p.stock = 0")
    Integer findOutOfStockProduct();

    @Modifying
    @Query("update Product p set p.stock = p.stock-:stock where p.name = :productName")
    void updateProductStock(@Param("stock") Integer stock, @Param("productName") String productName );

    @Query("select p.price from Product p where p.name = :productName")
    Double getPriceByProductName(@Param("productName") String productName);
}