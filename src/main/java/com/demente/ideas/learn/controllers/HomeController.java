package com.demente.ideas.learn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // redirect: redirige (reinicia) la peticion hacia otra peticion (cambia
    // el request) todos los parametros del request inicial se pierden, creandosese
    // una nueva peticion http (nuevos parametros). Pueden utilizarse rutas externas
    // al proyecto como por ejemplo https://google.com.
    @GetMapping(path = {"/home/redirect"})
    public String homeRedirect() {
        return "redirect:/app/index";
        // return "redirect:https://google.com";
    }

    // forward: utiliza la misma peticion, NO pierde los parametros (la url se mantiene
    // igual). El cambio se produce por debajo, ya que el forward lo que hace es ejecutar
    // un request dispatcher del api servlet y el metodo forward. Solo puede utilizarse
    // para rutas de controladores, rutas dentro del mismo proyecto, no para rutas externas.
    @GetMapping(path = {"/home/forward"})
    public String homeForward() {
        return "forward:/app/index";
    }

    @GetMapping(path = {"/", ""})
    public String redirectToHome() {
        return "redirect:/app/users";
    }
}
