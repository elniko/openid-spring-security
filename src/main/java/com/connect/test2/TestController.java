package com.connect.test2;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public final String home() {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
       // final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Welcome, " + username;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String users() {
        return "users";
    }

    @GetMapping("/admins")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admins() {
        return "admins";
    }

}
