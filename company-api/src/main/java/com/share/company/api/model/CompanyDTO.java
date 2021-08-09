package com.share.company.api.model;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Data
public class CompanyDTO {
    @NotEmpty(message = "Company name cannot be empty or null")
    private String companyName;
    @NotEmpty(message = "Company code cannot be empty or null")
    private String companyCode;
    @NotEmpty(message = "Company CEO cannot be empty or null")
    private String companyCEO;
    @DecimalMin(value = "10", message = "Turnover cannot be less than 10cr")
    private Double turnover;
    @NotEmpty(message = "Website cannot be empty or null")
    private String website;
    @NotEmpty(message = "Stock exchange cannot be empty or null")
    private String stockExchange;
}
