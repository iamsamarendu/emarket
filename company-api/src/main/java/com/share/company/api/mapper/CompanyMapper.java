package com.share.company.api.mapper;

import com.share.company.api.model.CompanyDTO;
import com.share.company.api.repositories.entity.Company;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper {
    CompanyDTO toDTO(Company company);
    Company toEntity(CompanyDTO companyDTO);
}
