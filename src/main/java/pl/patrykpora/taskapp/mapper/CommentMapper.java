package pl.patrykpora.taskapp.mapper;

import pl.patrykpora.taskapp.dto.CommentDto;
import pl.patrykpora.taskapp.entity.Comment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    public static CommentDto map (Comment comment) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setCreatedAt(dateFormat.format(comment.getCreatedAt()));
        return dto;
    }

    public static Comment map(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setText(comment.getText());
        return comment;
    }

    public static List<CommentDto> map (List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        comments.forEach( c -> commentDtos.add(map(c)));
        return commentDtos;
    }




}
