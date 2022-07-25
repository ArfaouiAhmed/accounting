package com.accounting.backend.dto.customer;

import com.accounting.backend.model.customer.Customer;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
public class AddCustomerPayload extends AbstractCustomerPayload {

    public AddCustomerPayload(Customer customer) {
        super(customer);
    }
}
