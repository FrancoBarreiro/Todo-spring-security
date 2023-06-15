package com.todo.crud.rest2.controllers;

import com.todo.crud.rest2.models.User;
import com.todo.crud.rest2.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@RestController
public class AuthController {
    @Autowired
    private IUserService userService;

    /*@GetMapping
    public boolean verifyUser(@RequestBody User user) {
        return userService.verifyUser(user);
    }*/
}
