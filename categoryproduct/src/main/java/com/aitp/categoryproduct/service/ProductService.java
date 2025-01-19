package com.aitp.categoryproduct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aitp.categoryproduct.exception.ResourceNotFoundException;
import com.aitp.categoryproduct.model.Category;
import com.aitp.categoryproduct.model.Product;
import com.aitp.categoryproduct.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Page<Product> getAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product, Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct, Long categoryId) {
        Product product = getProductById(id);
        Category category = categoryService.getCategoryById(categoryId);
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(category);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
