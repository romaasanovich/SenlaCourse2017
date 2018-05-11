package com.senla.detailshop.web;

import com.senla.detailshop.api.manager.IUserManager;
import com.senla.detailshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUserManager userManager;

    @GetMapping(value = "/users")
    public List getUsers() {
        List<User> users = userManager.getAllUsers();
        return users;
    }

    @PostMapping(value = "/users/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userManager.getById(id);
        return user;
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userManager.deleteUser(userManager.getById(id));
    }

    @PutMapping(value = "/users")
    public void addUser(String login, String password, String firstName, String secName, String phoneNumber, String email) {
        User user = new User(0,login,password, firstName, secName, phoneNumber, email);
        userManager.addUser(user);
    }


}
