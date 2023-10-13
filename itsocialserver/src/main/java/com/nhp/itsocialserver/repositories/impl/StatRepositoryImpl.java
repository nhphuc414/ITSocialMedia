package com.nhp.itsocialserver.repositories.impl;

import com.nhp.itsocialserver.repositories.StatRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class StatRepositoryImpl implements StatRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Object[]> countUserByMonth(int year) {
        String jpql = "SELECT FUNCTION('MONTH', u.createdDate) AS month, COUNT(u.id) AS userCount " +
                "FROM User u " +
                "WHERE FUNCTION('YEAR', u.createdDate) <= :year " +
                "GROUP BY FUNCTION('MONTH', u.createdDate)";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("year", year);
        return query.getResultList();
    }
}
