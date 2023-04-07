package com.trantuyen.springecommerce.repo;

import com.trantuyen.springecommerce.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "carts", path = "carts")
public interface CartRepo extends PagingAndSortingRepository<Cart, Long>, CrudRepository<Cart, Long> {

    @Query("SELECT c from Cart c where c.customerByCustomerId.id = :i")
    List<Cart> findCartsByCustomerByCustomerId(@Param("i") Long id);
}