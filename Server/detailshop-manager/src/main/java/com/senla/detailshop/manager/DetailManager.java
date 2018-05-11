package com.senla.detailshop.manager;


import com.senla.detailshop.api.dao.IDetailDao;
import com.senla.detailshop.api.manager.IDetailManager;
import com.senla.detailshop.entity.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetailManager implements IDetailManager {

    @Autowired
    private IDetailDao detailDao;

    @Transactional
    public void addDetail(Detail detail) {
        detailDao.add(detail);
    }

    @Transactional
    public void deleteDetail(Detail detail) {
        detailDao.delete(detail);
    }

    @Transactional
    public void updateDetail(Detail detail) {
        detailDao.update(detail);
    }

    @Transactional
    public Detail getById(Integer id) {
        return detailDao.getById(id);
    }

    @Transactional
    public List<Detail> getAllDetails() {
        return detailDao.getAll();
    }

}
