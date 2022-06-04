package com.example.salespublisher.controller;

import com.example.salespublisher.Dao;
import com.example.salespublisher.model.SalesHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    Dao dao;

    @RequestMapping("/get")
    public List<SalesHistory> getSalesHistory()
    {
        return dao.getSalesData();
    }
}
