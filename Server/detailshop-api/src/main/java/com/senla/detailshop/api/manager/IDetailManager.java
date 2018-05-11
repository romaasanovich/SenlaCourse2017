package com.senla.detailshop.api.manager;

import com.senla.detailshop.entity.Detail;

import java.util.List;

public interface IDetailManager {
    public void deleteDetail(Detail detail);
    public void updateDetail(Detail detail);
    public Detail getById(Integer id);
    public List<Detail> getAllDetails();
    public void addDetail(Detail detail);
    }
