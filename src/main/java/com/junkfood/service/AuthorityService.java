package com.junkfood.service;

import com.junkfood.entity.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findAuthoritiesOfAdmintrators();

    List<Authority> findAll();

    Authority create(Authority authority);

    void delete(Integer id);
}
