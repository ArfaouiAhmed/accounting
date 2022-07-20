package com.accounting.backend.dto;

import com.accounting.backend.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public class CustomerSearchResult {

    private final Page<Customer> result;

    public CustomerSearchResult(Page<Customer> result) {
        this.result = result;
    }

    public PageInfo getPageInfo() {
        return new PageInfo(result);
    }

    public List<Customer> getCustomers() {
        return result.getContent();
    }
}
