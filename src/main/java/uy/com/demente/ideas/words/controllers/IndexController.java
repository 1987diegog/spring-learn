package uy.com.demente.ideas.words.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uy.com.demente.ideas.words.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/app")
public class IndexController {

    // Lo utilizamos cuando queremos pasar datos que son comunes a dos o mas metodos
    // handlers del controlador, (por ejemplo si tuvieraos un select de paises). En este
    // caso @ModelAttribute carga title al controlador y podra ser accedido desde cualquier
    // vista (Thymeleaf, HTML, etc)
    @ModelAttribute("title")
    public String title() {
        return "Words Meanings [DementeIdeas]";
    }

    @ModelAttribute("countries")
    public List<String> countries() {
        List<String> countryList = Arrays.asList("Argentina", "Brasil", "España", "Japón", "Uruguay");
        return countryList;
    }

    // @RequestMapping(path = "/index", method = RequestMethod.GET)
    @GetMapping(path = {"", "/", "/index", "/home"})
    public String index(Model model) {
        model.addAttribute("wellcome", "Wellcome to Words meanings!!!");
        // debe retornar el nombre de la vista, en este caso se asume que tendremos
        // una plantilla que se llama (/resources/templates/index.html)
        return "index";
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


}
