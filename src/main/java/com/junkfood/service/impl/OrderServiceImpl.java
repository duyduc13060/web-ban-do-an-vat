package com.junkfood.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junkfood.entity.Order;
import com.junkfood.entity.OrderDetail;
import com.junkfood.reponsitory.OrderDetailReponsitory;
import com.junkfood.reponsitory.OrderReponsitory;
import com.junkfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderReponsitory orderReponsitory;

    @Autowired
    OrderDetailReponsitory orderDetailReponsitory;

    @Override
    public Order create(JsonNode orderData){
        ObjectMapper mapper = new ObjectMapper();

        Order order = mapper.convertValue(orderData,Order.class);
        System.out.println(order+"------------");

        orderReponsitory.save(order);

        System.out.println(orderReponsitory.save(order)+"faafaaf");

        TypeReference<List<OrderDetail>> typeReference = new TypeReference<>(){};

        // đọc json chuyển sang List<OrderDetail>
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"),typeReference)
                .stream().peek(d-> d.setOrder(order)).collect(Collectors.toList());
        orderDetailReponsitory.saveAll(details);

        return order;
    }

    @Override
    public Order findById(Integer integer) {
        return orderReponsitory.findById(integer).get();
    }

    @Override
    public List<Order> findByUsername(String username){
        return orderReponsitory.findByUsername(username);
    }

}
