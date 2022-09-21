package com.finaltask.alam.product.controller;

import com.finaltask.alam.product.entity.Product;
import com.finaltask.alam.product.service.CategoryService;
import com.finaltask.alam.product.entity.Category;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j @RestController @AllArgsConstructor
@RequestMapping("/product")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/category/list")
    public List<Category> getAllCategory() {
        List<Category> allCategory = categoryService.getAllCategory();
        log.info("total kategori: {}.", allCategory.size());
        return allCategory;
    }

    @PostMapping("/category/add")
    public void addCategory(@Valid @RequestBody Category category) {
        categoryService.addCategory(category);
        log.info("kategori ditambahkan: {}", category);
    }

    @PostMapping("/category/delete/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        log.info("Kategori dengan id {} terhapus", categoryId);
    }

    @PostMapping("/category/update/{categoryId}")
    public void updateCategory(@PathVariable("categoryId") Integer categoryId,@Valid @RequestBody Category category) {
        categoryService.updateCategory(category, categoryId);
        log.info("kategori diupdate: {}", category);
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/category/edit/{categoryId}")
    public Optional<Category> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.getCategoryById(categoryId);
    }
}
