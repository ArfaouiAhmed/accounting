package com.accounting.backend.dto.company;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AbstractCompanyInput {

    private String name;
    private String address;
    private String city;
    private String telephone;

}
