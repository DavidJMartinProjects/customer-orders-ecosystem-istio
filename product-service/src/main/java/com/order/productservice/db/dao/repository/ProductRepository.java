package com.order.productservice.db.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.order.productservice.db.dao.model.Product;

/**
 * @author DavidJMartin
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, CrudRepository<Product, Long> {
}
