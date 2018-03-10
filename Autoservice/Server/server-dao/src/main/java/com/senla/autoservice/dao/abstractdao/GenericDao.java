package com.senla.autoservice.dao.abstractdao;

import com.senla.autoservice.api.bean.AEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public abstract class GenericDao<T extends AEntity> {
    Class<T> clazz ;

    public GenericDao(Class<T> clazz){
        this.clazz= clazz;
    }


    public T getById(int id, Session session) {
        return (T) session.createCriteria(clazz).add(Restrictions.eq("id", id)).list().get(0);

    }

    public void add(T entity, Session session) {
        session.save(entity);
    }

}
