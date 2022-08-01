package com.accounting.backend.dto.transaction;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class AddTransactionInput extends AbstractTransactionInput {

    private UUID companyId;
}
