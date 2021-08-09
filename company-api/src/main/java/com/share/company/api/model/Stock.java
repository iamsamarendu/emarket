package com.share.company.api.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Stock {
    private String currency;
    private String type;
    private Double stockPrice;
    private LocalDateTime stockDateTime;
    private String companyCode;
}
