package com.accounting.backend.controller;

import com.accounting.backend.model.Company;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class CompanyController {

    public Company company1 = Company.builder()
            .id("testid")
            .name("company1")
            .build();

    @QueryMapping
    public Optional<Company> getCompany(@Argument String companyId) {
        if (companyId == null) {
            return null;
        }
        return Optional.ofNullable(company1);
    }

    @MutationMapping
    public Company addCompany(@Argument String customerId, @Argument String name){
        return null;
    }
}
