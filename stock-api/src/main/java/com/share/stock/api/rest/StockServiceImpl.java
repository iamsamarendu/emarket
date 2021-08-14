package com.share.stock.api.rest;

import com.share.stock.api.model.StockDTO;
import com.share.stock.api.service.StockManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@Slf4j
@RestController
//@RequestMapping("/stock")
public class StockServiceImpl implements StockService{
    @Autowired
    StockManagerService stockManagerService;
    @Override
    @PostMapping("/add/{companyCode}")
    public ResponseEntity<String> addStock(@RequestBody StockDTO stock, @PathVariable String companyCode) {
        log.info("Add Stock started for Company:{}",companyCode);
        try {
            stockManagerService.addStock(stock, companyCode);
            return new ResponseEntity("Stock added", HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Exception :" + e.getMessage());
            log.error("Error occurred while adding stock"+ e.getStackTrace().toString());
            return new ResponseEntity("Error occurred while adding", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/get/{companyCode}/{startDate}/{endDate}")
    public ResponseEntity<List<StockDTO>> getStock(@PathVariable String companyCode, @PathVariable String startDate, @PathVariable String endDate) {
        log.info("Get Stock Started");
        try {
            List<StockDTO> list = stockManagerService.findStock(companyCode,startDate, endDate);
            return new ResponseEntity(list, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception : " + e.getMessage());

            log.error("Error occurred while finding stock" ,e);
            return new ResponseEntity("Error occurred while finding" + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{companyCode}")
    public ResponseEntity<String> deleteStock(@PathVariable String companyCode){
        log.info("Delete stock started");
        try {
            stockManagerService.deleteStock(companyCode);
            return new ResponseEntity("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while deleting stock", e.getStackTrace());
            return new ResponseEntity("Error occurred while deleting", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/")
    public String welcome(){
        return "Welcome to Stock";
    }
}
