package com.demente.ideas.learn.controllers;

import com.demente.ideas.learn.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/query")
public class QueryParamController {

    // Lo utilizamos cuando queremos pasar datos que son comunes a dos o mas metodos
    // handlers del controlador, (por ejemplo si tuvieraos un select de paises). En este
    // caso @ModelAttribute carga title al controlador y podra ser accedido desde cualquier
    // vista (Thymeleaf, HTML, etc)
    @ModelAttribute("title")
    public String title() {
        return "Query Params example [DementeIdeas]";
    }

    @GetMapping("/param")
    public String oneParamsExample(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("paramName", "Name: " + name);
        return "params";
    }

    @GetMapping("/paramsRequestParam")
    public String manyRequestParamExample(@RequestParam(name = "name", required = false) String name,
                       @RequestParam(name = "age", required = false) Integer age,
                       @RequestParam(name = "married", required = false) Boolean married,
                       Model model) {

        model.addAttribute("paramName", "Name: " + name);
        model.addAttribute("paramAge", "Age: " + age);
        model.addAttribute("paramMarried", "Married: " + ((married) ? "Si":"No"));

        return "params";
    }

    @GetMapping("/paramsHttpServlet")
    public String manyParamsHttpServletExample(HttpServletRequest request, Model model) {

        String name = request.getParameter("name");
        Integer age = Integer.valueOf(request.getParameter("age"));
        Boolean married = Boolean.valueOf(request.getParameter("married"));

        model.addAttribute("paramName", "Name: " + name);
        model.addAttribute("paramAge", "Age: " + age);
        model.addAttribute("paramMarried", "Married: " + ((married) ? "Si":"No"));

        return "params";
    }
}
