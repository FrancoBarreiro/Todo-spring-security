package com.todo.crud.rest2.controllers;

import com.todo.crud.rest2.models.User;
import com.todo.crud.rest2.services.IUserService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
   /* @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        //Creo una password hasheado usando Argon2
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1, user.getPassword());
        user.setPassword(hash);

        User userSaved = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(user);
    }*/
}
