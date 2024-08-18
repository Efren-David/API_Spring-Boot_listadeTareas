package com.ToDo.RestFull.Mapper;

import com.ToDo.RestFull.Entity.Task;
import com.ToDo.RestFull.Enum.TaskStatus;
import com.ToDo.RestFull.Service.dto.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task> {
    @Override
    public Task map(TaskInDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ONE_TIME);
        return task;
    }
    }

