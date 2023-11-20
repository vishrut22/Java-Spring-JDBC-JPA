package com.dailycodebuffer.dbdemo.service;

import com.dailycodebuffer.dbdemo.model.OrderEntity;
import com.dailycodebuffer.dbdemo.repository.OrderJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class TransactionDemoService {
    @Autowired
    private OrderJPARepository orderJPARepository;

    @Autowired
    private TransactionChainDemoService transactionChainDemoService;

    @Transactional  //If you dont add this notice the result and then add it and see the difference
    public void transactionDemo() {
        //Insert some data
        OrderEntity order = new OrderEntity();
        order.setCustomerName("transaction");
        order.setOrderId(1000);
        order.setItemName("transaction_item");
        order.setAddress("some");
        orderJPARepository.save(order);
        throw new RuntimeException();

    }

    @Transactional(rollbackFor = SQLException.class) // by default checked exception are not taken care by transactional annotations and for that we need to specify this attribute to ensure rollback happens
    // @Transactional   //- try this and apply above
    public void transactionDemoWithSpecificException() throws SQLException {
        //Insert some data
        OrderEntity order = new OrderEntity();
        order.setCustomerName("transaction");
        order.setOrderId(1000);
        order.setItemName("transaction_item");
        order.setAddress("some");
        orderJPARepository.save(order);
        throw new SQLException();
    }

    @Transactional
    public void transactionChainDemo() {
        //Insert some data
        OrderEntity order = new OrderEntity();
        order.setCustomerName("transaction");
        order.setOrderId(1000);
        order.setItemName("transaction_item");
        order.setAddress("some");
        orderJPARepository.save(order);
        transactionChainDemoService.transaction_Required();
        throw new RuntimeException();
    }

    @Transactional
    public void transactionChainDemo_RequiresNew() {
        //Insert some data
        OrderEntity order = new OrderEntity();
        order.setCustomerName("transaction");
        order.setOrderId(1000);
        order.setItemName("transaction_item");
        order.setAddress("some");
        orderJPARepository.save(order);
        transactionChainDemoService.transaction_Requires_new();
        throw new RuntimeException();
    }
}
