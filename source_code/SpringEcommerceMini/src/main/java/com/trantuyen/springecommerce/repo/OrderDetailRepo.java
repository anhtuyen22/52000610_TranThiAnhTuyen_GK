package com.trantuyen.springecommerce.repo;

import com.trantuyen.springecommerce.entity.Order;
import com.trantuyen.springecommerce.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;

@RepositoryRestResource(collectionResourceRel = "order_details", path = "order_details")
public interface OrderDetailRepo extends PagingAndSortingRepository<OrderDetail, Long>, CrudRepository<OrderDetail,Long> {

    @Query("SELECT o FROM OrderDetail o where o.orderByOrderId.id = :i")
    List<OrderDetail> findOrderDetailByOrderByOrderId(@Param("i") Long id);
}