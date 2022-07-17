package com.accounting.backend.controller;

import com.accounting.backend.model.Customer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    final static Customer customer1 = Customer.builder()
            .id("testid")
            .name("customer1")
            .build();

    @QueryMapping
    public Optional<Customer> getCustomer(@Argument String customerId) {
        if (customerId == null) {
            return null;
        }
        return Optional.ofNullable(customer1);
    }

    @QueryMapping
    public List<Customer> getCustomers() {
        return Arrays.asList(customer1);
    }

    @MutationMapping
    public Customer addCustomer(@Argument String name){
        return null;
    }
}
