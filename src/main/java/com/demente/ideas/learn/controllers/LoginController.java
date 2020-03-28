package com.demente.ideas.learn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, Principal principal, RedirectAttributes flash) {

        if(principal != null) {
            flash.addFlashAttribute("info", "Ya se inicio la sesion");
            // se evita que se inicie la secion varias veces, ya que
            // si principal es != null ya existe un login realizado,
            // por lo tanto, se redirige a la pantalla home "/".
            return "redirect:/";
        }
        return "login";
    }
}
