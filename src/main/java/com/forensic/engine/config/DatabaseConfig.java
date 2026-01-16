package com.forensic.engine.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    // This uses settings from your application.yml automatically
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/forensic_db");
        config.setUsername("root");
        config.setPassword("Nova");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.setMaximumPoolSize(10);
        return new HikariDataSource(config);
    }
}
