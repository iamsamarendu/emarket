package com.share.stock.api.feign.hystrix;

import com.share.stock.api.feign.CompanyFeignClient;
import com.share.stock.api.model.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyApiFallback implements CompanyFeignClient {
    @Override
    public Company getCompanyDetails(String companyCode) {
        return new Company();
    }
}
