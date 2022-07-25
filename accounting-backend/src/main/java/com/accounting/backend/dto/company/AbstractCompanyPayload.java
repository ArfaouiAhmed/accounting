package com.accounting.backend.dto.company;

import com.accounting.backend.model.company.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AbstractCompanyPayload {

    private final Company company;
}
