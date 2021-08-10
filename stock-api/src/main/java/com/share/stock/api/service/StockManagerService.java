package com.share.stock.api.service;

import com.share.stock.api.feign.CompanyFeignClient;
import com.share.stock.api.mapper.StockMapper;
import com.share.stock.api.model.Company;
import com.share.stock.api.model.StockDTO;
import com.share.stock.api.repositories.StockRepository;
import com.share.stock.api.repositories.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired
    private CompanyFeignClient companyFeignClient;


    public void addStock(StockDTO stockDTO, String companyCode) throws Exception {
        log.info("Add stock start");
        if (!StringUtils.isEmpty(companyCode)) {
            //check company existence
            Company company = companyFeignClient.getCompanyDetails(companyCode);
            log.info("Company details : " + company.toString());
           // if(null!=company) {
                Stock stock = mapper.toEntity(stockDTO);
                stock.setCompanyCode(companyCode);
                repository.save(stock);
//            } else {
//                log.error("Company doesn't exists ");
//                throw new Exception("Company doesn't exists");
//            }
        } else {
            log.error("Error occurred while adding stock :Company code cannot be empty ");
            throw new Exception("Company code cannot be empty");
        }
    }

    public List<StockDTO> findStock(String companyCode, String startDate, String endDate) throws Exception {
        log.info("FindStock start");
        if (!StringUtils.isEmpty(companyCode)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate sDate = LocalDate.parse(startDate, formatter);
            LocalDate eDate = LocalDate.parse(endDate, formatter);
            if (sDate.isAfter(eDate)) {
                throw new Exception("Start date must be previous date of End date");
            }
            List<Stock> stockList = repository.findStock(companyCode, sDate, eDate);
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
            repository.deleteByCompany(companyCode);
            return true;
        } else {
            log.error("Company code empty");
            throw new Exception("Company code cannot be empty");
        }
    }
}