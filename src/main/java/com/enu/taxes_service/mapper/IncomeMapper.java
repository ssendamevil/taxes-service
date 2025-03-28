package com.enu.taxes_service.mapper;

import com.enu.taxes_service.domain.dto.IncomeDto;
import com.enu.taxes_service.domain.dto.IncomeView;
import com.enu.taxes_service.domain.model.Income;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncomeMapper {
    IncomeMapper INSTANCE = Mappers.getMapper(IncomeMapper.class);

    Income toEntity(IncomeDto incomeDto);

    IncomeView toView(Income income);
}
