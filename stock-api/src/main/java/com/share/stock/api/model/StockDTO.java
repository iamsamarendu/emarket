package com.share.stock.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StockDTO {
    @NotEmpty
    private String currency;
    @NotEmpty
    private String type;
    //@NotEmpty
    @DecimalMin(value = "0.1")
    private Double stockPrice;
    private LocalDateTime stockDateTime;
    private String companyCode;
}
