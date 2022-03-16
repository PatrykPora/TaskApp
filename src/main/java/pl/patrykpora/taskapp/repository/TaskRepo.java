package pl.patrykpora.taskapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.patrykpora.taskapp.entity.Task;
import pl.patrykpora.taskapp.entity.TypeOfTask;
import pl.patrykpora.taskapp.entity.User;

import java.util.Date;
import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {

    List<Task> findTasksByUser(User user);
    List<Task> findTasksByTypeOfTask(TypeOfTask typeOfTask);
    List<Task> findByTypeOfTaskAndCreatedAtBetween(TypeOfTask typeOfTask, Date start, Date end);

    @Query("select t from Task t inner join Comment c ON c.task.id = t.id where c.text like ?1")
    List<Task> findByCommentText(String searchedText);
}
