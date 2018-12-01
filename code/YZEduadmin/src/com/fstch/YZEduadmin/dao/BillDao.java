package com.fstch.YZEduadmin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstch.YZEduadmin.mapper.BillMapper;
import com.fstch.YZEduadmin.models.Bill;
@Repository
public class BillDao implements BillMapper {
	@Autowired
	BillMapper billMapper;
	@Override
	public List<Bill> findAll() {
		// TODO Auto-generated method stub
		return billMapper.findAll();
	}
	@Override
	public void delBill(int bill_id) {
		// TODO Auto-generated method stub
		billMapper.delBill(bill_id);
	}

}
