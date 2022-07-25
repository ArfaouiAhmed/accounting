package com.accounting.backend.dto.company;

import com.accounting.backend.model.company.Company;

public class AddCompanyPayload extends AbstractCompanyPayload {
    public AddCompanyPayload(Company company) {
        super(company);
    }
}
