package com.accounting.backend.dto.company;

import com.accounting.backend.model.company.Company;

public class UpdateCompanyPayload extends AbstractCompanyPayload {
    public UpdateCompanyPayload(Company company) {
        super(company);
    }
}
