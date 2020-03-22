package com.demente.ideas.learn.controllers;

import com.demente.ideas.learn.models.entity.User;
import com.demente.ideas.learn.models.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/injection-set")
public class InjectionSetController {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUser(Model model) {

        User user = userService.getUser();
        model.addAttribute("title", "Profile: " + user.getName());
        model.addAttribute("user", user);

        return "profile";
    }

}
