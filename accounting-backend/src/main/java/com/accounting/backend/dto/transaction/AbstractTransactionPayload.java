package com.accounting.backend.dto.transaction;

import com.accounting.backend.model.transactions.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AbstractTransactionPayload {

    private final Transaction transaction;
}
