package com.ericnascimet.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public String testSecurity() {
        return "user e admin acessa essa chamada";
    }
}
