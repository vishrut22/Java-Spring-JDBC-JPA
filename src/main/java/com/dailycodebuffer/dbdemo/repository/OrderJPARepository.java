package com.dailycodebuffer.dbdemo.repository;

import com.dailycodebuffer.dbdemo.model.CustomerDetailsView;
import com.dailycodebuffer.dbdemo.model.OrderEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//Similar to crud repository we have Paging repository to allow paging
public interface OrderJPARepository extends CrudRepository<OrderEntity,Integer>, PagingAndSortingRepository<OrderEntity,Integer> {

    List<OrderEntity> findByItemName(String itemName);

    List<OrderEntity> findByCustomerNameLikeOrderByItemName(String customerName);

    // We are querying like on customer column ,  we are defining named parameter for column name
    // to set that we need to use @Param and name that parameter so it will be replaced.
    //Additionally we can also provide sort parameter to sort by column and get result
    @Query("SELECT o FROM OrderEntity o WHERE o.customerName LIKE %:customer%")
    List<OrderEntity> searchByCustomerNameLike(@Param("customer") String customerName, Sort sort);


    // Lets see how projections work meaning , we sometime have requirement to only publish certain fields of entity
    // as part of API. And thats where projection comes into picture. We will have open projection and close projection using interface.
    // https://www.baeldung.com/spring-data-jpa-projections
    List<CustomerDetailsView> findAllByItemName(String itemName);

}
