package com.dailycodebuffer.dbdemo;

import com.dailycodebuffer.dbdemo.model.OrderDetails;
import com.dailycodebuffer.dbdemo.model.OrderEntity;
import com.dailycodebuffer.dbdemo.repository.OrderJPARepository;
import com.dailycodebuffer.dbdemo.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Profile("jpa")
@Component
public class JPACommandLineRunner implements CommandLineRunner {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderJPARepository orderJPARepository;

    @Override
    public void run(String... args) throws Exception {
       // orderRepository.getAllOrders();
      //  orderRepository.insertAndGet();
        Iterable<OrderEntity> orderEntities = orderJPARepository.findAll();
        System.out.println("orderEntities received using inbuilt JPA : "+orderEntities);

    }
}
