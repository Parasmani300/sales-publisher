package com.example.salespublisher.scheduler;

import com.example.salespublisher.Dao;
import com.example.salespublisher.kafka.SalesProducer;
import com.example.salespublisher.model.Sales;
import com.example.salespublisher.model.SalesHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class SalesScheduler {

    @Autowired
    Dao dao;

    @Autowired
    SalesProducer salesProducer;

    @Scheduled(fixedDelay = 3000)
    public void sendSalesDataToConsumer()
    {
        List<SalesHistory> salesData = dao.getSalesData();
        if(salesData.size()  >  0)
        {
            salesData.forEach(sales -> {
                boolean sentSalesInfo = salesProducer.doSales(sales);
                if(sentSalesInfo) {
                    System.out.println("Producing the sales data:" + sales);
                    boolean insertInHistory = dao.updateSalesHistory(sales);
                    if (insertInHistory) {
                        dao.deleteFromsSales(sales.getUuid());
                    }
                }else{
                    System.out.println("Error Publishing data" + sales);
                }
            });
        }
    }
}
