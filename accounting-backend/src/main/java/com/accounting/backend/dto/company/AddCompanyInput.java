package com.accounting.backend.dto.company;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class AddCompanyInput extends AbstractCompanyInput{

    private UUID customerId;
}
