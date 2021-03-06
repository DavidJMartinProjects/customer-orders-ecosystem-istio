package com.order.orderservice.usecases;

import static com.order.orderservice.controller.OrderController.ORDER_BASE_PATH;

import org.springframework.http.HttpStatus;

import com.order.orderservice.IntegrationTest;
import com.order.orderservice.db.dao.model.Order;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReadOrdersTests extends IntegrationTest {

    @BeforeEach
    public void saveTestCustomerOrders() {
        customerOrderFactory.persistTestData(1);
    }

    @AfterEach
    public void tearDown() {
        customerOrderFactory.deleteTestData();
    }

    // <-- Positive GET Requests Integration Tests -->
    @Test
    void GIVEN_existingCustomerOrderId_WHEN_getRequestToCustomerOrdersById_THEN_ok() {
        // given
        final Order order = customerOrderFactory.findCustomerOrderById(CUSTOMER_ID_ONE);

        // when
        webTestClient
            .get()
               .uri(ORDER_BASE_PATH + "/" + order.getId())
            .exchange()

            // then
            .expectStatus()
                .isOk()
            .expectBody(Order.class)
                .isEqualTo(order);
    }

    @Test
    void GIVEN_expectedCustomerOrders_WHEN_getRequestToCustomerOrders_THEN_ok() {
        // given
        customerOrderFactory.buildTestCustomerOrders(2);

        // when
        webTestClient
            .get()
                .uri(ORDER_BASE_PATH)
            .exchange()

            // then
            .expectStatus()
                .isOk()
            .expectBodyList(Order.class)
                .hasSize(2);
    }

    // <-- Negative GET Requests Integration Tests -->
    @Test
    void GIVEN_nonExistingId_WHEN_getRequestToCustomerOrdersById_THEN_conflict() {
        // given
        final long nonExistingId = 100;

        // when
        webTestClient
            .get()
                .uri(ORDER_BASE_PATH + "/" + nonExistingId)
            .exchange()

            // then
            .expectStatus()
            .isEqualTo(HttpStatus.CONFLICT)
            .expectBody()
                .jsonPath("$.url").value(Matchers.equalTo("GET request to : /orders/100"))
                .jsonPath("$.errorCode").value(Matchers.equalTo("order-service error."))
                .jsonPath("$.message").value(Matchers.containsString("order with id: 100 does not exist."))
                .jsonPath("$.timestamp").isNotEmpty();
    }

}
