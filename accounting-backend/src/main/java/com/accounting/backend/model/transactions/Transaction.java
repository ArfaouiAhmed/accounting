package com.accounting.backend.model.transactions;


import com.accounting.backend.model.company.Company;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private Integer number;

    @Column(name = "transaction_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate transactionDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
