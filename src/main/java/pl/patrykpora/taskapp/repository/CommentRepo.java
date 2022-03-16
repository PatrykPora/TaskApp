package pl.patrykpora.taskapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.patrykpora.taskapp.entity.Comment;

public interface CommentRepo extends CrudRepository<Comment, Long> {
}
