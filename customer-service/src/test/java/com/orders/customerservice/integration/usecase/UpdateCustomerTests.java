package com.orders.customerservice.integration.usecase;

import com.app.openapi.generated.model.Customer;
import com.orders.customerservice.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

/**
 * @author DavidJMartin
 */
public class UpdateCustomerTests extends IntegrationTest {

    // <-- Positive PUT Requests Integration Tests-->
    @Test
    void GIVEN_updatedCustomer_WHEN_putRequestToCustomerById_THEN_ok() {
        // given
        final Customer customer = customerFactory.findCustomerById(CUSTOMER_ID_ONE);
        customer.setFirstName("Clint");
        customer.setLastName("Eastwood");

        // when
        webTestClient
            .put()
            .uri(CUSTOMERS_API_BASE_PATH + "/" + customer.getId())
            .body(Mono.just(customer), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isOk()
            .expectBody(Customer.class)
                .isEqualTo(customer);
    }

    // <-- Negative PUT Requests Integration Tests-->
    @Test
    void GIVEN_emptyRequestBody_WHEN_putRequestToCustomer_THEN_badRequest() {
        // given
        final long customerId = 1;

        // when
        webTestClient
            .put()
            .uri(CUSTOMERS_API_BASE_PATH + "/" + customerId)
            .body(Mono.empty(), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isBadRequest()
            .expectBody()
                .isEmpty();
    }

}
