package com.dailycodebuffer.dbdemo.model;

public class OrderDetails {
    private int orderId;
    private String itemName;
    private String customerName;
    private String address;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId=" + orderId +
                ", itemName='" + itemName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
