package com.ToDo.RestFull.Controller;


import com.ToDo.RestFull.Entity.Task;
import com.ToDo.RestFull.Enum.TaskStatus;
import com.ToDo.RestFull.Service.TaskService;
import com.ToDo.RestFull.Service.dto.TaskInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/createTask")
    public ResponseEntity<String> createTask(@RequestBody TaskInDTO taskInDTO){
        Task createdTask = taskService.createTask(taskInDTO);
        String message = "Tarea creada con éxito. ID: " + createdTask.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/showTasks")
    public List<Task> findAll(){
        return taskService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByStatus(@PathVariable("status") TaskStatus status){
        return taskService.findAllByTaskStatus(status);
    }

    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<String> markAsFiniched(@PathVariable("id") Long id){
        taskService.updateTaskAsFinished(id);
        String message = "La tarea con ID " + id + " ha sido marcada como completada.";
        return ResponseEntity.ok(message);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        taskService.deleteById(id);
        String message = "Eliminado correctamente.";
        return ResponseEntity.ok(message);
    }



}
