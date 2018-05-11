package com.senla.detailshop.manager;

import com.senla.detailshop.api.dao.ICarDao;
import com.senla.detailshop.api.manager.ICarManager;
import com.senla.detailshop.dao.CarDao;
import com.senla.detailshop.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarManager implements ICarManager{

    @Autowired
    private ICarDao carDao;

    @Transactional
    public void addCar(Car car) {
        carDao.add(car);
    }

    @Transactional

    public void deleteCar(Car car) {
        carDao.delete(car);
    }

    @Transactional
    public void updateCar(Car car) {
        carDao.update(car);
    }

    @Transactional
    public Car getById(Integer id) {
        return carDao.getById(id);
    }

    @Transactional
    public List<Car> getAllCars() {
        return carDao.getAll();
    }

}
