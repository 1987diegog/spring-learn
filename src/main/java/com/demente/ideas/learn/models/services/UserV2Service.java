package com.demente.ideas.learn.models.services;

import com.demente.ideas.learn.models.entity.User;
import com.demente.ideas.learn.models.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserV2Service")
public class UserV2Service implements IUserService {

    private final IUserRepository userRepository;

    public UserV2Service(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Es necesario que todo @Componte tenga un constructor por defecto para que pueda
    // ser inyectado DI (inyeccion de dependencias). En caso de no tener un constructor
    // con parametros no es necesario indicar el mismo (ya que se encuentra implicito)
    // pero si se tiene un constructor con parametros, es necesario indicar el constructor
    // por defecto.

    @Override
    public User getUser() {
        User user = new User("diegog09","Diego", "Gonzalez", "1987diegog@gmail.com");
        return user;
    }

    /**
     * @return
     */
    @Override
    public List<User> findAll() {
        List<User> userList = this.userRepository.findAll();
        return userList;
    }
}