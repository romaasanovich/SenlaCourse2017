package com.senla.detailshop.api.manager;

import com.senla.detailshop.entity.Cart;

import java.util.List;

public interface ICartManager {
    public void addCart(Cart cart);
    public void deleteCart(Cart cart);
    public void updateCart(Cart cart);
    public Cart getById(Integer id);
    public List<Cart> getAllCarts();
}
