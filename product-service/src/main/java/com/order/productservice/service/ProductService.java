package com.order.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.productservice.db.DbOperation;
import com.order.productservice.db.dao.model.Product;

/**
 * @author DavidJMartin
 */
@Service
public class ProductService {

    @Autowired
    private DbOperation<Product> dbOperation;

    public Product saveProduct(Product product) {
        return dbOperation.save(product);
    }

    public List<Product> getProducts() {
        return dbOperation.findAll();
    }

    public Product getProductById(long id) {
        return dbOperation.findById(id);
    }

}
