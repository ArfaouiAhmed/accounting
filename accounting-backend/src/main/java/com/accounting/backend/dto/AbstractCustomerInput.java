package com.accounting.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
@Getter
@Setter
@ToString
public class AbstractCustomerInput {
    private String name;
    private String address;
    private String city;
    private String telephone;


}
