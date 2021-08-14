package com.share.company.api.repositories;

import com.share.company.api.repositories.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanyRepository extends MongoRepository<Company,Long> {
    Company findByCompanyCode(String companyCode);
    @Transactional
    long deleteByCompanyCode(String companyCode);
}
