package com.junkfood.service.impl;

import com.junkfood.entity.Category;
import com.junkfood.reponsitory.CategoryReponsitory;
import com.junkfood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryReponsitory categoryReponsitory;



    @Override
    public List<Category> findAll() {
        return categoryReponsitory.findAll();
    }




}
