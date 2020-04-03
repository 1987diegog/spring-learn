package com.demente.ideas.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class LearnSpringApplication implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String password_admin = "admin";
        String password_num = "123456";

        String bCryptPassword = passwordEncoder.encode(password_admin);
        System.out.println("BCryptPasswordEncoder - Clave encriptada para admin: " + bCryptPassword);

		bCryptPassword = passwordEncoder.encode(password_num);
		System.out.println("BCryptPasswordEncoder - Clave encriptada para otros usuarios: :" + bCryptPassword);
	}
}

