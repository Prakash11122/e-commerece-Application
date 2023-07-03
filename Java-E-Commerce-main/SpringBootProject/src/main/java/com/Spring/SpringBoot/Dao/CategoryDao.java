package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryDao extends PagingAndSortingRepository<Category, Long> {

}
