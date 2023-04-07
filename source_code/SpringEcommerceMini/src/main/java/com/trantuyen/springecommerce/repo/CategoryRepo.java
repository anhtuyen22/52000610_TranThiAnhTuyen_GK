package com.trantuyen.springecommerce.repo;
import com.trantuyen.springecommerce.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryRepo extends PagingAndSortingRepository<Category, Long>, CrudRepository<Category, Long> {
}