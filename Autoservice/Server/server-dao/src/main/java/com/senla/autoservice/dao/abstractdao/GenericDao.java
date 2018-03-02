package com.senla.autoservice.dao.abstractdao;

import com.senla.autoservice.api.bean.AEntity;
import org.hibernate.Session;

public abstract class GenericDao<T extends AEntity> {

    public T getById(int id, Session session) {
        return (T) session.load(AEntity.class,id);
    }

    public void add(T entity, Session session) {
        session.save(entity);
    }

}
