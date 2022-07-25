package com.accounting.backend.controller;

import com.accounting.backend.dto.company.AddCompanyInput;
import com.accounting.backend.dto.company.AddCompanyPayload;
import com.accounting.backend.dto.company.UpdateCompanyInput;
import com.accounting.backend.dto.company.UpdateCompanyPayload;
import com.accounting.backend.model.company.Company;
import com.accounting.backend.repository.CompanyRepository;
import com.accounting.backend.service.CompanyService;
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
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final CompanyService companyService;


    @QueryMapping
    public Iterable<Company> companies() {
        return companyRepository.findAll();
    }

    @QueryMapping
    public Optional<Company> company(@Argument UUID id) {
        return companyRepository.findById(id);
    }

    @MutationMapping
    public AddCompanyPayload addCompany(@Argument AddCompanyInput input) {
        Company company = companyService.addCompany(
                input.getCustomerId(),
                input.getName(),
                input.getAddress(),
                input.getCity(),
                input.getTelephone()
        );

        return new AddCompanyPayload(company);
    }

    @MutationMapping
    public UpdateCompanyPayload updateCompany(@Argument UpdateCompanyInput input) {
        Company company = companyService.updateCompany(
                input.getCompanyId(),
                input.getName(),
                input.getAddress(),
                input.getCity(),
                input.getTelephone()
        );

        return new UpdateCompanyPayload(company);
    }
}
