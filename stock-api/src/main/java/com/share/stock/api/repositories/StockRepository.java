package com.share.stock.api.repositories;


import com.share.stock.api.repositories.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "Select * from stock where company_code=:companyCode AND date(stock_date_time) >=:startDate AND date(stock_date_time)<=:endDate", nativeQuery = true)
    List<Stock> findStock(@Param("companyCode") String companyCode, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM stock WHERE company_code=:companyCode", nativeQuery=true)
    @Transactional
    long deleteByCompanyCode(String companyCode);
}
