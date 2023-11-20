package com.dailycodebuffer.dbdemo.service;

import com.dailycodebuffer.dbdemo.model.OrderEntity;
import com.dailycodebuffer.dbdemo.repository.OrderJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionChainDemoService {

    // https://www.baeldung.com/spring-transactional-propagation-isolation

    @Autowired
    private OrderJPARepository orderJPARepository;

    /**
     * Only explain mostly used main transaction that is REQUIRED which is by default
     * and REQUIRES_NEW. Others we can explain as theoriticoals
     */

    // REQUIRED means anything happens here will be in same transaction as previously
    // if caller method rollbacks this also gets rolledback
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_Required() {
        OrderEntity order = new OrderEntity();
        order.setCustomerName("transaction");
        order.setOrderId(2000);
        order.setItemName("transaction_chain_item");
        order.setAddress("some");
        orderJPARepository.save(order);
    }

    // Requires new - if any transaction exists it creates new transsaction
    // since this is new transaction  both caller and this transaction are independent and didnt impact rollback part to each other.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transaction_Requires_new() {
        OrderEntity order = new OrderEntity();
        order.setCustomerName("transaction");
        order.setOrderId(2001);
        order.setItemName("transaction_chain_item");
        order.setAddress("some");
        orderJPARepository.save(order);
    }
}
