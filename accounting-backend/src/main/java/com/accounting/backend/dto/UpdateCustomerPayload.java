package com.accounting.backend.dto;

import com.accounting.backend.model.Customer;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
public class UpdateCustomerPayload extends AbstractCustomerPayload {
    public UpdateCustomerPayload(Customer customer) {
        super(customer);
    }
}
