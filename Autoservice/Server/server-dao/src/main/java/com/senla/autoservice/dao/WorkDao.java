package com.senla.autoservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.senla.autoservice.api.dao.IWorkDao;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.dao.abstractdao.GenericDao;
import com.senla.autoservice.utills.Convert;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class WorkDao extends GenericDao<Work> implements IWorkDao {
	public static final String ADD_WORK = "INSERT INTO `mydb`.`work` (`nameOfService`, `price`, `idMaster`) VALUES (?,?,?)";
	public static final String GET_WORKS = "SELECT * FROM mydb.work;";
	

	public WorkDao() {

	}

	public ArrayList<Work> getListOfWorks(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Work.class);
		return (ArrayList<Work>) criteria.list();
	}
}
