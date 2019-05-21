package com.mma.db;

import org.springframework.data.repository.CrudRepository;

import com.mma.business.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
