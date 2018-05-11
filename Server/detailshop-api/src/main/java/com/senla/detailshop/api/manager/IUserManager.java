package com.senla.detailshop.api.manager;

import com.senla.detailshop.entity.User;

import java.util.List;

public interface IUserManager {
    public void addUser(User user);
    public void deleteUser(User user);
    public void updateUser(User user);
    public User getById(Integer id);
    public List<User> getAllUsers();
}
