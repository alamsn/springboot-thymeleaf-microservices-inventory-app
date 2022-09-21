package com.finaltask.alam.product.service;

import com.finaltask.alam.product.entity.Product;
import com.finaltask.alam.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
     return productRepository.findByIsDeletedIsZero();
    }

    public void addProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setCategoryId(product.getCategoryId());
        newProduct.setStock(product.getStock());
        newProduct.setIsDeleted(0);
        productRepository.save(newProduct);
    }

    public void deleteProduct(Integer productId) {
        Product deletedProduct = productRepository.getReferenceById(productId);
        deletedProduct.setIsDeleted(1);
        productRepository.save(deletedProduct);
    }

    public void updateProduct(Product product, Integer productId) {
        Product updatedProduct = productRepository.getReferenceById(productId);
        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setCategoryId(product.getCategoryId());
        updatedProduct.setStock(product.getStock());
        productRepository.save(updatedProduct);
    }

    public Integer getTotalProduct() {
        return productRepository.totalProduct();
    }

    public Optional<Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }

    public List<Object> getProductAndStock() {
        return productRepository.getProductAndStock();
    }

    public Integer findOutOfStockProduct() {
        return productRepository.findOutOfStockProduct();
    }

    @Transactional
    public void updateProductStock(Integer stock, String productName) {
        productRepository.updateProductStock(stock, productName);
    }

    public Double getPriceByProductName(String productName) {
        return productRepository.getPriceByProductName(productName);
    }
}
