package com.order.orderservice.usecases;

import org.springframework.http.HttpStatus;

import com.order.orderservice.IntegrationTest;
import com.order.orderservice.controller.OrderController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class DeleteCustomerOrderTests extends IntegrationTest {

    @Test
    public void given_nonExistantOrderId_when_deleteRequestToOrders_then_conflict() {
        // given
        final long nonExistantOrderId = 100;

        // when
        webTestClient
            .delete()
                .uri(OrderController.ORDER_BASE_PATH + "/" + nonExistantOrderId)
            .exchange()

        // then
            .expectStatus()
                .isEqualTo(HttpStatus.CONFLICT)
            .expectBody()
                .jsonPath("$.url").value(Matchers.equalTo("DELETE request to : /orders/100"))
                .jsonPath("$.errorCode").value(Matchers.equalTo("order-service error."))
                .jsonPath("$.message").value(Matchers.containsString("order with id: 100 does not exist."))
                .jsonPath("$.timestamp").isNotEmpty();
    }

}
