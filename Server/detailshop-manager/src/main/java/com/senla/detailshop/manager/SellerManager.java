package com.senla.detailshop.manager;

import com.senla.detailshop.api.dao.ISellerDao;
import com.senla.detailshop.api.manager.ISellerManager;
import com.senla.detailshop.dao.SellerDao;
import com.senla.detailshop.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerManager implements ISellerManager {

    @Autowired
    private ISellerDao sellerDao;

    @Transactional
    public void addSeller(Seller seller) {
        sellerDao.add(seller);
    }

    @Transactional

    public void deleteSeller(Seller seller) {
        sellerDao.delete(seller);
    }

    @Transactional
    public void updateSeller(Seller seller) {
        sellerDao.update(seller);
    }

    @Transactional
    public Seller getById(Integer id) {
        return sellerDao.getById(id);
    }

    @Transactional
    public List<Seller> getAllSellers() {
        return sellerDao.getAll();
    }

}
