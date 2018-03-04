package com.senla.autoservice.dao;

import com.senla.autoservice.api.dao.IMasterDao;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.dao.abstractdao.AGenericDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MasterDao extends AGenericDao<Master> implements IMasterDao {

	public MasterDao() {
		super(Master.class);
	}


	public void changeBusying(Session session,Master master) throws Exception {
		session.update(master);
	}

	public ArrayList<Master> getListOfMasters(String comp, Session session) throws Exception {
		List<Master> masters = new ArrayList<Master>();
		masters = session.createCriteria(Master.class).addOrder(org.hibernate.criterion.Order.asc(comp)).list();
		return (ArrayList<Master>) masters;
	}

	/*public Master getMasterCarriedOutOrder(com.senla.autoservice.bean.Order order, Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Master.class).add(Restrictions.like("master",));
		Master master =(Master)criteria.uniqueResult();
		return master;
	}*/
}
