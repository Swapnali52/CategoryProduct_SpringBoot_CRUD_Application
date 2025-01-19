package com.aitp.categoryproduct.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aitp.categoryproduct.model.Product;
import com.aitp.categoryproduct.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
    		 @RequestParam(value = "page", required = true) int page,
             @RequestParam(value = "size", required = true) int size) {
        return ResponseEntity.ok(productService.getAllProducts(page, size));
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product, @PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(productService.createProduct(product, categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}/{categoryId}")
    public ResponseEntity<Product> updateProduct(
    		@PathVariable("id") Long id, @PathVariable("categoryId") Long categoryId, @RequestBody Product updatedProduct) {
        return ResponseEntity.ok(productService.updateProduct(id, updatedProduct, categoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        Map<String, String> response = new HashMap();
        response.put("message", "Product with ID " + id + " has been deleted successfully.");
        return ResponseEntity.ok(response);
    }
}