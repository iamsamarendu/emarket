package com.share.stock.api.repositories.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private String currency;
    private String type;
}
