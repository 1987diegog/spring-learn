package com.demente.ideas.learn.controllers;

import com.demente.ideas.learn.models.entity.User;
import com.demente.ideas.learn.models.services.IUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/app")
@SessionAttributes("user")
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

        return "user/profile";
    }

    @GetMapping("/user")
    public String getUser(Model model) {
        User user = userService.getMockUser();
        model.addAttribute("title", "Profile: " + user.getName());
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping(value = {"/users", "/"})
    public String userList(Model model) {
        model.addAttribute("title", "User list");
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/user-form")
    public String crear(Model model) {
        User user = new User();
        model.addAttribute("title", "User form");
        model.addAttribute("user", user);
        return "user/form";
    }

    @PostMapping("/user-form")
    public String guardar(@Valid User user, BindingResult result, Model model, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("title", "User form");
            model.addAttribute("user", user); // puede omitirse ya que se pasa implicitamente cuando
            // da error (binging result) si el attributo tiene el mismo nombre, en este caso "user"
            return "user/form";
        }

        userService.save(user);
        // eliminara el objeto 'user' de la session
        status.setComplete();
        return "redirect:users";
    }

    @GetMapping(value = "/user-form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model) {
        try {
            User user = null;
            user = userService.find(id);
            model.addAttribute("title", "User form edit");
            model.addAttribute("user", user);
            return "user/form";
        } catch (NotFoundException e) {
            model.addAttribute("title", e.getMessage());
            return "user/form";
        }
    }

    @GetMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return "redirect:/app/users";
    }
}
