package com.accounting.backend.dto.company;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateCompanyInput extends AbstractCompanyInput{

    private UUID companyId;
}
