package com.accounting.backend.controller;

import com.accounting.backend.dto.transaction.AddTransactionInput;
import com.accounting.backend.dto.transaction.AddTransactionPayload;
import com.accounting.backend.dto.transaction.UpdateTransactionInput;
import com.accounting.backend.dto.transaction.UpdateTransactionPayload;
import com.accounting.backend.model.transactions.Transaction;
import com.accounting.backend.repository.TransactionRepository;
import com.accounting.backend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @QueryMapping
    public Iterable<Transaction> transactions() {
        return transactionRepository.findAll();
    }

    @QueryMapping
    public Optional<Transaction> transaction(@Argument UUID id) {
        return transactionRepository.findById(id);
    }

    @MutationMapping
    public AddTransactionPayload addTransaction(@Argument AddTransactionInput input) {
        Transaction transaction = transactionService.addTransaction(
                input.getCompanyId(),
                input.getName(),
                input.getNumber(),
                input.getTransactionDate(),
                input.getAmount(),
                input.getType()
        );

        return new AddTransactionPayload(transaction);
    }

    @MutationMapping
    public UpdateTransactionPayload updateTransaction(@Argument UpdateTransactionInput input) {
        Transaction transaction = transactionService.updateTransaction(
                input.getTransactionId(),
                input.getName(),
                input.getNumber(),
                input.getTransactionDate(),
                input.getAmount(),
                input.getType()
        );

        return new UpdateTransactionPayload(transaction);
    }
}
