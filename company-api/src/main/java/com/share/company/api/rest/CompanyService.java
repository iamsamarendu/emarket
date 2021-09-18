package com.share.company.api.rest;

import com.share.company.api.model.CompanyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CompanyService {

    ResponseEntity<String> register(CompanyDTO company) throws Exception;
    ResponseEntity<CompanyDTO> getInfo(@PathVariable(value = "companyCode") String companyCode);

    ResponseEntity<List<CompanyDTO>> getAll();
    ResponseEntity<String>  delete(String companyCode);

}
