package com.finaltask.alam.clients.product;

import com.finaltask.alam.clients.product.entity.Category;
import com.finaltask.alam.clients.product.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@FeignClient(name = "product-service", path = "/product")
public interface ProductClient {

    @GetMapping("/list")
    List<Product> getAllProduct();

    @PostMapping("/add")
    void addProduct(@RequestBody Product product);

    @PostMapping("/delete/{productId}")
    void deleteProduct(@PathVariable("productId") Integer productId);

    @PostMapping("/update/{productId}")
    void updateProduct(@Valid @RequestBody Product product, @PathVariable("productId") Integer productId);

    @GetMapping("/total")
    Integer getTotalProduct();

    @GetMapping("/productandstock")
    List<Object> getProductAndStock();

    @PostMapping("/{productName}/{stock}")
    void updateProductStock(@PathVariable("stock") Integer stock, @PathVariable("productName") String productName);

    @GetMapping("/edit/{productId}")
    Optional<Product> getProductById(@PathVariable("productId") Integer productId);

    @GetMapping("/price/{productName}")
    Double getPriceByProductName(@PathVariable("productName") String productName);

    @GetMapping("/outofstockproduct")
    Integer findOutOfStockProduct();

    @GetMapping("/category/list")
    List<Category> getAllCategory();

    @PostMapping("/category/add")
    void addCategory(@Valid @RequestBody Category category);

    @PostMapping("/category/delete/{categoryId}")
    void deleteCategory(@PathVariable("categoryId") Integer categoryId);

    @PostMapping("/category/update/{categoryId}")
    void updateCategory(@Valid @RequestBody Category category, @PathVariable("categoryId") Integer categoryId);

    @GetMapping("/categories")
    List<String> getCategories();

    @GetMapping("/category/edit/{categoryId}")
    Optional<Category> getCategoryById(@PathVariable("categoryId") Integer categoryId);

}
