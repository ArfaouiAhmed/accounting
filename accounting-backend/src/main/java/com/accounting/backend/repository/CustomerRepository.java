package com.accounting.backend.repository;

import com.accounting.backend.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    default Page<Customer> findAll(@Nullable Specification<Customer> spec, Pageable pageable) {
        throw new UnsupportedOperationException("findAll with specification only supported in spring-data-jpa repo currently!");
    }

}