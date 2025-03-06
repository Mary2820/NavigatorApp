package com.solvd.navigationapp.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static final String CONFIG_FILE = "mybatis-config.xml";

    public ConnectionFactory() {}

    public static SqlSessionFactory getSQLSessionFactory() {
        if (sqlSessionFactory == null) {
            try (Reader reader = Resources.getResourceAsReader(CONFIG_FILE)) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            } catch (IOException e) {
                logger.error("Error initializing MyBatis", e);
            }
        }
        return sqlSessionFactory;
    }
}