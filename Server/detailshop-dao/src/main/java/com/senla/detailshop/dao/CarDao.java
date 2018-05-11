package com.senla.detailshop.dao;

import com.senla.detailshop.api.dao.ICarDao;
import com.senla.detailshop.dao.genericdao.GenericDao;
import com.senla.detailshop.entity.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDao extends GenericDao<Car> implements ICarDao {

     public CarDao() {
        super(Car.class);
    }

}
