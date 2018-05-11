package com.senla.detailshop.api.dao.genericdao;

import com.senla.detailshop.entity.aentity.AEntity;

import java.util.List;

public interface IGenericDao<T extends AEntity> {

    public void add(T entity);

    public void delete(T entity);

    public T getById(Integer id);

    public List<T> getAll();

    public void update(T entity);

}
