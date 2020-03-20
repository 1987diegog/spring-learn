package com.demente.ideas.learn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/path")
public class PathParamController {

    // Lo utilizamos cuando queremos pasar datos que son comunes a dos o mas metodos
    // handlers del controlador, (por ejemplo si tuvieraos un select de paises). En este
    // caso @ModelAttribute carga title al controlador y podra ser accedido desde cualquier
    // vista (Thymeleaf, HTML, etc)
    @ModelAttribute("title")
    public String title() {
        return "Path param example (@PathVariable) [DementeIdeas]";
    }

    @GetMapping("/params/{name}")
    public String pathParamsExample(@PathVariable(name = "name", required = false) String name, Model model) {
        model.addAttribute("paramName", "Name: " + name);
        return "params";
    }

    @GetMapping("/params/{name}/{age}/{married}")
    public String pathParamsExample(@PathVariable(name = "name", required = false) String name,
                                    @PathVariable(name = "age", required = false) Integer age,
                                    @PathVariable(name = "married", required = false) Boolean married,
                                    Model model) {
        model.addAttribute("paramName", "Name: " + name);
        model.addAttribute("paramAge", "Age: " + age);
        model.addAttribute("paramMarried", "Married: " + ((married) ? "Si":"No"));

        return "params";
    }
}