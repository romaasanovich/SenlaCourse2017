package com.senla.autoservice.dao;

import com.senla.autoservice.api.dao.IGarageDao;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.dao.abstractdao.GenericDao;
import com.senla.autoservice.utills.Convert;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GarageDao extends GenericDao<Place> implements IGarageDao {


    public GarageDao() {

    }

    public void changeBusying(Session session, Place place) throws Exception {
        session.update(place);
    }


    public ArrayList<Place> getSortedPlaces(Session session,String comp) throws Exception{
        List<Place> places = session.createCriteria(Place.class).addOrder(Order.asc(comp)).list();
        return (ArrayList<Place>) places;
    }

    public ArrayList<Place> getFreePlaces(Session session) throws SQLException {
        List<Place> places = session.createCriteria(Place.class)
                .add(Restrictions.eq("isBusy", 0)).list();
        return (ArrayList<Place>) places;
    }

}
