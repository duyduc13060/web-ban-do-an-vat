package com.junkfood.service;

import com.junkfood.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> findById(String username);

    List<Account> getAdminstrators();

    List<Account> findAll();
}
