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
    @Query("Select * from Stock where companyCode=:companyCode AND date(stockDateTime) >=:StartDate AND date(stockDateTime)<=!endDate")
    List<Stock> findStock(@Param("companyCode") String companyCode, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query("DELETE FROM Stock WHERE companyCode=:companyCode")
    void deleteByCompany(@Param("companyCode") String companyCode);
}
