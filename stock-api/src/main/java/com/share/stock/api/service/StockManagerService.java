package com.share.stock.api.service;

import com.share.stock.api.mapper.StockMapper;
import com.share.stock.api.model.StockDTO;
import com.share.stock.api.repositories.StockRepository;
import com.share.stock.api.repositories.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockManagerService {
    @Autowired
    private StockMapper mapper;
    @Autowired
    private StockRepository repository;

    @Transactional
    public void addStock(StockDTO stockDTO, String companyCode) throws Exception {
        log.info("Add stock start");
        if (!StringUtils.isEmpty(companyCode)) {
            log.info("Inside stock. Before mapper");
                Stock stock = mapper.toEntity(stockDTO);
            log.info("Inside stock. after mapper before save");
                stock.setCompanyCode(companyCode);
                repository.saveAndFlush(stock);
            log.info("Inside stock. after save");
        } else {
            log.error("Error occurred while adding stock :Company code cannot be empty ");
            throw new Exception("Company code cannot be empty");
        }
    }

    public List<StockDTO> findStock(String companyCode, String startDate, String endDate) throws Exception {
        log.info("FindStock start");
        if (!StringUtils.isEmpty(companyCode)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate sDate = LocalDate.parse(startDate, formatter);
            LocalDate eDate = LocalDate.parse(endDate, formatter);
            if (sDate.isAfter(eDate)) {
                throw new Exception("Start date must be previous date of End date");
            }
            log.info("FindStock before search repo");
            List<Stock> stockList = repository.findStock(companyCode, sDate, eDate);
            log.info("FindStock after search repo");
            return stockList.stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            log.error("Company code empty");
            throw new Exception("Company code cannot be empty");
        }
    }

    public Boolean deleteStock(String companyCode) throws Exception {
        log.info("DeleteStock start");
        if (!StringUtils.isEmpty(companyCode)) {
            repository.deleteByCompanyCode(companyCode);
            return true;
        } else {
            log.error("Company code empty");
            throw new Exception("Company code cannot be empty");
        }
    }
}