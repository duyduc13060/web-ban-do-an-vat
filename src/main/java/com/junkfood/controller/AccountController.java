package com.junkfood.controller;

import com.junkfood.entity.Account;
import com.junkfood.reponsitory.AccountReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    AccountReponsitory accountreponsitory;

    @Autowired
    BCryptPasswordEncoder en;

    @PostMapping(value = "/account/regis")
    public String l(@RequestBody Account account){
        account.setPassword(en.encode(account.getPassword()));
        accountreponsitory.save(account);
        return "security/login";
    }
}
