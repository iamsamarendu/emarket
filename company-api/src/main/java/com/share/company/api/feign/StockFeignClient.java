package com.share.company.api.feign;

import com.share.company.api.model.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="${STOCK_API:stock-api}")
public interface StockFeignClient {
    @GetMapping("stock/get/{companyCode}/{startDate}/{endDate}")
    List<Stock> getStockDetails(@PathVariable String companyCode, @PathVariable String startDate, @PathVariable String endDate);
    @DeleteMapping("stock/delete/{companyCode}")
    String deleteStock(@PathVariable String companyCode);
}
