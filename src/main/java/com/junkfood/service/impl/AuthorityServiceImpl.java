package com.junkfood.service.impl;

import com.junkfood.entity.Account;
import com.junkfood.entity.Authority;
import com.junkfood.reponsitory.AccountReponsitory;
import com.junkfood.reponsitory.AuthorityReponsitory;
import com.junkfood.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityReponsitory authorityReponsitory;

    @Autowired
    AccountReponsitory accountReponsitory;

    @Override
    public List<Authority> findAuthoritiesOfAdmintrators(){
        List<Account> accounts = accountReponsitory.getAdminstrators();
        return authorityReponsitory.authoritiesOf(accounts);
    }

    @Override
    public List<Authority> findAll(){
        return authorityReponsitory.findAll();
    }

    @Override
    public Authority create(Authority authority){
        return authorityReponsitory.save(authority);
    }

    @Override
    public void delete(Integer id){
        authorityReponsitory.deleteById(id);
    }

}
