package com.demente.ideas.learn.models.services;

import com.demente.ideas.learn.models.entity.User;

public class UserServiceBean implements IUserServiceBean {

    @Override
    public User getUser() {
        User user = new User("diegog09","Diego", "Gonzalez", "1987diegog@gmail.com");
        return user;
    }
}
