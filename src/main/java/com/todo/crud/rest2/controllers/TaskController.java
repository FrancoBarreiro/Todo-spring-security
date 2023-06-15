package com.todo.crud.rest2.controllers;

import com.todo.crud.rest2.exceptions.ResourceNotFoundException;
import com.todo.crud.rest2.models.Task;
import com.todo.crud.rest2.persistence.repositories.entities.TaskEntity;
import com.todo.crud.rest2.services.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
//@CrossOrigin(origins = {"http://localhost:4200"})
public class TaskController {
    @Autowired
    private ITaskService taskService;


    @GetMapping("/page/{page}")
    public ResponseEntity<?> getTasksPaged(@PathVariable int page) {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(page,5,sort);
        Page<TaskEntity> tasks = taskService.getTasksPaged(pageable);
        return ResponseEntity.ok(tasks);
    }


    /**
     * Obtiene todas las Tasks registradas.
     *
     * @return ResponseEntity con la lista de todas las Tasks
     */
    @GetMapping
    public ResponseEntity<?> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }





    /**
     * Obtiene una Task específica basada en su ID.
     *
     * @param id indica el ID de la Task a obtener
     * @return ResponseEntity con la Task obtenida
     * @throws ResourceNotFoundException en caso de no encontrar ninguna Task con el ID proporcionado
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable long id) throws ResourceNotFoundException {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Guardar Task en base de datos.
     *
     * @param task la Task a guardar
     * @return ResponseEntity con la Task que se guardó
     */
    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody @Valid Task task) {
        Task taskSaved = taskService.saveTask(task);
        //Obteniendo URL del servicio.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(task);
    }

    /**
     * Actualizar Task de la base de datos.
     *
     * @param task es la Task que se quiere actualizar
     * @param type indica el tipo de Task a guardar
     * @return ResponseEntity con la Task que se actualizó
     */

    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return ResponseEntity.ok(task);
    }

    /**
     * Eliminar Task de la base de datos.
     *
     * @param id indica el ID de la Task que se quiere eliminar
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
