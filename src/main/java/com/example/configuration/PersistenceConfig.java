package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * Created by surmab on 06.06.2017.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistanceConfiguration.properties" )
public class PersistenceConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(restDataSource());
//        sessionFactory.setPackagesToScan(
//                "org.baeldung.spring.persistence.model");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }

    @Bean
    public DataSource dataSource() {

        return DataSourceBuilder.create()
                .driverClassName(driver)
                .url(url)
                .username(username)
                .password(password)
                .build();

    }

//    @Bean
//    @Autowired
//    public HibernateTransactionManager transactionManager(
//            SessionFactory sessionFactory) {
//
//        HibernateTransactionManager txManager
//                = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory);
//
//        return txManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
//    Properties hibernateProperties() {
//        return new Properties() {
//            {
//                setProperty("hibernate.hbm2ddl.auto",
//                        env.getProperty("hibernate.hbm2ddl.auto"));
//                setProperty("hibernate.dialect",
//                        env.getProperty("hibernate.dialect"));
//                setProperty("hibernate.globally_quoted_identifiers",
//                        "true");
//            }
//        };
//    }
}