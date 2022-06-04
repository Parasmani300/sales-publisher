package com.example.salespublisher;

import com.example.salespublisher.mapper.SalesMapper;
import com.example.salespublisher.model.Sales;
import com.example.salespublisher.model.SalesHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Dao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${sales.select.query}")
    private  String selectSales;

    @Value("${salesHistory.insert.query}")
    private String insertSalesHistory;

    @Value("${sales.delete.query}")
    private String salesDeleteQuery;

    public List<SalesHistory> getSalesData()
    {
        return namedParameterJdbcTemplate.query(selectSales,new SalesMapper());
    }

    public boolean updateSalesHistory(SalesHistory salesHistory)
    {
        try{
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("uuid",salesHistory.getUuid());
            params.addValue("MESSAGECREATETIME",salesHistory.getTimestamp());
            params.addValue("MESSAGEPAYLOAD",new SqlLobValue(String.valueOf(salesHistory.getSales()),new DefaultLobHandler()),Types.CLOB);
            namedParameterJdbcTemplate.update(insertSalesHistory,params);
            return true;
        }catch(Exception e)
        {
            return false;
        }

    }

    public void deleteFromsSales(String uuid)
    {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("uuid",uuid);
        namedParameterJdbcTemplate.update(salesDeleteQuery,parameterSource);
    }



}
