package com.senla.detailshop.web;

import com.senla.detailshop.api.dao.IDetailDao;
import com.senla.detailshop.api.manager.IDetailManager;
import com.senla.detailshop.entity.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DetailController {
    @Autowired
    private IDetailManager detailManager;

    @GetMapping(value = "/details")
    public List getDetails() {
        List<Detail> details = detailManager.getAllDetails();
        return details;
    }

    @PostMapping(value = "/details/{id}")
    public Detail getDetailById(@PathVariable int id) {
        Detail detail = detailManager.getById(id);
        return detail;
    }

    @DeleteMapping(value = "/details/{id}")
    public void deleteDetail(@PathVariable int id) {
        detailManager.deleteDetail(detailManager.getById(id));
    }

    @PutMapping(value = "/details")
    public void addDetail() {
        //TODO ddd
        Detail detail = new Detail();
        detailManager.addDetail(detail);
    }
}
