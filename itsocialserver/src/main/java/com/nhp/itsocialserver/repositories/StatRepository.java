package com.nhp.itsocialserver.repositories;

import java.util.List;

public interface StatRepository {
    List<Object[]> countUserByMonth(int year);
}
