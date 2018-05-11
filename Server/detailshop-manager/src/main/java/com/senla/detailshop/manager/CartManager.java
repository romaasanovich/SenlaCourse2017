package com.senla.detailshop.manager;


import com.senla.detailshop.api.dao.ICartDao;
import com.senla.detailshop.api.manager.ICarManager;
import com.senla.detailshop.api.manager.ICartManager;
import com.senla.detailshop.dao.CartDao;
import com.senla.detailshop.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartManager implements ICartManager {

    @Autowired
    private ICartDao cartDao;

    @Transactional
    public void addCart(Cart cart){
        try {
            cartDao.add(cart);
        }
        catch (RuntimeException ex){
//            bla vla
        }
    }

    @Transactional
    public void deleteCart(Cart cart){
        cartDao.delete(cart);
    }

    @Transactional
    public void updateCart(Cart cart){
        cartDao.update(cart);
    }

    @Transactional
    public Cart getById(Integer id){
        return cartDao.getById(id);
    }

    @Transactional
    public List<Cart> getAllCarts(){
        return cartDao.getAll();
    }

}
