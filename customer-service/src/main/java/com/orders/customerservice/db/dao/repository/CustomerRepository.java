package com.orders.customerservice.db.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orders.customerservice.db.dao.model.CustomerEntity;

/**
 * @author davidjmartin
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    boolean existsByEmail(String email);
}
