package com.senla.detailshop.dao;

import com.senla.detailshop.api.dao.IUserDao;
import com.senla.detailshop.dao.genericdao.GenericDao;
import com.senla.detailshop.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends GenericDao<User> implements IUserDao {

    public UserDao() {
        super(User.class);
    }

}
