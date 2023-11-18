package com.dailycodebuffer.dbdemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetails {
    private int orderId;
    private String itemName;
    private String customerName;
    private String address;

}
