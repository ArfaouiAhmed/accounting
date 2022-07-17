package com.accounting.backend.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.UUID;
@Data
@Builder
public class Company {
    private String id;
    private String name;
}
