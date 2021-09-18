package com.share.company.api.repositories;

import com.share.company.api.repositories.entity.Company;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@EnableScan
public interface CompanyRepository extends CrudRepository<Company,Long> {
    Company findByCompanyCode(String companyCode);
    @Transactional
    long deleteByCompanyCode(String companyCode);
}
