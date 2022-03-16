package pl.patrykpora.taskapp.mapper;

import pl.patrykpora.taskapp.dto.TaskDto;
import pl.patrykpora.taskapp.entity.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static TaskDto map(Task task) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TaskDto dto = new TaskDto();
        dto.setDescription(task.getDescription());
        dto.setId(task.getId());
        dto.setNameOfTask(task.getNameOfTask());
        dto.setTypeOfTask(task.getTypeOfTask());
        dto.setCreatedAt(dateFormat.format(task.getCreatedAt()));
        return dto;
    }

    public static List<TaskDto> map(Iterable<Task> tasks) {
        List<TaskDto> dtoList = new ArrayList<>();
        for (Task task : tasks) {
            dtoList.add(map(task));
        }
        return dtoList;
    }

    public static Task map (TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setNameOfTask(taskDto.getNameOfTask());
        task.setDescription(taskDto.getDescription());
        task.setTypeOfTask(taskDto.getTypeOfTask());
        return task;
    }
}
