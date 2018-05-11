package com.senla.detailshop.dao.genericdao;

import com.senla.detailshop.api.dao.genericdao.IGenericDao;
import com.senla.detailshop.entity.aentity.AEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


public abstract class GenericDao <T extends AEntity>  implements IGenericDao<T>{

    @Autowired
    private SessionFactory sessionFactory;

    Class clazz;

    public GenericDao(Class clazz) {
        this.clazz = clazz;
    }

    public void add(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public void delete(T entity){
        sessionFactory.getCurrentSession().delete(entity);
    }

    public T getById(Integer id){
        return (T) sessionFactory.getCurrentSession().createCriteria(clazz).add(Restrictions.eq("id",id)).list().get(0);
    }

    public List<T> getAll(){
        return sessionFactory.getCurrentSession().createCriteria(clazz).list();
    }

    public void update(T entity){
        sessionFactory.getCurrentSession().update(entity);
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

}
