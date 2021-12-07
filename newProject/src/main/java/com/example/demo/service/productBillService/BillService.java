package com.example.demo.service.productBillService;

import com.example.demo.model.Bill;
import com.example.demo.model.ProductBill;

import java.util.List;

public interface BillService {
    List<Bill> findBills(int id);

    void delete(int id);

    void save(Bill bill);

    void saveDetail(ProductBill productBill);
}
