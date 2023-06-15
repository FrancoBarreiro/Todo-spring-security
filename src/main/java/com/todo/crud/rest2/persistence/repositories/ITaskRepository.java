package com.todo.crud.rest2.persistence.repositories;


import com.todo.crud.rest2.models.Task;
import com.todo.crud.rest2.persistence.repositories.entities.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskEntity,Long> {

    List<TaskEntity> findAllByOrderById();

}
