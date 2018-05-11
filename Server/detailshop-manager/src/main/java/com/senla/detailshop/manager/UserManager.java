package com.senla.detailshop.manager;

import com.senla.detailshop.api.dao.IUserDao;
import com.senla.detailshop.api.manager.IUserManager;
import com.senla.detailshop.dao.UserDao;
import com.senla.detailshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManager implements IUserManager {

    @Autowired
    private IUserDao userDao;

    @Transactional
    public void addUser(User user){
        try {
            userDao.add(user);
        }
        catch (RuntimeException ex){
//            bla vla
        }
    }

    @Transactional
    public void deleteUser(User user){
        userDao.delete(user);
    }

    @Transactional
    public void updateUser(User user){
        userDao.update(user);
    }

    @Transactional
    public User getById(Integer id){
        return userDao.getById(id);
    }

    @Transactional
    public List<User> getAllUsers(){
        return userDao.getAll();
    }

}
