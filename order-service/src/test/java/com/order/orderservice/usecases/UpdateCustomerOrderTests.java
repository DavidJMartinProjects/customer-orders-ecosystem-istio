package com.order.orderservice.usecases;

import org.springframework.http.HttpStatus;

import com.order.orderservice.IntegrationTest;
import com.order.orderservice.controller.OrderController;
import com.order.orderservice.db.dao.model.CustomerOrder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class UpdateCustomerOrderTests extends IntegrationTest {

    @Test
    public void given_nonExistentOrderId_when_putRequestToOrders_then_conflict() {

        // given
        final long nonExistentOrderId = 100;

        // when
        webTestClient
            .put()
                .uri(OrderController.ORDER_BASE_PATH + "/" + nonExistentOrderId)
                .bodyValue(CustomerOrder.builder().build())
            .exchange()

            // then
            .expectStatus()
                .isEqualTo(HttpStatus.CONFLICT)
            .expectBody()
                .jsonPath("$.url").value(Matchers.equalTo("PUT request to : /orders/100"))
                .jsonPath("$.errorCode").value(Matchers.equalTo("order-service error."))
                .jsonPath("$.message").value(Matchers.containsString("order with id: 100 does not exist."))
                .jsonPath("$.timestamp").isNotEmpty();

    }

}
