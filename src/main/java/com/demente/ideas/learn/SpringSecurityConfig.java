package com.demente.ideas.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // Registramos nuestro passwordEncoder como componente Spring.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {

        PasswordEncoder encoder = passwordEncoder();
        User.UserBuilder users = User.builder().passwordEncoder(encoder::encode);

        builder.inMemoryAuthentication()
                .withUser(users.username("admin").password("admin").roles("ADMIN", "USER"))
                .withUser(users.username("diegog09").password("123456").roles("USER"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // spring ejecutrara un filtro, un interceptor antes de cargar cualquier ruta de nuestro controlador
        // va a controlar si el usuario tiene permisos para permitirle continuar, sino, negara el acceso y
        // redirigira a la pagina de Login.
        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**", "/app/users").permitAll()
                .antMatchers("/upload/**").hasAnyRole("USER")
                .antMatchers("/app/user-form/**").hasAnyRole("ADMIN")
                .antMatchers("/factura/**").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }
}
