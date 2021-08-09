package com.share.stock.api.mapper;

import com.share.stock.api.model.StockDTO;
import com.share.stock.api.repositories.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper
public interface StockMapper {
    @Mapping(source = "stockDateTime", target = "stockDateTime", qualifiedByName = "dateToTimestamp")
    StockDTO toDTO(Stock company);
    @Mapping(source = "stockDateTime", target = "stockDateTime", qualifiedByName = "timestampToDate")
    Stock toEntity(StockDTO companyDTO);
    @Named("timestampToDate")
    default Timestamp timestampToDate(LocalDateTime value){
        return Timestamp.valueOf(value);
    }
    @Named("dateToTimestamp")
    default LocalDateTime dateToTimestamp(Timestamp value){
        return value.toLocalDateTime();
    }

}
