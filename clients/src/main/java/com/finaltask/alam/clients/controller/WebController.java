package com.finaltask.alam.clients.controller;

import com.finaltask.alam.clients.product.ProductClient;
import com.finaltask.alam.clients.product.entity.Category;
import com.finaltask.alam.clients.product.entity.Product;
import com.finaltask.alam.clients.sales.SalesClient;
import com.finaltask.alam.clients.sales.entity.Sales;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/client")
public class WebController {

    private final ProductClient productClient;
    private final SalesClient salesClient;
    Integer prevQuantity = 0;

    public WebController(ProductClient productClient, SalesClient salesClient) {
        this.productClient = productClient;
        this.salesClient = salesClient;
    }

    @GetMapping("")
    public String viewHomePage(Model model) {
        try {
            List<String> topProduct = salesClient.getTopSellingProduct();
            String topSelling = topProduct.get(0);
            model.addAttribute("totalSales", salesClient.getTotalSales());
            model.addAttribute("totalProduct", productClient.getTotalProduct());
            model.addAttribute("topSellingProduct", topSelling);
            model.addAttribute("outOfStockProduct", productClient.findOutOfStockProduct());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "index";
    }
    @GetMapping("/product")
    public String viewProduct(Model model) {
        model.addAttribute("listProduct", productClient.getAllProduct());
        return "product/index";
    }

    @GetMapping("/product/add")
    public String addProduct(Model model) {
        model.addAttribute("listCategory", productClient.getAllCategory());
        model.addAttribute("newProduct", new Product());
        return "product/add";
    }

    @PostMapping("/product/save")
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return"product/add";
        } else {
            productClient.addProduct(product);
            return "redirect:/client/product";
        }
    }

    @GetMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Integer productId) {
        productClient.deleteProduct(productId);
        return "redirect:/client/product";
    }

    @GetMapping("/product/edit/{productId}")
    public String editProduct(@PathVariable("productId") Integer productId, Model model) {
        Optional<Product> product = productClient.getProductById(productId);
        model.addAttribute("listCategory", productClient.getAllCategory());
        model.addAttribute("editProduct", product);
        return "product/edit";
    }

    @PostMapping("/product/update")
    public String updateProduct(@Valid Product product, @RequestParam(name = "productId") Integer productId, Errors errors) {
        if(errors.hasErrors()){
            return"product/edit";
        } else {
            productClient.updateProduct(product, productId);
            return "redirect:/client/product";
        }
    }

    @GetMapping("/category")
    public String viewCategory(Model model) {
        model.addAttribute("listCategory", productClient.getAllCategory());
        return "category/index";
    }

    @GetMapping("/category/add")
    public String addCategory(Model model) {
        model.addAttribute("newCategory", new Category());
        return "category/add";
    }

    @PostMapping("/category/save")
    public String saveCategory(@Valid Category category, Errors errors) {
        if(errors.hasErrors()){
            return"category/add";
        } else {
            productClient.addCategory(category);
            return "redirect:/client/category";
        }
    }

    @GetMapping("/category/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        productClient.deleteCategory(categoryId);
        return "redirect:/client/category";
    }

    @GetMapping("/category/edit/{categoryId}")
    public String editCategory(@PathVariable("categoryId") Integer categoryId, Model model) {
        Optional<Category> category = productClient.getCategoryById(categoryId);
        model.addAttribute("editCategory", category);
        return "category/edit";
    }

    @PostMapping("/category/update")
    public String updateCategory(@Valid Category category, @RequestParam(name = "categoryId") Integer categoryId, Errors errors) {
        if(errors.hasErrors()){
            return"category/edit";
        } else {
            productClient.updateCategory(category, categoryId);
            return "redirect:/client/category";
        }
    }

    @GetMapping("/sales")
    public String viewSales(Model model) {
        model.addAttribute("listSales", salesClient.getAllSales());
        return "sales/index";
    }

    @GetMapping("/sales/add")
    public String addSales(Model model) {
        model.addAttribute("listProduct", productClient.getAllProduct());
        model.addAttribute("newSales", new Sales());
        return "sales/add";
    }

    @PostMapping("/sales/save")
    public String saveSales(@Valid Sales sales, Errors errors) {
        if(errors.hasErrors()){
            return"sales/add";
        } else {
            salesClient.addSales(sales);
            // dapetin harga unit
            sales.setUnitPrice(productClient.getPriceByProductName(sales.getProductName()));
            productClient.updateProductStock(sales.getQuantity(), sales.getProductName());
            return "redirect:/client/sales";
        }
    }

    @GetMapping("/sales/delete/{salesId}")
    public String deleteSales(@PathVariable("salesId") Integer salesId) {
        salesClient.deleteSales(salesId);
        return "redirect:/client/sales";
    }

    @GetMapping("/sales/edit/{salesId}")
    public String editSales(@PathVariable("salesId") Integer salesId, Model model) {
        Optional<Sales> sales = salesClient.getSalesById(salesId);
        model.addAttribute("listProduct", productClient.getAllProduct());
        model.addAttribute("editSales", sales);
        prevQuantity = salesClient.getQuantityById(salesId);
        return "sales/edit";
    }

    @PostMapping("/sales/update")
    public String updateSales(@Valid Sales sales, @RequestParam(name = "salesId") Integer salesId, Errors errors) {

        if(errors.hasErrors()){
            return"sales/edit";
        } else {
            // dapetin harga unit
             sales.setUnitPrice(productClient.getPriceByProductName(sales.getProductName()));
            // update sales
            salesClient.updateSales(sales, salesId);
            // update stock
            Integer newQuantity = sales.getQuantity() - prevQuantity;
            productClient.updateProductStock(newQuantity, sales.getProductName());
            return "redirect:/client/sales";
        }
    }

}

