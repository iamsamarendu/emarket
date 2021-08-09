package com.share.company.api.feign.hystrix;

import com.share.company.api.feign.StockFeignClient;
import com.share.company.api.model.Stock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StockApiFallback implements StockFeignClient {

    @Override
    public List<Stock> getStockDetails(String companyCode) {
        return new ArrayList<>(Collections.emptyList());
    }

    @Override
    public String deleteStock(String companyCode) {
        return null;
    }
}
