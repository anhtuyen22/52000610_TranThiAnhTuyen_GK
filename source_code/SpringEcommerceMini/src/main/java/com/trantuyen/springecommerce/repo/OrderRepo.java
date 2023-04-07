package com.trantuyen.springecommerce.repo;

import com.trantuyen.springecommerce.entity.Cart;
import com.trantuyen.springecommerce.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepo extends PagingAndSortingRepository<Order, Long> , CrudRepository<Order,Long> {

    @Query("SELECT o from Order o where o.customerByCustomerId.id = :i")
    List<Order> findOrderByCustomerByCustomerId(@Param("i") Long id);
}