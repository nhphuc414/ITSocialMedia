package com.nhp.itsocialserver.services.impl;

import com.nhp.itsocialserver.dtos.responses.stat.UserByMonth;
import com.nhp.itsocialserver.repositories.StatRepository;
import com.nhp.itsocialserver.services.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {
    @Autowired
    private StatRepository statRepository;
    @Override
    public List<UserByMonth> countUserByMonth(int year) {
        List<Object[]> results = statRepository.countUserByMonth(year);
        Map<String, Long> userCountsByMonth = new LinkedHashMap<>();
        for (int month = 1; month <= 12; month++) {
            userCountsByMonth.put(Month.of(month).toString(), 0L);
        }
        for (Object[] result : results) {
            int month = (int) result[0];
            Long countUser = (Long) result[1];
            String monthName = Month.of(month).toString();
            userCountsByMonth.put(monthName, countUser);
        }
        return userCountsByMonth.entrySet().stream()
                .map(entry -> new UserByMonth(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
