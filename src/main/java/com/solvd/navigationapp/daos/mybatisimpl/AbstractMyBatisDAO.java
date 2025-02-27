package com.solvd.navigationapp.daos.mybatisimpl;

import com.solvd.navigationapp.utils.ConnectionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractMyBatisDAO<T> {
    
    protected abstract Class<T> getMapperClass();
    
    protected <R> R executeInSession(Function<T, R> operation) {
        try (SqlSession session = ConnectionFactory.getSQLSessionFactory().openSession()) {
            T mapper = session.getMapper(getMapperClass());
            R result = operation.apply(mapper);
            session.commit();
            return result;
        }
    }

    protected void executeInSessionVoid(Consumer<T> operation) {
        try (SqlSession session = ConnectionFactory.getSQLSessionFactory().openSession()) {
            T mapper = session.getMapper(getMapperClass());
            operation.accept(mapper);
            session.commit();
        }
    }
} 