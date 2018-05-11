package com.senla.detailshop.manager;

import com.senla.detailshop.api.dao.IOrderDao;
import com.senla.detailshop.api.manager.IOrderManager;
import com.senla.detailshop.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderManager implements IOrderManager {

    @Autowired
    private IOrderDao orderDao;

    @Transactional
    public void addOrder(Order Order) {
        orderDao.add(Order);
    }

    @Transactional
    public void deleteOrder(Order Order) {
        orderDao.delete(Order);
    }

    @Transactional
    public void updateOrder(Order Order) {
        orderDao.update(Order);
    }

    @Transactional
    public Order getById(Integer id) {
        return orderDao.getById(id);
    }

    @Transactional
    public List<Order> getAllOrders() {
        return (ArrayList<Order>) orderDao.getAll();
    }

}
