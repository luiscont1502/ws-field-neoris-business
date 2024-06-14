package com.neoris.source;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
        @ComponentScan(basePackages = {"com.neoris.persistence.postgres.service"})
@EnableJpaRepositories(basePackages = {"com.neoris.persistence.postgres.repository"})
@EnableTransactionManagement
public class PostgresConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        // configuración azure
        dataSource.setUrl("jdbc:postgresql://localhost:5432/neoris");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        // configuracion local
        /*dataSource.setUrl("jdbc:postgresql://localhost:5432/store_jean");
        dataSource.setUsername("postgres");
        dataSource.setPassword("luis");*/
        //Configuracion con Aws
        /*dataSource.setUrl("jdbc:postgresql://3.12.77.177:5432/store_jean");
        dataSource.setUsername("luis");
        dataSource.setPassword("*Luis0959743877");*/
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.neoris.persistence.postgres.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
