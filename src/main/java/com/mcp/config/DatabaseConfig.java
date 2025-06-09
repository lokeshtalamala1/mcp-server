package com.mcp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Autowired
    private Environment env;

    public void testConnection() {
        try {
            DataSource dataSource = new DriverManagerDataSource(
                env.getProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/mcp"),
                env.getProperty("spring.datasource.username", "root"),
                env.getProperty("spring.datasource.password", "")
            );

            Connection connection = dataSource.getConnection();
            if (connection != null) {
                System.out.println("Successfully connected to the database!");
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            System.err.println("Error: " + e.getMessage());
        }
    }
} 