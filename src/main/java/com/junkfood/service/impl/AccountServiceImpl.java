package com.junkfood.service.impl;

import com.junkfood.entity.Account;
import com.junkfood.reponsitory.AccountReponsitory;
import com.junkfood.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountReponsitory accountreponsitory;

    @Override
    public Optional<Account> findById(String username) {
        return accountreponsitory.findById(username);
    }

    @Override
    public List<Account> getAdminstrators(){
        return accountreponsitory.getAdminstrators();
    }
    @Override
    public List<Account> findAll(){
        return accountreponsitory.findAll();
    }
}
