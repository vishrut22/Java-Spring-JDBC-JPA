package com.dailycodebuffer.dbdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JDBCCommandLineRunner implements CommandLineRunner {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void run(String... args) throws Exception {
        //Lets truncate table first for easy reference and multiple runs.
        jdbcTemplate.execute("truncate table ORDER_DETAILS");
        insertHardCodeData();

    }
    public void insertHardCodeData() {
        System.out.println("In JDBCCommandLineRunner For inserting hardcoded data.");
        jdbcTemplate.execute("INSERT INTO ORDER_DETAILS VALUES(1,'Mobile','customer','bengaluru')");
        System.out.println("Inserted hardcode data.");
        // go to mysql and check record
    }
}
