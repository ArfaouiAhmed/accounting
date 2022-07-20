package com.accounting.backend.dto;

import com.accounting.backend.model.Customer;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
public class AbstractCustomerPayload {

    private final Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public AbstractCustomerPayload(Customer customer) {
        this.customer = customer;
    }
}
