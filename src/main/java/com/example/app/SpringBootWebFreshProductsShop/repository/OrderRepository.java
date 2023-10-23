package com.example.app.SpringBootWebFreshProductsShop.repository;

import com.example.app.SpringBootWebFreshProductsShop.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
