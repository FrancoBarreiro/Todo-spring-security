package com.todo.crud.rest2.services;

import com.todo.crud.rest2.exceptions.ResourceNotFoundException;
import com.todo.crud.rest2.models.User;
import com.todo.crud.rest2.persistence.repositories.IUserRepository;
import com.todo.crud.rest2.persistence.repositories.entities.UserEntity;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
/*
    @Autowired
    private IUserRepository userRepo;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userRepo.save(userEntity);
        user.setId(userEntity.getId());
        return user;
    }

    @Override
    public User getUserById(long id) {
        UserEntity userEntity = userRepo.findById(id).get();
        User user = new User(userEntity.getId(), userEntity.getUserName(), userEntity.getPassword());
        return user;
    }

    @Override
    public boolean verifyUser(User user) {
        List<User> checkUser = userRepo.findByUserNameLike(user.getUserName())
                .stream()
                .map(userEntity -> new User(userEntity.getId(), userEntity.getUserName(), userEntity.getPassword()))
                .collect(Collectors.toList());

        User userFound = checkUser.stream()
                .filter(u -> u.getUserName().equals(user.getUserName()))
                .findFirst()
                .orElse(null);

        String passwordHashed = userFound.getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(passwordHashed, user.getPassword());
    }*/
}
