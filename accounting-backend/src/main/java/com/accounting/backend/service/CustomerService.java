package com.accounting.backend.service;

import com.accounting.backend.model.Customer;
import com.accounting.backend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;
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
            setIfGiven(name, customer::setName);
            setIfGiven(address, customer::setAddress);
            setIfGiven(city, customer::setCity);
            setIfGiven(telephone, customer::setPhone);
            customerRepository.save(customer);

        return customer;
    }

    private void setIfGiven(String value, Consumer<String> s) {
        if (value != null) {
            s.accept(value);
        }
    }
}
