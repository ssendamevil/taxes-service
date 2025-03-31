package com.enu.taxes_service.service.impl;

import com.enu.taxes_service.domain.IncomeType;
import com.enu.taxes_service.domain.PropertyType;
import com.enu.taxes_service.domain.TaxType;
import com.enu.taxes_service.domain.dto.TaxFilter;
import com.enu.taxes_service.domain.dto.TaxView;
import com.enu.taxes_service.domain.model.Income;
import com.enu.taxes_service.domain.model.Property;
import com.enu.taxes_service.domain.model.Tax;
import com.enu.taxes_service.domain.model.User;
import com.enu.taxes_service.mapper.TaxMapper;
import com.enu.taxes_service.repository.IncomeRepository;
import com.enu.taxes_service.repository.PropertyRepository;
import com.enu.taxes_service.repository.TaxRepository;
import com.enu.taxes_service.repository.UserRepository;
import com.enu.taxes_service.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {
    private final TaxRepository taxRepository;
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    @Override
    public List<TaxView> calculateTaxes(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        List<Income> incomes = incomeRepository.findAllByUserIdAndType(user.getId(), IncomeType.MAIN_JOB);
        for (Income income: incomes){
            if(taxRepository.existsByIncomeId(income.getId())){
                continue;
            }
            for (TaxType type : TaxType.values()) {
               switch (type){
                   case INDIVIDUAL_INCOME_TAX, MEDICAL_INSURANCE, PENSION_CONTRIBUTION, SOCIAL_CONTRIBUTION -> calculateStatTaxes(type, income, user);
               }
            }
        }

        List<Property> properties = propertyRepository.findAllByUserId(user.getId());
        properties.forEach(property -> {
            if(!taxRepository.existsByPropertyId(property.getId())){
                switch (property.getType()){
                    case LAND: {
                        calculatePropertyTaxes(TaxType.LAND_TAX, property, user);
                        break;
                    }
                    case VEHICLE: {
                        calculatePropertyTaxes(TaxType.TRANSPORT_TAX, property, user);
                        break;
                    }
                    default: {
                        calculatePropertyTaxes(TaxType.PROPERTY_TAX, property, user);
                        break;
                    }
                }
            }
        });

        List<Tax> taxes = taxRepository.findByUserId(user.getId());
        return taxes.stream().map(TaxMapper.INSTANCE::toView).toList();
    }
    
    private void calculateStatTaxes(TaxType taxType, Income income, User user){
        if(taxRepository.existsByTypeAndIncomeId(taxType, income.getId())) return;
        double taxRate = taxType.getRate()/100;
        double taxAmount = 0d;
        if(TaxType.INDIVIDUAL_INCOME_TAX.equals(taxType) || TaxType.SOCIAL_CONTRIBUTION.equals(taxType)){
            taxAmount = (income.getAmount() - (income.getAmount() * 0.1))*taxRate;
        }else{
            taxAmount = income.getAmount() * taxRate;
        }
        Tax tax = new Tax(null, user,taxAmount, income.getId(), null, taxType.getDescription(), LocalDateTime.now(), taxType);
        taxRepository.save(tax);
    }

    private void calculatePropertyTaxes(TaxType taxType, Property property, User user){
        if(taxRepository.existsByTypeAndPropertyId(taxType, property.getId())) return;
        double taxRate = taxType.getRate()/100;
        double taxAmount = property.getPrice() * taxRate;
        Tax tax = new Tax(null, user,taxAmount, null, property.getId(), taxType.getDescription(), LocalDateTime.now(), taxType);
        taxRepository.save(tax);
    }

    @Override
    public List<TaxView> getTaxesByFilters(String username, TaxFilter taxFilter) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        List<Tax> taxes = taxRepository.findByUserId(user.getId());
        List<Tax> filteredTaxes = taxes;
        if(Objects.nonNull(taxFilter.getType())){
            filteredTaxes = taxes.stream().filter(tax -> Objects.equals(tax.getType(), taxFilter.getType())).toList();
        }
        if((Objects.nonNull(taxFilter.getTo()) && Objects.nonNull(taxFilter.getFrom()))
            && taxFilter.getFrom().isBefore(taxFilter.getTo())){
            filteredTaxes = filteredTaxes.stream().filter(tax -> taxFilter.getTo().isAfter(tax.getDate()) && taxFilter.getFrom().isBefore(tax.getDate())).toList();
        }
        if((Objects.nonNull(taxFilter.getMin()) && Objects.nonNull(taxFilter.getMax()))
                && taxFilter.getMax() > taxFilter.getMin()){
            filteredTaxes = filteredTaxes.stream().filter(tax -> taxFilter.getMax() >= tax.getAmount() && taxFilter.getMin() <= tax.getAmount()).toList();
        }
        return filteredTaxes.stream().map(TaxMapper.INSTANCE::toView).toList();
    }
}
