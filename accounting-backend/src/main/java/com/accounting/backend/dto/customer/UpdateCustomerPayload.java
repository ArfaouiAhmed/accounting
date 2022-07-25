package com.accounting.backend.dto.customer;

import com.accounting.backend.model.customer.Customer;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
public class UpdateCustomerPayload extends AbstractCustomerPayload {
    public UpdateCustomerPayload(Customer customer) {
        super(customer);
    }
}
