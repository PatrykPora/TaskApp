package pl.patrykpora.taskapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.patrykpora.taskapp.dto.CommentDto;
import pl.patrykpora.taskapp.service.CommentService;
import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    public String saveComment(@Valid CommentDto commentDto, Error error) {
        commentService.save(commentDto);
        return "redirect:/task/";
    }
}
