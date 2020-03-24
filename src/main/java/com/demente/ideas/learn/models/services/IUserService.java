package com.demente.ideas.learn.models.services;

import com.demente.ideas.learn.models.entity.User;
import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService {

    User save(User user);
    User getMockUser();
    List<User> findAll();
    User find(Long id) throws NotFoundException;
    void delete(Long id);
}
