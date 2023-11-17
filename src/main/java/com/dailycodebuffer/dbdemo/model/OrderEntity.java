package com.dailycodebuffer.dbdemo.model;

//Notice the change previously with spring boot it was having java ee now with spring boot 3 it is jakarta ee
import jakarta.persistence.*;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderEntity {
    @Id
    @Column(name = "ORDER_ID", nullable = false)
    private int orderId;
    @Column(name = "ITEM_NAME")
    private String itemName;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "ADDRESS")
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
