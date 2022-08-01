package com.accounting.backend.service;

import com.accounting.backend.common.Utils;
import com.accounting.backend.model.customer.Customer;
import com.accounting.backend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;
import java.util.function.Consumer;
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    CustomerRepository customerRepository;

    @Transactional
    public Customer addCustomer(@NotEmpty String name, @NotEmpty String telephone, @NotEmpty String address, @NotEmpty String city) {
        final Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setPhone(telephone);

        customerRepository.save(customer);

        return customer;
    }



    @Transactional
    public Customer updateCustomer(@NotEmpty UUID customerId, String name, String telephone, String address, String city) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(customerId.toString()));
        Utils.setIfGiven(name, customer::setName);
        Utils.setIfGiven(address, customer::setAddress);
        Utils.setIfGiven(city, customer::setCity);
        Utils.setIfGiven(telephone, customer::setPhone);
            customerRepository.save(customer);

        return customer;
    }
}
