package com.senla.detailshop.api.manager;

import com.senla.detailshop.entity.Order;

import java.util.List;

public interface IOrderManager {
    public void addOrder(Order Order);
    public void deleteOrder(Order Order);
    public void updateOrder(Order Order);
    public Order getById(Integer id);
    public List<Order> getAllOrders();
}
