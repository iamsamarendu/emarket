package com.share.stock.api.feign;

import com.share.stock.api.feign.hystrix.CompanyApiFallbackFactory;
import com.share.stock.api.model.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="company-api",fallbackFactory = CompanyApiFallbackFactory.class)
public interface CompanyFeignClient {
    @GetMapping("company/info/{companyCode}")
    Company getCompanyDetails(@PathVariable String companyCode);
}
