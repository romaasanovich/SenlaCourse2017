package com.senla.autoservice.dao;

import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.api.dao.IOrderDao;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.dao.abstractdao.AGenericDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderDao extends AGenericDao<Order> implements IOrderDao {

    public OrderDao(){
        super(Order.class);
    }


    public ArrayList<Order> getListOfOrders(String comp, Session session) throws SQLException {
        List<Order> orders = new ArrayList<Order>();
        orders = session.createCriteria(Order.class).addOrder(org.hibernate.criterion.Order.asc(comp)).list();
        return (ArrayList<Order>) orders;
    }

    public ArrayList<Order> getListOfCurrentOrders(String comp, Session session) throws Exception {
        List<Order> orders = session.createCriteria(Order.class)
                .add(Restrictions.eq("status", StatusOrder.Opened)).addOrder(org.hibernate.criterion.Order.asc(comp)).list();
        return (ArrayList<Order>) orders;

    }

    public Order getOrderCurrentMaster(Master master, Session session) throws Exception {
        Criteria criteria = session.createCriteria(Order.class).add(Restrictions.like("master",master));
        return (Order)criteria.uniqueResult();
    }

    public ArrayList<Order> getOdersForPeriodOfTime(Date fDate,Date sDate,Session session) throws Exception {
        Criteria criteria =session.createCriteria(Order.class).add(Restrictions.le("orderDate",fDate)).add(Restrictions.ge("completionDate",sDate));
        return (ArrayList<Order>)criteria.list();
    }

    public int getCountOfPlacesOnDate(Date date, Session session) throws SQLException {
        Criteria criteria =session.createCriteria(Order.class).add(Restrictions.ge("orderDate",date)).add(Restrictions.le("completionDate",date));
        int countBusyPlace = criteria.list().size();
        return countBusyPlace;
    }

    public void changeStatusOfOrder(Order order, Session session) throws Exception {
        session.update(order);
    }

 }
