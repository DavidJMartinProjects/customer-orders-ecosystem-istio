package com.orders.customerservice.db.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.generated.model.Customer;
import com.orders.customerservice.db.DbOperation;
import com.orders.customerservice.db.dao.mapper.CustomerMapper;
import com.orders.customerservice.db.dao.model.CustomerEntity;
import com.orders.customerservice.db.dao.repository.CustomerRepository;
import com.orders.customerservice.exception.CustomerServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Slf4j
@Component
public class CustomerDao implements DbOperation<Customer> {

    private static String CUSTOMER_ID_DOES_NOT_EXIST = "customer with id: %s does not exist.";

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        log.info("fetching customers.");
        return customerRepository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public Customer findById(long customerId) {
        log.info("fetching customer with id: {}.", customerId);
        CustomerEntity entity = customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerServiceException(String.format(CUSTOMER_ID_DOES_NOT_EXIST, customerId)));
        return mapper.toDto(entity);
    }

    @Override
    public Customer save(Customer customer) {
        log.info("saving customer with lastName: {}.", customer.getLastName());
        return mapper.toDto(customerRepository.save(mapper.toEntity(customer)));
    }

    @Override
    public Customer update(Customer customer) {
        log.info("updating customer with id: {}.", customer.getId());
        if(customerRepository.existsById(Long.valueOf(customer.getId()))) {
            return mapper.toDto(customerRepository.save(mapper.toEntity(customer)));
        } else {
            throw new CustomerServiceException(String.format(CUSTOMER_ID_DOES_NOT_EXIST, customer.getId()));
        }
    }

    @Override
    public void deleteById(long customerId) {
        log.info("deleting customer with id: {}.", customerId);
        if(customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
        } else {
            throw new CustomerServiceException(String.format(CUSTOMER_ID_DOES_NOT_EXIST, customerId));
        }
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return customerRepository.existsByEmail(email);
    }

}
