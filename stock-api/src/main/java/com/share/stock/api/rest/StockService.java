package com.share.stock.api.rest;

import com.share.stock.api.model.StockDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StockService {
    ResponseEntity<String> addStock(@RequestBody StockDTO stock, @PathVariable String companyCode);
    public ResponseEntity<List<StockDTO>> getStock(@PathVariable String companyCode, @PathVariable String startDate, @PathVariable String endDate);
}
