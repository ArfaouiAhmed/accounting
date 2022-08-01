package com.accounting.backend.service;

import com.accounting.backend.common.Utils;
import com.accounting.backend.model.company.Company;
import com.accounting.backend.model.transactions.Transaction;
import com.accounting.backend.model.transactions.TransactionType;
import com.accounting.backend.repository.CompanyRepository;
import com.accounting.backend.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService {

    private final CompanyRepository companyRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction addTransaction(UUID companyId, String name, Integer number, LocalDate date, BigDecimal amount, TransactionType type) {
        final Company company = companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException(companyId.toString()));;

            Transaction transaction = new Transaction();
            transaction.setName(name);
            transaction.setNumber(number);
            transaction.setTransactionDate(date);
            transaction.setAmount(amount);
            transaction.setType(type);

            transactionRepository.save(transaction);
            return transaction;
    }

    @Transactional
    public Transaction updateTransaction(UUID transactionId, String name, Integer number, LocalDate date, BigDecimal amount, TransactionType type) {
        final Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new EntityNotFoundException(transactionId.toString()));;
            Utils.setIfGiven(name, transaction::setName);
            Utils.setIfGiven(number, transaction::setNumber);
            Utils.setIfGiven(date, transaction::setTransactionDate);
            Utils.setIfGiven(amount, transaction::setAmount);
            Utils.setIfGiven(type, transaction::setType);

            transactionRepository.save(transaction);
            return transaction;

    }
}
