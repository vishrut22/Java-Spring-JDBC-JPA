package com.dailycodebuffer.dbdemo.model;

//Notice the change previously with spring boot it was having java ee now with spring boot 3 it is jakarta ee
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
// Similar to record in java 17 , we can reduce the boiler platecode by mentioning @data we are telling
// to add getter setters and allargs constructor. this is the feature of lombok.
@Data
@ToString
@Entity
@Table(name = "ORDER_DETAILS")
public class OrderEntity {
    @ToString.Exclude // We can also execlude this specific field from tostring.
    @Id
    @Column(name = "ORDER_ID", nullable = false)
    private int orderId;
    @Column(name = "ITEM_NAME")
    private String itemName;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "ADDRESS")
    private String address;

}
