package com.accounting.backend.dto.customer;

import java.util.UUID;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
public class UpdateCustomerInput extends AbstractCustomerInput {

    private UUID customerId;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
