package com.senla.autoservice.dao;

import com.senla.autoservice.bean.User;
import com.senla.autoservice.dao.abstractdao.GenericDao;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends GenericDao<User> {

    public UserDao() {
        super(User.class);
    }


}
