package pl.patrykpora.taskapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.patrykpora.taskapp.dto.TaskDto;
import pl.patrykpora.taskapp.entity.Task;
import pl.patrykpora.taskapp.entity.User;
import pl.patrykpora.taskapp.mapper.TaskMapper;
import pl.patrykpora.taskapp.repository.TaskRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<TaskDto> getAll() {
        User user = getLoggedInUser();
        Iterable<Task> tasks = taskRepo.findTasksByUser(user);
        List<TaskDto> taskDtos = TaskMapper.map(tasks);
        return taskDtos;
    }

    private User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }

    public TaskDto create(TaskDto taskDto) {
        Task task = TaskMapper.map(taskDto);
        User user = getLoggedInUser();
        task.setUser(user);
        task = taskRepo.save(task);
        return TaskMapper.map(task);
    }

    public void update(TaskDto taskDto) {
        if (taskDto.getId() == 0 || taskRepo.existsById(taskDto.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Task task = TaskMapper.map(taskDto);
        taskRepo.save(task);
    }

    public TaskDto findById(long id) {
        Task task = taskRepo.findById(id).orElseThrow(NoSuchElementException::new);
        TaskDto taskDto = TaskMapper.map(task);

        return taskDto;
    }

    public void delete(long id) { taskRepo.deleteById(id);}

}
