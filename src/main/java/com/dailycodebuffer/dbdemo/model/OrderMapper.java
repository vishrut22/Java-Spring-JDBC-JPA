package com.dailycodebuffer.dbdemo.model;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderMapper implements RowMapper<OrderDetails> {
    @Override
    public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(rs.getInt("ORDER_ID"));
        orderDetails.setItemName(rs.getString("ITEM_NAME"));
        orderDetails.setCustomerName(rs.getString("CUSTOMER_NAME"));
        orderDetails.setAddress(rs.getString("ADDRESS"));
        return orderDetails;
    }
}
