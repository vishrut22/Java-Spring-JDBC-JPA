package com.dailycodebuffer.dbdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class JDBCCommandLineRunner implements CommandLineRunner {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        //Lets truncate table first for easy reference and multiple runs.
        jdbcTemplate.execute("truncate table ORDER_DETAILS");
        insertHardCodeData();
        // Lets insert data using the prepared statement with params
        insertOrderWithPreparedStatement(2, "Shoe", "dummy", "dummy");
        // In this method you have to remember the index of the parameter. think there is big insert query with 100 of values, it won't be easy to remember that.

        // this is prepared statement example where we define the parameters using name and set it in query.
        // to do so we used named param jdbc template
        insertOrder(3, "Shirt", "dummy", "dummy");

        // Lets delete order by order id.
        deleteOrder(3);

        // Insert orders in batch say we want to insert multiple rows in one go.
        insertBatchOrder(5);
    }
    public void insertHardCodeData() {
        System.out.println("In JDBCCommandLineRunner For inserting hardcoded data.");
        jdbcTemplate.execute("INSERT INTO ORDER_DETAILS VALUES(1,'Mobile','customer','bengaluru')");
        System.out.println("Inserted hardcode data.");
        // go to mysql and check record
    }

    public void insertOrder(int orderId, String itemName, String customerName, String address) {
        String query = "INSERT INTO ORDER_DETAILS VALUES(:ORDER_ID,:ITEM_NAME,:CUSTOMER_NAME,:ADDRESS)";
        Map param = new HashMap();
        param.put("ORDER_ID", orderId);
        param.put("ITEM_NAME", itemName);
        param.put("CUSTOMER_NAME", customerName);
        param.put("ADDRESS", address);
        int rowCount = namedParameterJdbcTemplate.update(query, param);
        System.out.println("Number of rows affected :"+ rowCount);
    }

    public void insertOrderWithPreparedStatement(int orderId, String itemName, String customerName, String address) {
        String query = "INSERT INTO ORDER_DETAILS VALUES(?,?,?,?)";

        int rowCount = jdbcTemplate.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, orderId);
                ps.setString(2, itemName);
                ps.setString(3, customerName);
                ps.setString(4, address);
            }
        });
        System.out.println("Number of rows affected :"+ rowCount);
    }

    public void deleteOrder(int orderId) {
        String query = "DELETE FROM ORDER_DETAILS WHERE ORDER_ID=:ORDER_ID";
        Map param = new HashMap();
        param.put("ORDER_ID", orderId);
        int rowCount = namedParameterJdbcTemplate.update(query, param);
        System.out.println("Number of rows deleted :"+ rowCount);

    }
    public void insertBatchOrder(int totalCount) {
        String query = "INSERT INTO ORDER_DETAILS VALUES(:ORDER_ID,:ITEM_NAME,:CUSTOMER_NAME,:ADDRESS)";
        Map[] totalParams = new Map[totalCount];
        IntStream.range(0, totalCount).forEach(i -> {
            Map param = new HashMap();
            param.put("ORDER_ID", i);
            param.put("ITEM_NAME", "itemName"+i);
            param.put("CUSTOMER_NAME", "customerName"+i);
            param.put("ADDRESS", "address"+i);
            totalParams[i] = param;
        });
        int[] rowCount = namedParameterJdbcTemplate.batchUpdate(query, totalParams);
        System.out.println("Number of rows affected :"+ rowCount.length);
    }
}
