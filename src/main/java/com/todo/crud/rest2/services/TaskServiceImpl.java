package com.todo.crud.rest2.services;


import com.todo.crud.rest2.exceptions.ResourceNotFoundException;
import com.todo.crud.rest2.models.PersonalTask;
import com.todo.crud.rest2.models.Task;
import com.todo.crud.rest2.models.WorkTask;
import com.todo.crud.rest2.persistence.repositories.ITaskRepository;
import com.todo.crud.rest2.persistence.repositories.entities.PersonalTaskEntity;
import com.todo.crud.rest2.persistence.repositories.entities.TaskEntity;
import com.todo.crud.rest2.persistence.repositories.entities.WorkTaskEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private ITaskRepository taskRepo;
    ModelMapper modelMapper = new ModelMapper();

    /**
     * Recuperar todas las Tasks.
     *
     * @return Lista de Tasks
     */
    @Override
    public List<Task> getTasks() {
        List<TaskEntity> listTasksEntity = taskRepo.findAllByOrderById();

        List<Task> tasks = listTasksEntity.stream()
                .map(taskEntity -> {
                    if (taskEntity instanceof PersonalTaskEntity) {
                        return modelMapper.map(taskEntity, PersonalTask.class);
                    } else if (taskEntity instanceof WorkTaskEntity) {
                        return modelMapper.map(taskEntity, WorkTask.class);

                    } else {
                        return null;
                    }

                }).collect(Collectors.toList());
        return tasks;
    }


    public Page<TaskEntity> getTasksPaged(Pageable pageable){
        return taskRepo.findAll(pageable);
    }




    /**
     * Encontrar una Task basada en su ID.
     *
     * @param id el ID de la Task a encontrar
     * @return la Task correspondiente al ID proporcionado
     * @throws ResourceNotFoundException si no se encuentra ninguna Task con el ID proporcionado
     */
    public Task getTaskById(long id) throws ResourceNotFoundException {

        TaskEntity taskFound = taskRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));

        if (taskFound instanceof PersonalTaskEntity) {
            Task task = new PersonalTask();

            task.setId(taskFound.getId());
            task.setTitle(taskFound.getTitle());
            task.setDescription(taskFound.getDescription());
            task.setStatus(Task.EnumStatus.valueOf(taskFound.getStatus().name()));
            task.setTaskType(Task.EnumType.valueOf(taskFound.getTaskType().name()));
            ((PersonalTask) task).setCategory(PersonalTask.Category
                    .valueOf(((PersonalTaskEntity) taskFound).getCategory().name()));
            return task;
        } else {
            Task task = new WorkTask();

            task.setId(taskFound.getId());
            task.setTitle(taskFound.getTitle());
            task.setDescription(taskFound.getDescription());
            task.setStatus(Task.EnumStatus.valueOf(taskFound.getStatus().name()));
            task.setTaskType(Task.EnumType.valueOf(taskFound.getTaskType().name()));
            ((WorkTask) task).setProject(String.valueOf(((WorkTaskEntity) taskFound).getProject()));

            return task;
        }
    }

    /**
     * Guardar Task en base de datos.
     *
     * @param task la Task a guardar
     * @return la Task que se guardó
     */
    public Task saveTask(Task task) {

        if (task instanceof PersonalTask) {
            TaskEntity personalTaskEntity = new PersonalTaskEntity();

            personalTaskEntity.setTitle(task.getTitle());
            personalTaskEntity.setDescription(task.getDescription());
            ((PersonalTaskEntity) personalTaskEntity).setCategory(PersonalTaskEntity.Category.valueOf(((PersonalTask) task).getCategory().name()));
            personalTaskEntity.setStatus(PersonalTaskEntity.EnumStatusEntity.valueOf(task.getStatus().name()));
            personalTaskEntity.setTaskType(PersonalTaskEntity.EnumTypeEntity.valueOf(task.getTaskType().name()));

            taskRepo.save(personalTaskEntity);
            task.setId(personalTaskEntity.getId());
            return task;

        } else {
            WorkTaskEntity workTaskEntity = new WorkTaskEntity();

            workTaskEntity.setTitle(task.getTitle());
            workTaskEntity.setDescription(task.getDescription());
            workTaskEntity.setProject(((WorkTask) task).getProject());
            workTaskEntity.setStatus(WorkTaskEntity.EnumStatusEntity.valueOf(task.getStatus().name()));
            workTaskEntity.setTaskType(WorkTaskEntity.EnumTypeEntity.valueOf(task.getTaskType().name()));

            taskRepo.save(workTaskEntity);
            task.setId(workTaskEntity.getId());
            return task;
        }
    }

    /**
     * Actualizar Task de la base de datos.
     *
     * @param task es la Task que se quiere actualizar
     * @return la Task que se actualizó
     */
    @Override
    public Task updateTask(Task task) {
        TaskEntity taskFound = taskRepo.findById(task.getId()).orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));

        if (taskFound instanceof PersonalTaskEntity) {
            taskFound.setTitle(task.getTitle());
            taskFound.setDescription(task.getDescription());
            taskFound.setStatus(TaskEntity.EnumStatusEntity.valueOf(task.getStatus().name()));
            ((PersonalTaskEntity) taskFound).setCategory(PersonalTaskEntity.Category
                    .valueOf(((PersonalTask) task).getCategory().name()));
            taskRepo.save(taskFound);
            return task;

        } else {
            taskFound.setTitle(task.getTitle());
            taskFound.setDescription(task.getDescription());
            taskFound.setStatus(TaskEntity.EnumStatusEntity.valueOf(task.getStatus().name()));
            ((WorkTaskEntity) taskFound).setProject(String.valueOf(((WorkTask) task).getProject()));
            taskRepo.save(taskFound);
            return task;
        }
    }

    /**
     * Eliminar Task de la base de datos.
     *
     * @param id indica el ID de la Task a eliminar
     */
    @Override
    public void deleteTask(long id) {
        TaskEntity taskEntity = taskRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task Not Found"));
        taskRepo.deleteById(taskEntity.getId());
    }


}
