package com.codercampus.assignment14.contoller;

import com.codercampus.assignment14.domain.User;
import com.codercampus.assignment14.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getWelcomePage(){
        return "welcome";
    }

    @PostMapping("/")
    @ResponseBody
    public User saveUser(@RequestBody User user, ModelMap model){
        User savedUser = this.userRepository.saveUser(user);
        model.put("user",savedUser);
       return user;
    }
}
