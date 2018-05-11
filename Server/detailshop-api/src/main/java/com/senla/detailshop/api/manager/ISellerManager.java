package com.senla.detailshop.api.manager;

import com.senla.detailshop.entity.Seller;

import java.util.List;

public interface ISellerManager {
    public void addSeller(Seller seller);
    public void deleteSeller(Seller seller);
    public void updateSeller(Seller seller);
    public Seller getById(Integer id);
    public List<Seller> getAllSellers();
}
