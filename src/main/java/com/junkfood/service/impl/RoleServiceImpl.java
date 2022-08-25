package com.junkfood.service.impl;

import com.junkfood.entity.Role;
import com.junkfood.reponsitory.RoleReponsitory;
import com.junkfood.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleReponsitory roleReponsitory;

    @Override
    public List<Role> findAll(){
       return roleReponsitory.findAll();
    }

}
