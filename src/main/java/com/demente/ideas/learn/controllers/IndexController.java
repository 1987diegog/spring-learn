package com.demente.ideas.learn.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/app")
public class IndexController {

    // @Value: Inyeccion de dependecia (texto) a traves de .properties
    @Value("${index.title}")
    private String msgWellcome;

    // Lo utilizamos cuando queremos pasar datos que son comunes a dos o mas metodos
    // handlers del controlador, (por ejemplo si tuvieraos un select de paises). En este
    // caso @ModelAttribute carga title al controlador y podra ser accedido desde cualquier
    // vista (Thymeleaf, HTML, etc)
    @ModelAttribute("title")
    public String title() {
        return msgWellcome;
    }

    @ModelAttribute("countries")
    public List<String> countries() {
        List<String> countryList = Arrays.asList("Argentina", "Brasil", "España", "Japón", "Uruguay");
        return countryList;
    }

    // @RequestMapping(path = "/index", method = RequestMethod.GET)
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("wellcome", msgWellcome);
        // debe retornar el nombre de la vista, en este caso se asume que tendremos
        // una plantilla que se llama (/resources/templates/index.html)
        return "index";
    }
}
