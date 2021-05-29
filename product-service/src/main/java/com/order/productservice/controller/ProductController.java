package com.order.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.order.productservice.db.dao.model.Product;
import com.order.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@RestController
@RequestMapping(ProductController.PRODUCTS_BASE_PATH)
@Slf4j
public class ProductController {

    public static final String PRODUCTS_BASE_PATH = "/products";

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@Validated @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

}
