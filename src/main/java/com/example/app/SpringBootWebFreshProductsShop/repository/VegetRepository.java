package com.example.app.SpringBootWebFreshProductsShop.repository;

import com.example.app.SpringBootWebFreshProductsShop.entity.Veget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VegetRepository extends CrudRepository<Veget, Long> {
}
