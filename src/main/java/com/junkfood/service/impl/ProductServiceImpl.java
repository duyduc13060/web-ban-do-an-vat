package com.junkfood.service.impl;

import com.junkfood.entity.Product;
import com.junkfood.reponsitory.ProductReponsitory;
import com.junkfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductReponsitory productReponsitory;

    @Override
    public List<Product> findAll() {
        return productReponsitory.findAll();
    }

    @Override
    public Product findById(Integer integer) {
        return productReponsitory.findById(integer).get();
    }

    @Override
    public List<Product> findByCategoryId(Integer cid){
        return productReponsitory.findByCategoryId(cid);
    }

    @Override
    public Product create(Product product){
        return productReponsitory.save(product);
    }

    @Override
    public Product update(Product product){
        return productReponsitory.save(product);
    }

    @Override
    public void delete(Integer id){
        productReponsitory.deleteById(id);
    }

}
