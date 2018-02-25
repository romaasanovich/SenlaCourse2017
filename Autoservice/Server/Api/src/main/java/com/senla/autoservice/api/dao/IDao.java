package com.senla.autoservice.api.dao;

import com.senla.autoservice.api.bean.AEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDao {
    public AEntity getById(int id, Connection con) throws  SQLException;
}
