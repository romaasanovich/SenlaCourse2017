package com.senla.detailshop.api.manager;

import com.senla.detailshop.entity.Car;

import java.util.List;

public interface ICarManager {
    public void addCar(Car car);
    public void deleteCar(Car car);
    public void updateCar(Car car);
    public Car getById(Integer id);
    public List<Car> getAllCars();
}
