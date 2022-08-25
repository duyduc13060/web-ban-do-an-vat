package com.junkfood.rest.controller;

import com.junkfood.entity.Authority;
import com.junkfood.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {

    @Autowired
    AuthorityService authorityService;

    @GetMapping
    public List<Authority> findAll(@RequestParam("admin")Optional<Boolean> admin){
        if(admin.orElse(false)){
            return authorityService.findAuthoritiesOfAdmintrators();
        }
        return authorityService.findAll();
    }

    @PostMapping
    public Authority post(@RequestBody Authority authority){
        return authorityService.create(authority);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        authorityService.delete(id);
    }

}
