package com.dailycodebuffer.dbdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JDBCCommandLineRunner implements CommandLineRunner {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("In JDBCCommandLineRunner");

    }
}
