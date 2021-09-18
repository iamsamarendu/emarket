package com.share.company.api.repositories.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@DynamoDBTable(tableName = "company")
@Data
public class Company {
    @NotEmpty(message = "Company name cannot be empty or null")
    private String companyName;
    @DynamoDBHashKey
    @NotEmpty(message = "Company code cannot be empty or null")
    private String companyCode;
    private String description;
    private String website;
    private String companyCEO;
    private String stockExchange;
    //@DecimalMin(value = "100000000",message = "Turnover for your company is too low.")
    private Double turnover;
}
