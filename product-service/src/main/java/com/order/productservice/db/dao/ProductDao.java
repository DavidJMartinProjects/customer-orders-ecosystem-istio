package com.order.productservice.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.productservice.db.DbOperation;
import com.order.productservice.db.dao.model.Product;
import com.order.productservice.db.dao.repository.ProductRepository;
import com.order.productservice.exception.ProductServiceException;

/**
 * @author DavidJMartin
 */
@Component
public class ProductDao implements DbOperation<Product> {

    private static String PRODUCT_ID_DOES_NOT_EXIST = "product with id: %s does not exist.";

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ProductServiceException(String.format(PRODUCT_ID_DOES_NOT_EXIST, id)));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product entity) {
        return productRepository.saveAndFlush(entity);
    }

    @Override
    public Product update(Product product) {
        if(productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        } else {
            throw new ProductServiceException(String.format(PRODUCT_ID_DOES_NOT_EXIST, product.getId()));
        }
    }

    @Override
    public void deleteById(long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductServiceException(String.format(PRODUCT_ID_DOES_NOT_EXIST, id));
        }
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

}
