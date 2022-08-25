package com.junkfood.rest.controller;

import com.junkfood.entity.Account;
import com.junkfood.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {

    @Autowired
    AccountService accountService;

    public List<Account> getAccounts(@RequestParam("admin")Optional<Boolean> admin){
        if(admin.orElse(false)){
            return accountService.getAdminstrators();
        }
        return accountService.findAll();
    }

}
