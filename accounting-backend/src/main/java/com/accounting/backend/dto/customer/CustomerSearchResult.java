package com.accounting.backend.dto.customer;

import com.accounting.backend.dto.PageInfo;
import com.accounting.backend.model.customer.Customer;
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
