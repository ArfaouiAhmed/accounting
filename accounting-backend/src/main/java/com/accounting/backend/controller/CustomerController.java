package com.accounting.backend.controller;

import com.accounting.backend.dto.customer.*;
import com.accounting.backend.model.customer.Customer;
import com.accounting.backend.model.customer.CustomerFilter;
import com.accounting.backend.model.customer.CustomerOrder;
import com.accounting.backend.repository.CustomerRepository;
import com.accounting.backend.service.CustomerService;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {


    private final CustomerService customerService;
    private final CustomerRepository customerRepository;


    @QueryMapping
    public CustomerSearchResult customers(@Argument Optional<Integer> page, @Argument Optional<Integer>size,
                                          @Argument Optional<CustomerFilter> filter,
                                          @Argument List<CustomerOrder> orders) {
        int pageNo = page.orElse(0);
        int sizeNo = Math.min(size.orElse(20), 25);

        Sort sort = orders != null ? Sort.by(orders.stream().map(CustomerOrder::toOrder).collect(Collectors.toList()))
                : Sort.unsorted();

        final PageRequest pageRequest = PageRequest.of(pageNo, sizeNo, sort);
        return new CustomerSearchResult(customerRepository.findAll(filter.orElse(null), pageRequest));
    }

    @QueryMapping
    public Optional<Customer> customer(DataFetchingEnvironment env) {
        String id = env.getArgument("id");
        return customerRepository.findById(UUID.fromString(id));
    }

    @MutationMapping
    public AddCustomerPayload addCustomer(@Argument AddCustomerInput input) {
        Customer Customer = customerService.addCustomer(
                input.getName(),
                input.getTelephone(),
                input.getAddress(),
                input.getCity()
        );

        return new AddCustomerPayload(Customer);
    }

    @MutationMapping
    public UpdateCustomerPayload updateCustomer(@Argument UpdateCustomerInput input) {
        Customer customer = customerService.updateCustomer(
                input.getCustomerId(),
                input.getName(),
                input.getTelephone(),
                input.getAddress(),
                input.getCity()
        );

        return new UpdateCustomerPayload(customer);
    }
}
