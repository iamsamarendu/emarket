package com.share.company.api.rest;

import com.share.company.api.model.CompanyDTO;
import com.share.company.api.service.CompanyManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
//@RequestMapping("/company")
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    CompanyManagerService companyManagerService;

    @Override
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@Valid @RequestBody CompanyDTO company) throws Exception {
        log.info("Register start");
      //  try{
            System.out.println("Starting register : company req :-" + company.toString());
            companyManagerService.register(company);
            log.info("Registered succesfully {company}",company.toString());
            return new ResponseEntity("Company registered successfully", HttpStatus.CREATED);
//        }catch (Exception e){
//            log.error("Company register failed. Error : {}",e.getStackTrace());
//            System.out.println("Inside catch :" + e.getMessage());
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
    }
    @Override
    @GetMapping("/info/{companyCode}")
    public ResponseEntity<CompanyDTO> getInfo(@PathVariable(value = "companyCode") String companyCode) {
        log.info("GetInfor started for company code ",companyCode);
        try {
            CompanyDTO companyDTO = companyManagerService.getComapny(companyCode);
            return new ResponseEntity(companyDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occured for getInfo: Error :", e.getStackTrace());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/getall")
    public ResponseEntity<List<CompanyDTO>> getAll() {
        try {
            List<CompanyDTO> companyList = companyManagerService.getAllCompany();
            if(!CollectionUtils.isEmpty(companyList)){
                return new ResponseEntity(companyManagerService.getAllCompany(),HttpStatus.OK);
            } else {
                return new ResponseEntity(new ArrayList(),HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("/delete/{companyCode}")
    public ResponseEntity<String> delete(@PathVariable String companyCode) {
        try {
            Boolean result = companyManagerService.deleteCompany(companyCode);
            return new ResponseEntity("Company deleted successfully",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/")
    public String get(){
        return "Hello Company";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }
}
