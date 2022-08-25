package com.junkfood.service;

import com.junkfood.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer integer);

    List<Product> findByCategoryId(Integer cid);

    Product create(Product product);

    Product update(Product product);

    void delete(Integer id);
}
