package com.accounting.backend.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.Set;
import java.util.UUID;
@Data
@Builder
public class Customer {

    private String id;
    private String name;
    private Set<Company> companies;
}
