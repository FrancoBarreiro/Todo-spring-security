package com.todo.crud.rest2.persistence.repositories;

import com.todo.crud.rest2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
