package com.orders.customerservice.controller;

import static com.orders.customerservice.controller.CustomerController.CUSTOMERS_API_BASE_PATH;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.openapi.generated.api.CustomersApi;
import com.app.openapi.generated.model.Customer;
import com.orders.customerservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Slf4j
@RestController
@RequestMapping(CUSTOMERS_API_BASE_PATH)
public class CustomerController implements CustomersApi {

    static final String CUSTOMERS_API_BASE_PATH = "/customers";

    @Autowired
    private CustomerService customerService;

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public String getPing() {
        return new RestTemplate().getForObject("http://order-service:9001/orders/ping", String.class);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Customer>> getCustomers() {
        log.info("received GET request to path: {}.", CUSTOMERS_API_BASE_PATH);
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        log.info("received POST request to path: {}.", CUSTOMERS_API_BASE_PATH);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customer));
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        log.info("received GET request to path: {}.", CUSTOMERS_API_BASE_PATH + id);
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> updateCustomerById(@PathVariable("id") Integer id, @RequestBody Customer customer) {
        log.info("received PUT request to path: {}.", CUSTOMERS_API_BASE_PATH + id);
        customer.setId(id);
        return ResponseEntity.ok(customerService.updateCustomerById(customer));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") Integer id) {
        log.info("received DELETE request to path: {}.", CUSTOMERS_API_BASE_PATH + id);
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

}
