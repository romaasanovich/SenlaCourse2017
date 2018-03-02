package com.senla.autoservice.dao;

import com.senla.autoservice.api.dao.IMasterDao;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.dao.abstractdao.GenericDao;
import com.senla.autoservice.utills.Convert;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MasterDao extends GenericDao<Master> implements IMasterDao {

	public MasterDao() {
	}

	public Master getById(int id ,Session session){
		return getById(id, session);
	}

	public void changeBusying(Session session,Master master) throws Exception {
		session.update(master);
	}

	public ArrayList<Master> getListOfMasters(String comp, Session session) throws Exception {
		List<Master> masters = new ArrayList<Master>();
		masters = session.createCriteria(Master.class).addOrder(org.hibernate.criterion.Order.asc(comp)).list();
		return (ArrayList<Master>) masters;
	}

	public Master getMasterCarriedOutOrder(com.senla.autoservice.bean.Order order, Session session) throws SQLException {
		Master master = null;
		Criteria criteria = session.createCriteria(Master.class).add(Restrictions.like("idOrder",order.getId()));
		master =(Master)criteria.list().get(0);
		return master;
	}
}
