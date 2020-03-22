package com.demente.ideas.learn;

import com.demente.ideas.learn.models.services.IUserServiceBean;
import com.demente.ideas.learn.models.services.UserServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class InjectionBeanConfig {

    // Otra forma de indicarle al contenedor de Spring
    // que sera un componente a utilizar (inyectar)
    @Bean
    @Primary
    public IUserServiceBean registerServices() {
        return new UserServiceBean();
    }
}
