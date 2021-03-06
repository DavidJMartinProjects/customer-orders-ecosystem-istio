package com.order.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.order.orderservice.factories.CustomerOrderFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Slf4j
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class IntegrationTest {

    protected static final String CUSTOMERS_API_BASE_PATH = "/customers";

    protected static final int CUSTOMER_ID_ONE = 1;

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected CustomerOrderFactory customerOrderFactory;

//    @BeforeEach
//    public void init() {
//        customerFactory.persistTestCustomers(3);
//    }

}

