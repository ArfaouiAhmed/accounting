package com.accounting.backend.dto.transaction;

import com.accounting.backend.model.transactions.Transaction;

public class UpdateTransactionPayload extends AbstractTransactionPayload {
    public UpdateTransactionPayload(Transaction transaction) {
        super(transaction);
    }
}
