package com.accounting.backend.dto;

import com.accounting.backend.model.Customer;
import org.springframework.data.domain.Page;

public class PageInfo {
    private final Page<Customer> result;

    public PageInfo(Page<Customer> result) {
        this.result = result;
    }

    public int getPageNumber() {
        return result.getNumber();
    }

    public int getTotalPages() {
        return result.getTotalPages();
    }

    public long getTotalCount() {
        return result.getTotalElements();
    }

    public boolean getHasNext() {
        return result.hasNext();
    }

    public boolean getHasPrev() {
        return result.hasPrevious();
    }

    public Integer getNextPage() {
        if (result.hasNext()) {
            return result.getNumber() + 1;
        }

        return null;
    }

    public Integer getPrevPage() {
        if (result.hasPrevious()) {
            return result.getNumber() - 1;
        }

        return null;
    }
}

