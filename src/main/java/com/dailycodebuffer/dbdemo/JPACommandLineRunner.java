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

import java.util.*;
import java.util.stream.IntStream;

@Profile("jpa")
@Component
//For each JPA , check the query in logs which is getting fired.
// Notice that for save and delete it first selects and then if found then execute that operation.
// In JPA feature : Explain that it reduces boiler plate code by providing different crud operations inbuilt.
//Additionally you dont explict handles on entity manager .Everything got wirted automatically.
public class JPACommandLineRunner implements CommandLineRunner {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderJPARepository orderJPARepository;

    @Override
    public void run(String... args) throws Exception {
       // orderRepository.getAllOrders();
      //  orderRepository.insertAndGet();
        System.out.println("Deleting all records first.");
        orderJPARepository.deleteAll();


        //Lets explore different operations
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(13);
        orderEntity.setAddress("JPA_ADDRESS");
        orderEntity.setItemName("JPA_ITEM");
        orderEntity.setCustomerName("JPA_CUSTOMER");
        System.out.println("Inserting new record.");
        //Save the data , save method used for both insert new data or update the data identified by id
        orderJPARepository.save(orderEntity);
        // find data by order id 2 and then update the same
        //Notice the logs how queries getting printed
        Optional<OrderEntity> id = orderJPARepository.findById(13);
        System.out.println("Order by id :"+id.get());
        OrderEntity entity = id.get();
        entity.setCustomerName("UPDATE");
        orderJPARepository.save(entity);
        System.out.println("Delete record by Id");
        // In jpa repo we can delete it by id or we can provide list of ids for deletion.
        orderJPARepository.deleteById(13);

        // Save all helps use to do batch operations.
        List<OrderEntity> ls = new ArrayList<>();
        IntStream.range(20,30).forEach(index-> {
            OrderEntity orderEntityBatch = new OrderEntity();
            orderEntityBatch.setOrderId(index);
            orderEntityBatch.setAddress("JPA_ADDRESS"+index);
            orderEntityBatch.setItemName("JPA_ITEM"+index);
            orderEntityBatch.setCustomerName("JPA_CUSTOMER");
            ls.add(orderEntityBatch);
        });
        System.out.println("Inserting all bunch of records.");
        orderJPARepository.saveAll(ls);

        //Find All data
        System.out.println("Finding all records");
        Iterable<OrderEntity> orderEntities = orderJPARepository.findAll();
        System.out.println("orderEntities received using inbuilt JPA : "+orderEntities);
        //Similarly, we can delete it as well
        orderJPARepository.deleteAllById(Arrays.asList(15,20,22,25));
    }
}
