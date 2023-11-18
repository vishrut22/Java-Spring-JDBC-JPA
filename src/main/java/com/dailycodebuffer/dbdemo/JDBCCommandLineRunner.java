package com.dailycodebuffer.dbdemo;

import com.dailycodebuffer.dbdemo.model.OrderDetails;
import com.dailycodebuffer.dbdemo.model.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
@Profile("!jpa")
@Component
// this is also creating constructor and on to it add auto wire so automatically injection happening
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JDBCCommandLineRunner implements CommandLineRunner {
    //@Autowired
    JdbcTemplate jdbcTemplate;

    //@Autowired
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

        //Select all orders
        selectOrder();

        //Select single order and full row of that order
        selectSingleOrder(4);

        //Select single order and which does not exists.
        selectSingleOrder(100);

        //Select items of all orders
        selectSingleColumn();
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

    public void selectOrder() {
        List<OrderDetails> ls = jdbcTemplate.query("SELECT * FROM ORDER_DETAILS", new OrderMapper());
        System.out.println("All orders are :"+ls);
    }

    public void selectSingleOrder(int orderId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ORDER_ID", orderId); // One more way of passing arguments to query.
        // previously we seen Map and this is another way using mapsqlparamsource
        try {

            OrderDetails order = namedParameterJdbcTemplate.queryForObject("SELECT * FROM ORDER_DETAILS WHERE ORDER_ID=:ORDER_ID", mapSqlParameterSource, new OrderMapper());
            System.out.println("Order is :"+order);
            // When we are dealing with queryforobject it expects only 1 result not more than that not lesser than that
            // to handle no result we can ideally catch this exception and tackle.
        } catch (EmptyResultDataAccessException e ) {
            System.out.println("No record found.");
        }
    }

    public void selectSingleColumn() {
        List<String> items = jdbcTemplate.queryForList("SELECT ITEM_NAME FROM ORDER_DETAILS",  String.class);
        System.out.println("Items :"+items);
    }
}
