package com.accounting.backend.dto;

import com.accounting.backend.model.Customer;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
public class AddCustomerPayload extends AbstractCustomerPayload {

    public AddCustomerPayload(Customer customer) {
        super(customer);
    }
}
