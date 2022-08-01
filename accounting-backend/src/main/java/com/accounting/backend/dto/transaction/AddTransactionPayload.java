package com.accounting.backend.dto.transaction;

import com.accounting.backend.model.company.Company;
import com.accounting.backend.model.transactions.Transaction;

public class AddTransactionPayload extends AbstractTransactionPayload {
    public AddTransactionPayload(Transaction transaction) {
        super(transaction);
    }
}
