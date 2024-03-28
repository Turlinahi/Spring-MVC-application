package com.wad.firstmvc.controllers;


import com.wad.firstmvc.domain.User;
import com.wad.firstmvc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //-populate the model with the retrieved users!
    //-select the appropriate view (navigation)
    @GetMapping
    public String viewUsers(Model model){
        model.addAttribute("users",userService.findAll());
        return "users";
    }

    @GetMapping("/new")
    public String showAddUserForm(Model model){
        model.addAttribute("user",new User());
        return "addusers";
    }

    @PostMapping("/new")
    public String addUser(User user){
        if(user.getId()==null)
            user.setId(new Random().nextLong());
        userService.save(user);
        return "redirect:/users";
    }

}
