package com.finaltask.alam.product.repository;

import com.finaltask.alam.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.isDeleted = 0")
    List<Category> findByIsDeleted();

    @Query("select c.name from Category c where c.isDeleted = 0")
    List<String> getCategories();
}