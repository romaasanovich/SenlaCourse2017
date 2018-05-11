package com.senla.detailshop.dao;

import com.senla.detailshop.api.dao.IOrderDao;
import com.senla.detailshop.dao.genericdao.GenericDao;
import com.senla.detailshop.entity.HistoryOrder;
import com.senla.detailshop.entity.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends GenericDao<Order> implements IOrderDao {

    public OrderDao() {
        super(Order.class);
    }
}
