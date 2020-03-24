package com.demente.ideas.learn.models.services;

import com.demente.ideas.learn.models.entity.User;

// Clase a modo de ejemplo que podra ser inyectada (se le avisara al contenedor de Spring)
// Se configurara utilizando @Bean, en la clase de @configuration InjectionBeanConfig
public interface IUserServiceBean {
    public User getUser();
}
