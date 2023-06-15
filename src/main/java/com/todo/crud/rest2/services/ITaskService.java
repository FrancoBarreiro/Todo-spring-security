package com.todo.crud.rest2.services;

import com.todo.crud.rest2.exceptions.ResourceNotFoundException;
import com.todo.crud.rest2.models.PersonalTask;
import com.todo.crud.rest2.models.Task;
import com.todo.crud.rest2.persistence.repositories.entities.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITaskService {

    List<Task> getTasks();

    Page<TaskEntity> getTasksPaged(Pageable pageable);

    Task getTaskById(long id) throws ResourceNotFoundException;
    Task saveTask(Task task);
    Task updateTask(Task task);
    void deleteTask(long id);

}
