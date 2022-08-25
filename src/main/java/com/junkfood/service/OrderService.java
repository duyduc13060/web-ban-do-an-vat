package com.junkfood.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.junkfood.entity.Order;

import java.util.List;

public interface OrderService {

    Order create(JsonNode orderData);

    Order findById(Integer integer);

    List<Order> findByUsername(String username);
}
