package com.senla.detailshop.dao;

import com.senla.detailshop.api.dao.IDetailDao;
import com.senla.detailshop.entity.Detail;
import com.senla.detailshop.dao.genericdao.GenericDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DetailDao extends GenericDao<Detail> implements IDetailDao{

    public DetailDao() {
        super(Detail.class);
    }
}
