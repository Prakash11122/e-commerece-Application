package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.Products;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductDao")
public interface ProductDao extends PagingAndSortingRepository<Products,Long> {
    @Query("from Products as c where c.category.Cid =:cat_id")
    List<Products> findProductsByCategory(@Param("cat_id") Long id);
}
