package com.accounting.backend.dto.transaction;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateTransactionInput extends AbstractTransactionInput {

    private UUID transactionId;
}
