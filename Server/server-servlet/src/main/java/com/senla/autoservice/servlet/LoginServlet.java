package com.senla.autoservice.servlet;

import com.google.gson.JsonObject;
import com.senla.autoservice.bean.User;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.manager.UserManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("username");
        final String password = req.getParameter("password");

        if (login != null && password != null && !login.isEmpty() && !password.isEmpty()) {
            final User user = new UserManager().getUser(login, password);

            if (user != null) {
                final PrintWriter writer = resp.getWriter();
                req.getSession().setAttribute("user", user);
                writer.println("Login OK");
            }
        }
    }

    protected void doDelete(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            user.setToken("");
            final PrintWriter writer = resp.getWriter();
            writer.println("Disconected....");
        }
    }
}

