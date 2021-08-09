package com.share.stock.api.model;

import lombok.Data;
@Data
public class Company {
    private String companyName;
    private String companyCode;
    private String companyCEO;
    private Double turnover;
    private String website;
    private String stockExchange;
}
