package com.fstch.YZEduadmin.mapper;

import java.util.List;

import com.fstch.YZEduadmin.models.Bill;

public interface BillMapper {
	List<Bill> findAll();
	void delBill(int bill_id);
}
