package com.example.salespublisher.mapper;

import com.example.salespublisher.model.Sales;
import com.example.salespublisher.model.SalesHistory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SalesMapper implements RowMapper<SalesHistory> {
    @Override
    public SalesHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        String customerOrderNo = rs.getString("CUSTOMERODERNO");
        Integer item = rs.getInt("item");
        Integer dept = rs.getInt("dept");
        Integer deptClass = rs.getInt("class");
        Integer subclass = rs.getInt("subclass");
        Integer store = rs.getInt("store");
        Integer qty = rs.getInt("qty");
        Float totalCost = rs.getFloat("totalCost");
        String uuid = rs.getString("uuid");

        Sales sales = new Sales();
        sales.setCustomerOrderNo(customerOrderNo);
        sales.setItem(item);
        sales.setDept(dept);
        sales.setDeptClass(deptClass);
        sales.setSubclass(subclass);
        sales.setStore(store);
        sales.setQty(qty);
        sales.setTotalCost(totalCost);
        sales.setUuid(uuid);

        SalesHistory salesHistory = new SalesHistory();
        salesHistory.setUuid(uuid);
        salesHistory.setSales(sales);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        salesHistory.setTimestamp(timestamp);
        return  salesHistory;
    }
}
