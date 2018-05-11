package com.senla.detailshop.dao;

import com.senla.detailshop.api.dao.ICartDao;
import com.senla.detailshop.dao.genericdao.GenericDao;
import com.senla.detailshop.entity.Cart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao extends GenericDao<Cart> implements ICartDao{

    public CartDao() {
        super(Cart.class);
    }
}
