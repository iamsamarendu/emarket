package com.share.stock.api.feign.hystrix;


import com.share.stock.api.feign.CompanyFeignClient;
import com.share.stock.api.model.Company;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
@Component
public class CompanyApiFallbackFactory implements FallbackFactory<CompanyFeignClient> {
    @Override
    public CompanyFeignClient create(Throwable throwable) {
        return company -> {
            log.error("Error occurred trying to fetch company from company service", throwable);
            return new Company();
        };

    }
}
