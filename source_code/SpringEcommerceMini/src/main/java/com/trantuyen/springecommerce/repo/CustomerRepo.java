package com.trantuyen.springecommerce.repo;

import com.trantuyen.springecommerce.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepo extends PagingAndSortingRepository<Customer, Long>, CrudRepository<Customer, Long> {
    List<Customer> findByName(@Param("name") String name);

    Optional<Customer> findByUsername(@Param("username") String username);

}