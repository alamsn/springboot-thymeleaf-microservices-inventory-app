package com.finaltask.alam.product.service;

import com.finaltask.alam.product.entity.Product;
import com.finaltask.alam.product.repository.CategoryRepository;
import com.finaltask.alam.product.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
     return categoryRepository.findByIsDeleted();
    }

    public void addCategory(Category category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setCode(category.getCode());
        newCategory.setIsDeleted(0);
        categoryRepository.save(newCategory);
    }

    public void deleteCategory(Integer categoryId){
        Category deletedCategory = categoryRepository.getReferenceById(categoryId);
        deletedCategory.setIsDeleted(1);
        categoryRepository.save(deletedCategory);
    }

    public void updateCategory(Category category, Integer categoryId) {
        Category updatedCategory = categoryRepository.getReferenceById(categoryId);
        updatedCategory.setCode(category.getCode());
        updatedCategory.setName(category.getName());
        categoryRepository.save(updatedCategory);
    }

    public List<String> getCategories() {
        return categoryRepository.getCategories();
    }

    public Optional<Category> getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
