package com.demente.ideas.learn.models.services;

import com.demente.ideas.learn.models.entity.User;
import com.demente.ideas.learn.models.repository.IUserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
// @Primary indica que la implementacion concreta por defecto que debe inyectar Spring
// es esta clase marcada con @Primary, esto es necesario cuando se tienen varias
// implementaciones de una determinada interface, al momento de inyectar la interface
// Spring no sabe cual inyectar a no ser que se le indique.
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Es necesario que todo @Componte tenga un constructor por defecto para que pueda
    // ser inyectado DI (inyeccion de dependencias). En caso de no tener un constructor
    // con parametros no es necesario indicar el mismo (ya que se encuentra implicito)
    // pero si se tiene un constructor con parametros, es necesario indicar el constructor
    // por defecto.

    @Override
    @Transactional(readOnly=true)
    public User getUser() {
        User user = new User("1987Diegog","Diego",
                "Gonzalez", "1987diegog@gmail.com");
        return user;
    }

    /**
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public List<User> findAll() {
        List<User> userList = this.userRepository.findAll();
        return userList;
    }
}
