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
@RequestMapping("/app")
public class UsersController {

    // Lo utilizamos cuando queremos pasar datos que son comunes a dos o mas metodos
    // handlers del controlador, (por ejemplo si tuvieraos un select de paises). En este
    // caso @ModelAttribute carga title al controlador y podra ser accedido desde cualquier
    // vista (Thymeleaf, HTML, etc)
    @ModelAttribute("title")
    public String title() {
        return "Words Meanings [DementeIdeas]";
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        User user = new User();
        user.setName("Diego");
        user.setLastname("Gonzalez");
        user.setEmail("1987diegog@gmail.com");

        model.addAttribute("title", "Profile: " + user.getName());
        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/users")
    public String userList(Model model) {

        List<User> userList = Arrays.asList(new User("Diego", "Gonzalez", "1987diegog@gmail.com"),
                new User("Irina", "Gonzalez", "iri@gmail.com"),
                new User("Silvia", "Narbaiz", "silnarbaiz@gmail.com"),
                new User("Ragnar", "Lothbrok", "ragnar@gmail.com"),
                new User("Roger", "Federer", "rfederer@gmail.com"),
                new User("Diego", "Forlan", "forlan@gmail.com"));

        model.addAttribute("title", "User list");
        model.addAttribute("users", userList);
        return "user-list";
    }

    @GetMapping("/view")
    public String view(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("data", "View name: " + name);
        return "view";
    }

    @GetMapping("/params")
    public String view(@RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "age", required = false) Integer age,
                       @RequestParam(value = "married", required = false) Boolean married,
                       Model model) {

        model.addAttribute("paramName", "Name: " + name);
        model.addAttribute("paramAge", "Age: " + age);
        model.addAttribute("paramMarried", "Married: " + ((married) ? "Si":"No"));

        return "params";
    }

    @GetMapping("/paramsHttpServlet")
    public String view(HttpServletRequest request, Model model) {

        String name = request.getParameter("name");
        Integer age = Integer.valueOf(request.getParameter("age"));
        Boolean married = Boolean.valueOf(request.getParameter("married"));

        model.addAttribute("paramName", "Name: " + name);
        model.addAttribute("paramAge", "Age: " + age);
        model.addAttribute("paramMarried", "Married: " + ((married) ? "Si":"No"));

        return "params";
    }

    @GetMapping("/add-user")
    public String view() {
        return "add-user";
    }





}
