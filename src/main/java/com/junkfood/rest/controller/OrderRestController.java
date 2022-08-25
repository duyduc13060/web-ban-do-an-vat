package com.junkfood.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.junkfood.entity.Order;
import com.junkfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public Order create(@RequestBody JsonNode order){
        return orderService.create(order);
    }

}
