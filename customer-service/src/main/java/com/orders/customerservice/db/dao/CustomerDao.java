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
    public Customer findById(long id) {
        log.info("fetching customer with id: {}.", id);
        CustomerEntity entity = customerRepository.findById(id)
            .orElseThrow(() -> new CustomerServiceException(String.format(CUSTOMER_ID_DOES_NOT_EXIST, id)));
        return mapper.toDto(entity);
    }

    @Override
    public Customer save(Customer entity) {
        log.info("saving customer with lastName: {}.", entity.getLastName());
        return mapper.toDto(customerRepository.save(mapper.toEntity(entity)));
    }

    @Override
    public Customer update(Customer entity) {
        log.info("updating customer with id: {}.", entity.getId());
        if(customerRepository.existsById(Long.valueOf(entity.getId()))) {
            return mapper.toDto(customerRepository.save(mapper.toEntity(entity)));
        } else {
            throw new CustomerServiceException(String.format(CUSTOMER_ID_DOES_NOT_EXIST, entity.getId()));
        }
    }

    @Override
    public void deleteById(long id) {
        log.info("deleting customer with id: {}.", id);
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerServiceException(String.format(CUSTOMER_ID_DOES_NOT_EXIST, id));
        }
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return customerRepository.existsByEmail(email);
    }

}
