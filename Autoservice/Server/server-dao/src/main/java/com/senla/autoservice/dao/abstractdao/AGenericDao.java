package com.senla.autoservice.dao.abstractdao;

import com.senla.autoservice.api.bean.AEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public abstract class AGenericDao<T extends AEntity> {
    Class<T> clazz ;

    public  AGenericDao(Class<T> clazz){
        this.clazz= clazz;
    }


    public T getById(int id, Session session) {
        return (T) session.createCriteria(clazz).add(Restrictions.eq("id", id)).uniqueResult();
    }

    public void add(T entity, Session session) {
        session.save(entity);
    }

}
