package com.share.stock.api.repositories.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name = "STOCK")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long stockId;
    //@NotBlank
    private Double stockPrice;
    @Basic
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime stockDateTime = LocalDateTime.now();
    @NotBlank
    private String companyCode;
    @NotEmpty(message = "Currency cannot be empty")
    private String currency;
    private String type;
}
