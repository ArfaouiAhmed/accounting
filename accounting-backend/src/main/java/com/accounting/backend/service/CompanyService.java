package com.accounting.backend.service;

import com.accounting.backend.common.Utils;
import com.accounting.backend.model.company.Company;
import com.accounting.backend.model.customer.Customer;
import com.accounting.backend.repository.CompanyRepository;
import com.accounting.backend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyService {

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public Company addCompany(UUID customerId, String name, String address, String city, String telephone) {
        final Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(customerId.toString()));;

            Company company = new Company();
            company.setName(name);
            company.setAddress(address);
            company.setCity(city);
            company.setPhone(telephone);
            company.setCustomer(customer);

            companyRepository.save(company);
            return company;
    }

    @Transactional
    public Company updateCompany(UUID companyId, String name, String address, String city, String telephone) {
        final Company company = companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException(companyId.toString()));;
        Utils.setIfGiven(name, company::setName);
        Utils.setIfGiven(address, company::setAddress);
        Utils.setIfGiven(city, company::setCity);
        Utils.setIfGiven(telephone, company::setPhone);

            companyRepository.save(company);
            return company;

    }
}
