package com.senla.autoservice.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.senla.autoservice.api.dao.IWorkDao;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.dao.abstractdao.AGenericDao;
import org.hibernate.Criteria;
import org.hibernate.Session;

public class WorkDao extends AGenericDao<Work> implements IWorkDao {

    public WorkDao() {
        super(Work.class);
    }

    public ArrayList<Work> getListOfWorks(Session session) throws SQLException {
        Criteria criteria = session.createCriteria(Work.class);
        return (ArrayList<Work>) criteria.list();
    }


}
