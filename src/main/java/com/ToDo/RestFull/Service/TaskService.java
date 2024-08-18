package com.ToDo.RestFull.Service;

import com.ToDo.RestFull.Entity.Task;
import com.ToDo.RestFull.Enum.TaskStatus;
import com.ToDo.RestFull.Exception.ExceptionHandler;
import com.ToDo.RestFull.Exception.ToDoExceptions;
import com.ToDo.RestFull.Mapper.IMapper;
import com.ToDo.RestFull.Repository.TaskRepository;
import com.ToDo.RestFull.Service.dto.TaskInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {


    private final TaskRepository taskRepository;
    private final IMapper<TaskInDTO, Task> mapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, IMapper<TaskInDTO, Task> mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus) {
        return taskRepository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        taskRepository.markTaskAsFinished(id);
    }

    public void deleteById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        taskRepository.deleteById(id);
    }
}
