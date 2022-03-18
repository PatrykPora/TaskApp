package pl.patrykpora.taskapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import pl.patrykpora.taskapp.dto.CommentDto;
import pl.patrykpora.taskapp.dto.TaskDto;
import pl.patrykpora.taskapp.service.TaskService;
import pl.patrykpora.taskapp.service.UserService;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;
    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public String showTasks(Model model) {
        model.addAttribute("modelTaskList", taskService.getAll());
        return "/tasksTemplate";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable("id") long id, Model model){
        try {
            TaskDto taskDto = taskService.findById(id);
            model.addAttribute("task", taskDto);
            model.addAttribute("comment", new CommentDto());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find task");
        }
        return "taskTemplate";
    }

    @GetMapping("/create")
    public String createTask(Model model) {
        model.addAttribute("task", new TaskDto());
        return "taskCreate";
    }

    @PostMapping("/create")
    public String createTask(@Valid TaskDto taskDto, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("task", taskDto);
            return "taskCreate";
        }
        taskService.create(taskDto);
        return "redirect:/task";
    }
}
