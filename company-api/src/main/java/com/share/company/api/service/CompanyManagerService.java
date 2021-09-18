package com.share.company.api.service;

import com.share.company.api.feign.StockFeignClient;
import com.share.company.api.mapper.CompanyMapper;
import com.share.company.api.model.CompanyDTO;
import com.share.company.api.repositories.CompanyRepository;
import com.share.company.api.repositories.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyManagerService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyMapper mapper;
    @Autowired
    StockFeignClient stockFeignClient;
    public void register(CompanyDTO company) throws Exception {
        System.out.println("Inside manager service");
        Company com = mapper.toEntity(company);
        Company check = companyRepository.findByCompanyCode(com.getCompanyCode());
        if(check == null){
            companyRepository.save(com);
        } else {
           throw new Exception("Company already exists");
        }

        System.out.println("After save repo");
    }

    public CompanyDTO getComapny(String companyCode) throws Exception {
        if(!StringUtils.isEmpty(companyCode)){
//            Company com = companyRepository.findByCompanyCode(companyCode);
//            CompanyDTO companyDTO  = new CompanyDTO();
//            companyDTO.setCompanyCode(com.getCompanyCode());
//            companyDTO.setCompanyName(com.getCompanyName());
//            companyDTO.setTurnover(com.getTurnover());
//            companyDTO.setCompanyCEO(com.getCompanyCEO());;
//            companyDTO.setStockExchange(com.getStockExchange());
//            companyDTO.setWebsite(com.getWebsite());
//            //return companyDTO;
            Company company = companyRepository.findByCompanyCode(companyCode);
            System.out.println("company :" + company);
            if(null != company) {
                return mapper.toDTO(company);
            } else {
                throw new Exception("Company doesn't exists");
            }


        } else {
            throw new Exception("Company code cannot be blank");
        }

    }

    public List<CompanyDTO> getAllCompany() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(companyRepository.findAll().iterator(),Spliterator.ORDERED), false)
                    .map(mapper::toDTO).collect(Collectors.toList());
    }
    public boolean deleteCompany(String companyCode) throws Exception {
        if(!StringUtils.isEmpty(companyCode)) {
            System.out.println("Inside delete if");
            Company company = companyRepository.findByCompanyCode(companyCode);
            if(null != company) {
                stockFeignClient.deleteStock(companyCode);
                companyRepository.deleteByCompanyCode(companyCode);
                return true;
            } else {
                System.out.println("Inide else code doesnot exits");
                throw new Exception("Company doesn't exists");
            }

        } else {
            throw new Exception("Company code cannot be empty");
        }
    }
}
