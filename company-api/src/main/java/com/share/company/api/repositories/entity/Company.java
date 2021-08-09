package com.share.company.api.repositories.entity;

import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "company")
@Data
public class Company {
    @NonNull
    private String companyName;

    @Indexed(unique = true,name = "companycode_index")
    private String companyCode;
    @NonNull
    private String website;
    private String companyCEO;
    private String stockExchange;
    @NonNull
    //@DecimalMin(value = "100000000",message = "Turnover for your company is too low.")
    private Double turnover;
}
