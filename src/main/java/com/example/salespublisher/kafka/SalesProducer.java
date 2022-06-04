package com.example.salespublisher.kafka;

import com.example.salespublisher.model.SalesHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SalesProducer {

    private String TOPIC = "sales-publisher";

    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    public boolean doSales(SalesHistory salesHistory)
    {

        try{
            this.kafkaTemplate.send(TOPIC,salesHistory);
            return  true;
        }catch (Exception e)
        {
            return false;
        }
    }
}
