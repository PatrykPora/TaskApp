package pl.patrykpora.taskapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.patrykpora.taskapp.dto.CommentDto;
import pl.patrykpora.taskapp.entity.Comment;
import pl.patrykpora.taskapp.entity.Task;
import pl.patrykpora.taskapp.exceptions.NoSuchTaskException;
import pl.patrykpora.taskapp.repository.CommentRepo;
import pl.patrykpora.taskapp.repository.TaskRepo;

@Service
public class CommentService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private CommentRepo commentRepo;

    public void save(CommentDto commentDto) {
        Comment comment = new Comment();
        Task task = taskRepo.findById(commentDto.getId()).orElseThrow(NoSuchTaskException::new);
        comment.setTask(task);
        commentRepo.save(comment);
    }

}
