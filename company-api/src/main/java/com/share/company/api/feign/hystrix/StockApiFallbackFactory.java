package com.share.company.api.feign.hystrix;


import com.share.company.api.feign.StockFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
@Component
public class StockApiFallbackFactory implements FallbackFactory<StockFeignClient> {


    @Override
    public StockFeignClient create(Throwable throwable) {
        return null;
    }
}
