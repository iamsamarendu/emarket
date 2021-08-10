package com.share.stock.api.repositories;


import com.share.stock.api.repositories.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "Select * from STOCK where companyCode=:companyCode AND date(stockDateTime) >=:startDate AND date(stockDateTime)<=:endDate", nativeQuery=true)
    List<Stock> findStock(@Param("companyCode") String companyCode, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query(value = "DELETE FROM STOCK WHERE companyCode=:companyCode", nativeQuery=true)
    void deleteByCompany(@Param("companyCode") String companyCode);
}
