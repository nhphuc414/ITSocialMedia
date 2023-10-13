package com.nhp.itsocialserver.services;

import com.nhp.itsocialserver.dtos.responses.stat.UserByMonth;

import java.util.List;

public interface StatService {
    List<UserByMonth> countUserByMonth(int year);
}
