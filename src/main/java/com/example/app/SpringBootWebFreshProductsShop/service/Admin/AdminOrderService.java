package com.example.app.SpringBootWebFreshProductsShop.service.Admin;

import com.example.app.SpringBootWebFreshProductsShop.entity.Order;
import com.example.app.SpringBootWebFreshProductsShop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AdminOrderService {

    @Autowired
    OrderRepository repository;

    public List<Order> getAll() {
        Iterable<Order> iterable = repository.findAll();
        List<Order> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(order -> new Order(order.getId(),
                                order.getOrderCode(),
                                order.getUserName(),
                                order.getUserPhone(),
                                order.getUserEmail(),
                                order.getContent(),
                                order.getStatus()))
                        .toList();
        return new ArrayList<>(list);
    }
}
