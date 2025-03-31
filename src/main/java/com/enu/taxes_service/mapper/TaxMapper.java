package com.enu.taxes_service.mapper;

import com.enu.taxes_service.domain.dto.TaxView;
import com.enu.taxes_service.domain.model.Tax;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaxMapper {
    TaxMapper INSTANCE = Mappers.getMapper(TaxMapper.class);

    TaxView toView(Tax tax);
}
