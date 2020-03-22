package com.demente.ideas.learn.controllers;

import com.demente.ideas.learn.models.entity.User;
import com.demente.ideas.learn.models.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/app")
public class UserController {

    // INYECCION DE DEPENDENCIAS 1
    // Con @Autowired podemos realizar inyeccion de dependencias sobre atributos
    @Autowired
    private IUserService userService;

    public UserController(IUserService userService) {

    }

    /*
        Si queremos decirle a Spring que no inyecte la implementacion marcada
        con @Primary (por defecto), podemos indicar la implementacion concreta
        utilizando @Qualifier y el nombre de la implementacion concreta de la
        interface que se esta inyectando

        @Autowired
        @Qualifier("UserV2Service")
        private IUserService userService;
    */

    /* INYECCION DE DEPENDENCIAS 2
    Otra forma de inyeccion de dependencias es a traves del contructor:

        private IUserService userService;

        @Autowired
        public UserController(IUserService userService) {
            this.userService = userService;
        }

    Tener en cuenta que si la inyeccion es por constructor, se puede omitir
    el @Autowired, ya que se inyecta de forma implicita.

    */

    /* INYECCION DE DEPENDENCIAS 3
    Otra forma de inyeccion de dependencias es a traves del metodo set:

        private IUserService userService;

        @Autowired
        public void setUserService(IUserService userService) {
            this.userService = userService;
        }

    */

    // @ModelAttribute lo utilizamos cuando queremos pasar datos que son comunes a dos o mas metodos
    // handlers del controlador, (por ejemplo si tuvieraos un select de paises). En este
    // caso @ModelAttribute carga title al controlador y podra ser accedido desde cualquier
    // vista (Thymeleaf, HTML, etc)
    @ModelAttribute("title")
    public String title() {
        return "Users [DementeIdeas]";
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

    @GetMapping("/user")
    public String getUser(Model model) {

        User user = userService.getUser();
        model.addAttribute("title", "Profile: " + user.getName());
        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/users")
    public String userList(Model model) {

        model.addAttribute("title", "User list");
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }



}
