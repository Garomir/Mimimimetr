package com.ramich.Mimimimetr.controllers;

import com.ramich.Mimimimetr.entities.User;
import com.ramich.Mimimimetr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/registration")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(Model model, User user){
        if(!userService.saveUser(user)){
            model.addAttribute("falseReg", "User already exists");
            return "registration";
        }
        return "redirect:/login";
    }
}
