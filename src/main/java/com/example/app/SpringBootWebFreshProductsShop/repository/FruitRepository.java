package com.example.app.SpringBootWebFreshProductsShop.repository;

import com.example.app.SpringBootWebFreshProductsShop.entity.Fruit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FruitRepository extends CrudRepository<Fruit, Long> {
    List<Fruit> findAll();
}
