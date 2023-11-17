package com.dailycodebuffer.dbdemo.repository;

import com.dailycodebuffer.dbdemo.model.OrderEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

//Everything in this class is manual meaning , we are getting entity manager and doing jpa operations
@Repository
// Since we have insert/update operation which is transcations we need annotate this class as transactional
// will go deep later on.
@Transactional
public class OrderRepository {
    //Consider we are having spring jpa starter so we got connection here automatically preconfigured.
    @PersistenceContext
    private EntityManager entityManager;

    //We are not executing queries manually here.
    public void getAllOrders() {
        List<OrderEntity> orderEntities = entityManager.createQuery("FROM OrderEntity", OrderEntity.class)
                .getResultList();
        System.out.println("orderEntities :"+ orderEntities);
    }

    public void insertAndGet() {

        OrderEntity order = new OrderEntity();
        order.setOrderId(11);
        order.setAddress("JPA Address");
        order.setCustomerName("JPA");
        order.setItemName("Entity");

        entityManager.persist(order);
        OrderEntity insertedOrder = entityManager.find(OrderEntity.class, 11);
        System.out.println("insertedOrder :"+insertedOrder);
    }
}
