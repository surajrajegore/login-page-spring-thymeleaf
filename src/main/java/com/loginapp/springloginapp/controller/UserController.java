package com.loginapp.springloginapp.controller;

import com.loginapp.springloginapp.entity.User;
import com.loginapp.springloginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String login(Model model){
        User user   = new User();
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/userLogin")
    public String loginUser(@ModelAttribute("user") User user){

        String userId = user.getUserId();
        Optional<User> userData =  userRepository.findById(userId);
        if (user.getPassword().equals(userData.get().getPassword())){
            return "home";
        }
        else {
            return "error";
        }

    }
}
