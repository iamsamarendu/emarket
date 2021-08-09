package com.share.stock.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StockDTO {
    private String currency;
    private String type;
    private Double stockPrice;
    private LocalDateTime stockDateTime;
    private String companyCode;
}
