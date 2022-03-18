package pl.patrykpora.taskapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.patrykpora.taskapp.dto.UserDto;
import pl.patrykpora.taskapp.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("register")
    public String getRegister(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute(value = "user") @Valid UserDto userDto, BindingResult errors, Model model){
        if(errors.hasErrors()){
            return "register";
        }
        userService.registerUser(userDto);
        return "redirect:/login";
    }
}
