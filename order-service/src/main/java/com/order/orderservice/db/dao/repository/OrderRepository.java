package com.order.orderservice.db.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.orderservice.db.dao.model.CustomerOrder;

/**
 * @author DavidJMartin
 */
@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
}
