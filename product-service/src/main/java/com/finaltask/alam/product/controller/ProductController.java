package com.finaltask.alam.product.controller;

import com.finaltask.alam.product.entity.Product;
import com.finaltask.alam.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j @RestController @AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("/list")
    public List<Product> getAllProduct() {
        List<Product> allProduct = productService.getAllProduct();
        log.info("total produk: {} .", allProduct.size());
        return allProduct;
    }

    @PostMapping("/add")
    public void addProduct(@Valid @RequestBody Product product) {
        productService.addProduct(product);
        log.info("produk ditambahkan: {}", product);
    }

    @PostMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") Integer productId) {
        productService.deleteProduct(productId);
        log.info("produk dengan id {} terhapus", productId);
    }

    @PostMapping("/update/{productId}")
    public void updateCategory(@Valid @RequestBody Product product, @PathVariable("productId") Integer productId) {
        productService.updateProduct(product, productId);
        log.info("produk diupdate: {}", product);
    }

    @GetMapping("/total")
    public Integer getTotalProduct() {
        return productService.getTotalProduct();
    }

    @GetMapping("/edit/{productId}")
    public Optional<Product> getProductById(@PathVariable("productId") Integer productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/productandstock")
    public List<Object> getProductAndStock() {
        return productService.getProductAndStock();
    }

    @GetMapping("/outofstockproduct")
    public Integer findOutOfStockProduct() {
        return productService.findOutOfStockProduct();
    }

    @PostMapping("/{productName}/{stock}")
    public void updateProductStock(@PathVariable("stock") Integer stock, @PathVariable("productName") String productName) {
        productService.updateProductStock(stock, productName);
    }

    @GetMapping("/price/{productName}")
    public Double getPriceByProductName(@PathVariable("productName") String productName) {
       return productService.getPriceByProductName(productName);
    }

}
