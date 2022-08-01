package com.accounting.backend.dto.transaction;


import com.accounting.backend.model.transactions.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
public class AbstractTransactionInput {

    private String name;
    private Integer number;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private TransactionType type;

}
