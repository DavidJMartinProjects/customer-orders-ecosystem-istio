package com.order.orderservice.db.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.order.orderservice.db.dao.model.Order;

/**
 * @author DavidJMartin
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, CrudRepository<Order, Long> {
}
