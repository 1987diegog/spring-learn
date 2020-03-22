package com.demente.ideas.learn.models.services;

import com.demente.ideas.learn.models.entity.User;

import java.util.List;

public interface IUserService {
    User getUser();
    List<User> findAll();
}
