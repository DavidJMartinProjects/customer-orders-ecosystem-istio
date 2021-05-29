package com.order.orderservice.usecases;

import static com.order.orderservice.controller.OrderController.ORDER_BASE_PATH;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.orderservice.IntegrationTest;
import com.order.orderservice.db.dao.model.Order;
import com.order.orderservice.db.dao.model.OrderItem;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class CreateOrderTests extends IntegrationTest {

    @Test
    public void given_customerOrder_when_postRequestToOrders_then_ok() throws JsonProcessingException, ParseException {
        // given
        Order order = buildCustomerOrder(3);

        // when
        webTestClient
            .post()
                .uri(ORDER_BASE_PATH)
                .body(Mono.just(order), Order.class)
            .exchange()

            //then
            .expectStatus()
                .isCreated()
            .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.orderItems").isNotEmpty()
                .jsonPath("$.status").value(Matchers.equalTo("order received."));

    }

    private Order buildCustomerOrder(int numOfOrderItems) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (int index = 0; index < numOfOrderItems; index++) {
            OrderItem orderItem =
                OrderItem.builder()
                .productId(100 + index)
                .quantity(10 + index)
                .build();
            orderItems.add(orderItem);
        }

        return
            Order.builder()
            .customerId(1)
            .orderItems(orderItems)
            .status("order received.")
            .timestamp(Timestamp.from(Instant.now()).toString())
            .build();
    }

    private JSONArray mapToJson(Set<OrderItem> orderItems) throws ParseException, JsonProcessingException {
        return (JSONArray) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE)
            .parse(new ObjectMapper().writeValueAsString(orderItems));
    }

}
