package com.senla.detailshop.dao;

import com.senla.detailshop.api.dao.ISellerDao;
import com.senla.detailshop.dao.genericdao.GenericDao;
import com.senla.detailshop.entity.Seller;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SellerDao extends GenericDao<Seller> implements ISellerDao{

    public SellerDao() {
        super(Seller.class);
    }
}
