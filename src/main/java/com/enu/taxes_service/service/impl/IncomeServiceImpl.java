package com.enu.taxes_service.service.impl;

import com.enu.taxes_service.domain.dto.IncomeDto;
import com.enu.taxes_service.domain.dto.IncomeView;
import com.enu.taxes_service.domain.model.Income;
import com.enu.taxes_service.domain.model.User;
import com.enu.taxes_service.mapper.IncomeMapper;
import com.enu.taxes_service.repository.IncomeRepository;
import com.enu.taxes_service.repository.UserRepository;
import com.enu.taxes_service.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    @Override
    public IncomeView addIncome(IncomeDto incomeDto, String username) {
        Income income = IncomeMapper.INSTANCE.toEntity(incomeDto);
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        income.setUser(user);
        return IncomeMapper.INSTANCE.toView(incomeRepository.save(income));
    }

    @Override
    public List<IncomeView> getAllByUsername(String username) {
        Long userId = userRepository.findByUsername(username).getId();
        List<Income> income = incomeRepository.findIncomesByUserId(userId);
        return income.stream().map(IncomeMapper.INSTANCE::toView).toList();
    }
}
