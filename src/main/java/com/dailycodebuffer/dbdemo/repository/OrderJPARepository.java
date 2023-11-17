package com.dailycodebuffer.dbdemo.repository;

import com.dailycodebuffer.dbdemo.model.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJPARepository extends CrudRepository<OrderEntity,Integer> {

}
